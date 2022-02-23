package io.vividcode.springnative.nativesupport.flyway.graal;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.TargetClass;
import org.flywaydb.core.internal.scanner.classpath.JarFileClassPathLocationScanner;

/**
 * Just make the constructor visible to ClassPathScannerSubstitutions.
 */
@TargetClass(JarFileClassPathLocationScanner.class)
public final class JarFileClassPathLocationScannerSubstitutions {

    @Alias
    public JarFileClassPathLocationScannerSubstitutions(String separator) {
    }
}
