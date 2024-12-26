package com.albert.toolkit.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * @author losfoo
 * @since 2024-12-25
 */
public class RsaUtil {

    public static PublicKey getPublicKey(String keyStoreFileName) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

        InputStream ins = RsaUtil.class.getResourceAsStream(keyStoreFileName);
        // JKS密钥库密码
        String keyStorePass = "123456";
        keyStore.load(ins, keyStorePass.toCharArray());

        String alias = "foo";
        return keyStore.getCertificate(alias).getPublicKey();
    }

    public static PrivateKey getPrivateKey(String keyStoreFileName) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream ins = RsaUtil.class.getResourceAsStream(keyStoreFileName);
        // JKS密钥库密码
        String keyStorePass = "123456";
        keyStore.load(ins, keyStorePass.toCharArray());

        String alias = "foo";
        String keyPass = "123456";
        return (PrivateKey) keyStore.getKey(alias, keyPass.toCharArray());
    }

}
