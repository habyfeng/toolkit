package com.albert.toolkit.jwt;

import com.albert.toolkit.config.RsaKeyConfig;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

/**
 * @author losfoo
 * @since 2024-12-25
 */
public class JwtUtil {

    public static String createJwtWithPrivateKey() {
        Map<String, Object> customClaims = Map.of("name", "losfoo", "age", "99", "gender", "male");
        RsaKeyConfig rsaKeyConfig = RsaKeyConfig.getInstance();
        String jwt = Jwts.builder()
                .id("com.albert.toolkit.jwt")
                .subject("Test")
                .issuer("Losfoo")
                .issuedAt(Date.from(Instant.now()))
                .notBefore(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(365L, ChronoUnit.DAYS)))
                .claims(customClaims)
                .signWith(rsaKeyConfig.getPrivateKey())
                .compact();
        // 格式：Header.Payload.Signature
        System.out.println("jwt: " + jwt);

        return jwt;
    }

    public static void parseJwtWithPublicKey(String jwtContent) {
        RsaKeyConfig rsaKeyConfig = RsaKeyConfig.getInstance();
        Jwt<?, ?> jwt = Jwts.parser().verifyWith(rsaKeyConfig.getPublicKey()).build().parse(jwtContent);
        System.out.println("header: " + jwt.getHeader());
        System.out.println("payload: " + jwt.getPayload());
    }


    public static String createJwsWithSecretKey(SecretKey secretKey) {
        String content = "What a beautiful day!";
        String jws = Jwts.builder()
                .signWith(secretKey)
                .content(content.getBytes(StandardCharsets.UTF_8))
                .header()
                // 建议指定Content-Type
                .contentType("text/plain")
                .and()
                .compact();

        System.out.println("jws: " + jws);
        return jws;
    }

    public static String parseJwsWithSecretKey(String jwsContent, SecretKey secretKey) {
        Jws<byte[]> jws = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedContent(jwsContent);

        String payload = new String(jws.getPayload(), StandardCharsets.UTF_8);
        System.out.println("payload: " + payload);

        return payload;
    }

}
