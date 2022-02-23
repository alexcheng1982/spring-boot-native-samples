package io.vividcode.springnative.jpawebmvc.user;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.nativex.hint.NativeHint;

@TestConfiguration
@NativeHint(
        options = {
                "--enable-url-protocols=unix"
        }
)
public class NativeTestConfiguration {

}
