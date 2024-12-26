package com.albert.toolkit.config;

import com.albert.toolkit.util.RsaUtil;
import lombok.Getter;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * @author losfoo
 * @since 2024-12-25
 */
@Getter
public class RsaKeyConfig {
    private static final String keyStoreFileName = "/foo.jks";
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    private static class RsaKeyConfigHolder {
        static RsaKeyConfig rsaKeyConfig = new RsaKeyConfig();
    }

    public static RsaKeyConfig getInstance() {
        return RsaKeyConfigHolder.rsaKeyConfig;
    }

    private RsaKeyConfig() {
        try {
            this.publicKey = RsaUtil.getPublicKey(keyStoreFileName);
            this.privateKey = RsaUtil.getPrivateKey(keyStoreFileName);
            System.out.println("RsaKeyConfig inited");
        } catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException |
                 UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        }
    }

}
