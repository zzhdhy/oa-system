package com.zzh.oa_system.common;

import java.util.Base64;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/5/5 1:59
 * description: Base64 加密 解密
 * 激活邮件的时候 为邮箱地址 code验证码 进行加密
 * 当回传回来后 进行邮箱地址 和 code 的解密
 */
public class Base64Util {
    //加密
    public static String encode(String msg) {
        return Base64.getEncoder().encodeToString(msg.getBytes());
    }

    //解密
    public static String decode(String msg) {
        return new String(Base64.getDecoder().decode(msg));
    }
}
