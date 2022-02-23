package io.vividcode.springnative.jpawebmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.nativex.hint.NativeHint;

@Configuration
@NativeHint(
        options = {
                "-H:+ReportExceptionStackTraces",
        }
)
public class SpringNativeConfiguration {

}
