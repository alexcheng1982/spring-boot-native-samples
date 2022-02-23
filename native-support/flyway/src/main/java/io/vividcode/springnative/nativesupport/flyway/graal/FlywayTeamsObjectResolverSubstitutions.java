package io.vividcode.springnative.nativesupport.flyway.graal;

import com.oracle.svm.core.annotate.Delete;
import com.oracle.svm.core.annotate.TargetClass;
import org.flywaydb.core.internal.FlywayTeamsObjectResolver;

/**
 * Remove this class as it's not supposed to be used
 */
@Delete
@TargetClass(FlywayTeamsObjectResolver.class)
public final class FlywayTeamsObjectResolverSubstitutions {

}
