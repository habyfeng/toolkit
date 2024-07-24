package com.albert.toolkit.http;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * http客户端
 *
 * @author hoyo
 * @since 2024-07-20
 */
public class HttpClient {

    /**
     * @param url       不带参数的url
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 响应内容
     * @throws IOException
     */
    public static String doGet(String url, String startDate, String endDate) throws IOException {
        String result;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ClassicRequestBuilder requestBuilder = ClassicRequestBuilder.get(url);
            if (startDate != null) {
                requestBuilder.addParameter("start_date", startDate);
            }
            if (endDate != null) {
                requestBuilder.addParameter("end_date", endDate);
            }

            ClassicHttpRequest httpGet = requestBuilder.build();
            result = httpClient.execute(httpGet, response -> {
                HttpEntity httpEntity = response.getEntity();
                return EntityUtils.toString(httpEntity, "utf-8");
            });
        }

        return result;
    }

    /**
     * @param url            下载文件url
     * @param targetFilepath 目标文件路径
     * @throws IOException
     */
    public static void download(String url, String targetFilepath) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             OutputStream ops = new FileOutputStream(targetFilepath)) {
            ClassicRequestBuilder requestBuilder = ClassicRequestBuilder.get(url);


            ClassicHttpRequest httpGet = requestBuilder.build();
            httpClient.execute(httpGet, response -> {
                response.getEntity().writeTo(ops);
                return null;
            });
        }
    }
}
