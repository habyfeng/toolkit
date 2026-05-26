package com.albert.toolkit.tcp;

import com.albert.data.DemoReqVo;

/**
 * @author losfoo
 * @since 2026-05-26
 */
public class DemoService {

    public void register(DemoReqVo reqVo) {
        System.out.println(String.format("开始注册: %s, %s", reqVo.name, reqVo.age));
    }
}
