package io.vividcode.springnative.grpc;

import org.springframework.context.annotation.Configuration;
import org.springframework.nativex.hint.NativeHint;

@Configuration
@NativeHint(
        options = {
                "-H:+ReportExceptionStackTraces",
                "--initialize-at-run-time=io.netty",
                "--initialize-at-build-time=io.netty.util",
        }
)
public class SpringNativeConfiguration {

}
