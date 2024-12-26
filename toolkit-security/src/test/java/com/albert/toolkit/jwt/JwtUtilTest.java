package com.albert.toolkit.jwt;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

/**
 * @author losfoo
 * @since 2024-12-26
 */
public class JwtUtilTest {

    @Test
    public void testCreateJwt() {
        String jwt = JwtUtil.createJwtWithPrivateKey();
        System.out.println(jwt);

        JwtUtil.parseJwtWithPublicKey(jwt);
    }

    @Test
    public void testCreateJws() {
        // 生成随机密钥
        SecretKey testKey = Jwts.SIG.HS512.key().build();
        String jws = JwtUtil.createJwsWithSecretKey(testKey);
        System.out.println("jws: " + jws);

        String payload = JwtUtil.parseJwsWithSecretKey(jws, testKey);
        System.out.println("payload: " + payload);
    }
}
