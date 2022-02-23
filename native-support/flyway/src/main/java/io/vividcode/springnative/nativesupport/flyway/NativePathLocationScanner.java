package io.vividcode.springnative.nativesupport.flyway;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.migration.JavaMigration;
import org.flywaydb.core.api.resource.LoadableResource;
import org.flywaydb.core.internal.resource.classpath.ClassPathResource;
import org.flywaydb.core.internal.scanner.classpath.ResourceAndClassScanner;
import org.flywaydb.core.internal.scanner.filesystem.FileSystemScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used in order to prevent Flyway from doing classpath scanning which is both slow
 * and won't work in native mode
 */
@SuppressWarnings("rawtypes")
public final class NativePathLocationScanner implements ResourceAndClassScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(NativePathLocationScanner.class);
    private static final String LOCATION_SEPARATOR = "/";
    private static Collection<String> applicationMigrationFiles = Collections.emptyList(); // the set default to aid unit tests
    private static Collection<Class<? extends JavaMigration>> applicationMigrationClasses = Collections.emptyList(); // the set default to aid unit tests
    private static Map<String, Collection<Callback>> applicationCallbackClasses = Collections.emptyMap(); // the set default to aid unit tests

    private final Collection<LoadableResource> scannedResources;

    public NativePathLocationScanner(Collection<Location> locations) {
        LOGGER.debug("Locations: {}", locations);

        this.scannedResources = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        FileSystemScanner fileSystemScanner = null;
        for (String migrationFile : applicationMigrationFiles) {
            if (isClassPathResource(locations, migrationFile)) {
                LOGGER.debug("Loading {}", migrationFile);
                scannedResources.add(new ClassPathResource(null, migrationFile, classLoader,
                        StandardCharsets.UTF_8));
            } else if (migrationFile.startsWith(Location.FILESYSTEM_PREFIX)) {
                if (fileSystemScanner == null) {
                    fileSystemScanner = new FileSystemScanner(StandardCharsets.UTF_8, false, false,
                            false);
                }
                LOGGER.debug("Checking {} for migration files", migrationFile);
                Collection<LoadableResource> resources = fileSystemScanner.scanForResources(
                        new Location(migrationFile));
                LOGGER.debug("{} contains {} migration files", migrationFile, resources.size());
                scannedResources.addAll(resources);
            }
        }

    }

    public static void setApplicationCallbackClasses(
            Map<String, Collection<Callback>> callbackClasses) {
        NativePathLocationScanner.applicationCallbackClasses = callbackClasses;
    }

    public static Collection<Callback> callbacksForDataSource(String dsName) {
        return applicationCallbackClasses.getOrDefault(dsName, Collections.emptyList());
    }

    /**
     * @return The resources that were found.
     */
    @Override
    public Collection<LoadableResource> scanForResources() {
        return scannedResources;
    }

    private boolean isClassPathResource(Collection<Location> locations, String migrationFile) {
        for (Location location : locations) {
            String locationPath = location.getPath();
            if (!locationPath.endsWith(LOCATION_SEPARATOR)) {
                locationPath += "/";
            }

            if (migrationFile.startsWith(locationPath)) {
                return true;
            } else {
                LOGGER.debug(
                        "Migration file '{}' will be ignored because it does not start with '{}'",
                        migrationFile,
                        locationPath);
            }
        }

        return false;
    }

    /**
     * Scans the classpath for concrete classes under the specified package implementing this
     * interface. Non-instantiable abstract classes are filtered out.
     *
     * @return The non-abstract classes that were found.
     */
    @Override
    public Collection<Class<? extends JavaMigration>> scanForClasses() {
        return applicationMigrationClasses;
    }

    public static void setApplicationMigrationFiles(Collection<String> applicationMigrationFiles) {
        NativePathLocationScanner.applicationMigrationFiles = applicationMigrationFiles;
    }

    public static void setApplicationMigrationClasses(
            Collection<Class<? extends JavaMigration>> applicationMigrationClasses) {
        NativePathLocationScanner.applicationMigrationClasses = applicationMigrationClasses;
    }
}
