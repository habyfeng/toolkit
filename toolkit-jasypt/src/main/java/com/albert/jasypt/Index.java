package com.albert.jasypt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author losfoo
 * @since 2025-02-24
 */
@Controller
public class Index {

    @Value("${spring.datasource.sakila.password}")
    private String password;

    @GetMapping("/")
    public String index() {
        System.out.println(password);
        return "Done!";
    }
}
