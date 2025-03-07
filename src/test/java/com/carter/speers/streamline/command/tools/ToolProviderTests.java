package com.carter.speers.streamline.command.tools;

import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;
import java.util.spi.ToolProvider;
import java.util.stream.StreamSupport;

public class ToolProviderTests {

    @Test
    public void lookAtAvailableTools() {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ServiceLoader<ToolProvider> sl = ServiceLoader.load(ToolProvider.class, systemClassLoader);
        StreamSupport.stream(sl.spliterator(), false)
                .forEach(p -> System.out.println(p.name()));
    }
}
