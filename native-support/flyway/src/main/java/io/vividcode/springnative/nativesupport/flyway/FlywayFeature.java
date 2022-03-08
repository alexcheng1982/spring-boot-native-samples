package io.vividcode.springnative.nativesupport.flyway;

import com.oracle.svm.core.annotate.AutomaticFeature;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.flywaydb.core.api.Location;
import org.graalvm.nativeimage.hosted.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AutomaticFeature
public class FlywayFeature implements Feature {

    private static final String CLASSPATH_APPLICATION_MIGRATIONS_PROTOCOL = "classpath";
    private static final String JAR_APPLICATION_MIGRATIONS_PROTOCOL = "jar";
    private static final String FILE_APPLICATION_MIGRATIONS_PROTOCOL = "file";
    private static final String FILE = "file";
    private static final String JAR = "jar";
    private static final String FLYWAY_LOCATIONS = "flyway.locations";
    private static final String DEFAULT_FLYWAY_LOCATIONS = "classpath:db/migration";

    private static final Logger LOGGER = LoggerFactory.getLogger(FlywayFeature.class);

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        List<String> locations = Stream
                .of(System.getProperty(FLYWAY_LOCATIONS, DEFAULT_FLYWAY_LOCATIONS).split(","))
                .toList();
        try {
            NativePathLocationScanner.setApplicationMigrationFiles(
                    discoverApplicationMigrations(locations));
        } catch (IOException e) {
            LOGGER.warn("Failed to scan migration files", e);
        }
    }

    private Collection<String> discoverApplicationMigrations(Collection<String> locations)
            throws IOException {
        LinkedHashSet<String> applicationMigrationResources = new LinkedHashSet<>();
        // Locations can be a comma separated list
        for (String location : locations) {
            location = normalizeLocation(location);
            if (location.startsWith(Location.FILESYSTEM_PREFIX)) {
                applicationMigrationResources.add(location);
                continue;
            }

            String finalLocation = location;
            consumeAsPaths(Thread.currentThread().getContextClassLoader(), location,
                    path -> {
                        Set<String> applicationMigrations = null;
                        try {
                            applicationMigrations = getApplicationMigrationsFromPath(
                                    finalLocation, path);
                        } catch (IOException e) {
                            LOGGER.warn("Can't process files in path {}", path);
                        }
                        if (applicationMigrations != null) {
                            applicationMigrationResources.addAll(applicationMigrations);
                        }
                    });
        }
        return applicationMigrationResources;
    }

    private String normalizeLocation(String location) {
        if (location == null) {
            throw new IllegalStateException("Flyway migration location may not be null.");
        }

        // Strip any 'classpath:' protocol prefixes because they are assumed
        // but not recognized by ClassLoader.getResources()
        if (location.startsWith(CLASSPATH_APPLICATION_MIGRATIONS_PROTOCOL + ':')) {
            location = location.substring(CLASSPATH_APPLICATION_MIGRATIONS_PROTOCOL.length() + 1);
            if (location.startsWith("/")) {
                location = location.substring(1);
            }
        }
        if (!location.endsWith("/")) {
            location += "/";
        }

        return location;
    }

    private Set<String> getApplicationMigrationsFromPath(final String location,
            final Path rootPath)
            throws IOException {

        try (final Stream<Path> pathStream = Files.walk(rootPath)) {
            return pathStream.filter(Files::isRegularFile)
                    .map(it -> Paths.get(location, rootPath.relativize(it).toString()).normalize()
                            .toString())
                    .map(it -> it.replace('\\', '/'))
                    .peek(it -> LOGGER.debug("Discovered path: {}", it))
                    .collect(Collectors.toSet());
        }
    }

    private void consumeAsPaths(ClassLoader cl, String resource, Consumer<Path> consumer)
            throws IOException {
        final Enumeration<URL> resources = cl.getResources(resource);
        while (resources.hasMoreElements()) {
            consumeAsPath(resources.nextElement(), consumer);
        }
    }

    private void consumeAsPath(URL url, Consumer<Path> consumer) {
        processAsPath(url, p -> {
            consumer.accept(p);
            return null;
        });
    }

    private <R> R processAsPath(URL url, Function<Path, R> function) {
        if (JAR.equals(url.getProtocol())) {
            final String file = url.getFile();
            final int exclam = file.lastIndexOf('!');
            final Path jar;
            try {
                jar = toLocalPath(exclam >= 0 ? new URL(file.substring(0, exclam)) : url);
            } catch (MalformedURLException e) {
                throw new RuntimeException(
                        "Failed to create a URL for '" + file.substring(0, exclam) + "'", e);
            }
            try (FileSystem jarFs = FileSystems.newFileSystem(jar)) {
                Path localPath;
                if (exclam >= 0) {
                    localPath = jarFs.getPath(file.substring(exclam + 1));
                } else {
                    localPath = jarFs.getPath("/");
                }
                return function.apply(localPath);
            } catch (IOException e) {
                throw new UncheckedIOException("Failed to read " + jar, e);
            }
        }

        if (FILE.equals(url.getProtocol())) {
            return function.apply(toLocalPath(url));
        }

        throw new IllegalArgumentException(
                "Unexpected protocol " + url.getProtocol() + " for URL " + url);
    }

    private Path toLocalPath(final URL url) {
        try {
            return Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Failed to translate " + url + " to local path", e);
        }
    }
}
