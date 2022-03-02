package io.vividcode.springnative.grpc;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.nativex.hint.JdkProxyHint;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.type.NativeConfiguration;

@NativeHint(
        jdkProxies = @JdkProxyHint(types = {Configuration.class, SynthesizedAnnotation.class})
)
public class NativeTestConfiguration implements NativeConfiguration {

}
