package io.vividcode.springnative.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.nativex.hint.NativeHint;

@SpringBootApplication
@NativeHint(
        options = {
                "-H:+ReportExceptionStackTraces",
                "--initialize-at-run-time=io.netty",
                "--initialize-at-build-time=io.netty.util",
        },
        aotProxies = {
                @AotProxyHint(targetClass = org.lognet.springboot.grpc.FailureHandlingSupport.class)
        }
)
public class GrpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcApplication.class, args);
    }

}
