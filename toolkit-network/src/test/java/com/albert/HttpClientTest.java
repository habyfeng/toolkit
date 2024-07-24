package com.albert;

import com.albert.toolkit.http.HttpClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class HttpClientTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testDoGet() throws IOException {
        String content = HttpClient.doGet("http://www.baidu.com", null, null);
        System.out.println(content);
    }
}
