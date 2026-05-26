package com.albert.toolkit.tcp;

import com.albert.data.DemoReqVo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author losfoo
 * @since 2026-05-26
 */
public class TcpClientTest {
    TcpClient tcpClient = new TcpClient();

    @Test
    public void testSend() throws IOException, ClassNotFoundException {
        DemoReqVo reqVo = new DemoReqVo();
        reqVo.name = "Losfoo";
        reqVo.age = "98";

        tcpClient.send(reqVo);
    }
}
