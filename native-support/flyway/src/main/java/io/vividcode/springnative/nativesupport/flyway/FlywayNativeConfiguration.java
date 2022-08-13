package io.vividcode.springnative.nativesupport.flyway;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.logging.buffered.BufferedLogCreator;
import org.flywaydb.core.internal.logging.slf4j.Slf4jLogCreator;
import org.flywaydb.core.internal.util.ClassUtils;
import org.flywaydb.core.internal.util.FeatureDetector;
import org.springframework.nativex.hint.*;
import org.springframework.nativex.type.NativeConfiguration;

@NativeHint(
    trigger = Flyway.class,
    initialization = {
      @InitializationHint(
          types = {
            FeatureDetector.class,
            NativePathLocationScanner.class,
            BufferedLogCreator.class,
            ClassUtils.class,
          },
          initTime = InitializationTime.BUILD)
    },
    types = {@TypeHint(types = {Slf4jLogCreator.class})},
    resources = {@ResourceHint(patterns = "org/flywaydb/core/internal/version.txt")})
public class FlywayNativeConfiguration implements NativeConfiguration {}
