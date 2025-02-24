package com.albert.jasypt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author losfoo
 * @since 2025-02-24
 */
@SpringBootTest
public class StringEncryptorUtilTest {
    @Autowired
    private StringEncryptorUtil stringEncryptorUtil;

    @Test
    public void testEncrypt() {
        String oriString = "123456";
        String encryptedString = stringEncryptorUtil.encrypt(oriString);
        System.out.println(encryptedString);
    }

    @Test
    public void testDecrypt() {
        String encryptedString = "HWw5ZSmp1MISQCfP8G3nlrGifNqAEN6kmNeFTusPbKAAZsN58bgleYSDb4v6ByZZ";
        String oriString = stringEncryptorUtil.decrypt(encryptedString);
        System.out.println(oriString);
    }

}
