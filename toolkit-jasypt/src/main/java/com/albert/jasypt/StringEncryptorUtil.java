package com.albert.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author losfoo
 * @since 2025-02-24
 */
@Component
public class StringEncryptorUtil {
    @Autowired
    private StringEncryptor stringEncryptor;

    /**
     * 加密字符串
     * @param pwd 明文
     * @return 密文
     */
    public String encrypt(String pwd) {
        String result = stringEncryptor.encrypt(pwd);
        return result;
    }


    /**
     * 解密
     * @param encryptedString 密文
     * @return 明文
     */
    public String decrypt(String encryptedString) {
        String oriString = stringEncryptor.decrypt(encryptedString);
        return oriString;
    }
}
