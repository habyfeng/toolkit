package com.albert;

import com.albert.toolkit.csv.CarPosition;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void test() {
        String s1 = null;
        String s2 = null;
        assertTrue(StringUtils.equals(s1, s2));
    }

    @Test
    public void testOpt() {
        CarPosition position = new CarPosition();
        position.setId(111);
        position.setName("one");

        CarPosition position2 = new CarPosition();
        position2.setId(222);

        Optional.ofNullable(position).ifPresent(info -> position2.setName(info.getName()));

        System.out.println(position2);
    }

    @Test
    public void testSubString() {
        String s = "aa0";
        System.out.println(s.substring(0, 0));
    }


    @Test
    public void testIsoDatetime() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        System.out.println(LocalDateTime.now().format(df));
    }

    @Test
    public void testNumFormat() {
        BigDecimal amt1 = new BigDecimal("1400");
        System.out.println("1========" + new DecimalFormat("0.00").format(amt1));

        BigDecimal amt2 = new BigDecimal("1400.1");
        System.out.println("2========" + new DecimalFormat("0.00").format(amt2));

        BigDecimal amt3 = new BigDecimal("1400.12");
        System.out.println("3========" + new DecimalFormat("0.00").format(amt3));

        BigDecimal amt4 = new BigDecimal("1400.120");
        System.out.println("4========" + new DecimalFormat("0.00").format(amt4));

        BigDecimal amt5 = new BigDecimal("1400.121");
        System.out.println("5========" + new DecimalFormat("0.00").format(amt5));

        BigDecimal amt6 = new BigDecimal("97881400.121");
        System.out.println("6========" + new DecimalFormat("0.00").format(amt6));

        BigDecimal amt8 = BigDecimal.ZERO;
        System.out.println("8========" + new DecimalFormat("0.00").format(amt8));

        BigDecimal amt9 = new BigDecimal("0.121");
        System.out.println("9========" + new DecimalFormat("0.00").format(amt9));

        BigDecimal amt7 = null;
        BigDecimal amt = Optional.ofNullable(amt7).orElse(BigDecimal.ZERO);
        System.out.println("7========" + new DecimalFormat("0.00").format(amt));

        BigDecimal amt99 = Optional.ofNullable(amt9).orElse(BigDecimal.ZERO);
        System.out.println("99========" + new DecimalFormat("0.00").format(amt99));

        BigDecimal amt10 = new BigDecimal("0.01");
        System.out.println("10========" + new DecimalFormat("0.00").format(amt10));
    }

}
