package com.albert.toolkit.https;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * TLS客户端
 *
 * @author hoyo
 * @since 2014-07-20
 */
public class TlsClient {

    /**
     *  创建ssl套接字
     *
     * @param host 主机名或IP
     * @param port 端口
     * @return
     */
    public SSLSocket createConnection(String host, int port) {
        String severTrustCerPath = "";

        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream(severTrustCerPath), "qwe123#@!".toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(keyStore);

            SSLContext sslContext = SSLContext.getInstance("tls1.2");
            sslContext.init(null, tmf.getTrustManagers(), null);

            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            InetAddress inetAddress = InetAddress.getByName(host);
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(inetAddress, port);
            return sslSocket;
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }
}
