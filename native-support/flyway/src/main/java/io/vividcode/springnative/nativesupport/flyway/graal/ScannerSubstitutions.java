package io.vividcode.springnative.nativesupport.flyway.graal;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import io.vividcode.springnative.nativesupport.flyway.NativePathLocationScanner;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.resource.LoadableResource;
import org.flywaydb.core.internal.scanner.LocationScannerCache;
import org.flywaydb.core.internal.scanner.ResourceNameCache;
import org.flywaydb.core.internal.scanner.Scanner;
import org.flywaydb.core.internal.scanner.classpath.ResourceAndClassScanner;

/**
 * Needed to get rid of some Android related classes
 */
@TargetClass(Scanner.class)
public final class ScannerSubstitutions {

    @Alias
    private List<LoadableResource> resources = new ArrayList<>();

    @Alias
    private List<Class<?>> classes = new ArrayList<>();

    @Alias
    private HashMap<String, LoadableResource> relativeResourceMap = new HashMap<>();

    @Substitute
    public ScannerSubstitutions(Class<?> implementedInterface,
            Collection<Location> locations,
            ClassLoader classLoader,
            Charset encoding,
            boolean detectEncoding,
            boolean stream,
            ResourceNameCache resourceNameCache,
            LocationScannerCache locationScannerCache,
            boolean throwOnMissingLocations) {
        ResourceAndClassScanner<?> scanner = new NativePathLocationScanner(locations);
        Collection<LoadableResource> resources = scanner.scanForResources();
        this.resources.addAll(resources);

        Collection scanForClasses = scanner.scanForClasses();
        classes.addAll(scanForClasses);

        for (LoadableResource resource : this.resources) {
            relativeResourceMap.put(resource.getRelativePath().toLowerCase(), resource);
        }
    }
}