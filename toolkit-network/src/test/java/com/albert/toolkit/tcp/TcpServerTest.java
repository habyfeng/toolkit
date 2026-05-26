package com.albert.toolkit.tcp;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author losfoo
 * @since 2026-05-26
 */
public class TcpServerTest {
    TcpServer tcpServer = new TcpServer();

    @Test
    public void testStart() throws IOException, ClassNotFoundException {
        tcpServer.start();
    }
}
