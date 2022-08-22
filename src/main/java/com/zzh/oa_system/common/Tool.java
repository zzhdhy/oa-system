package com.zzh.oa_system.common;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 14:42
 * description:
 */
public class Tool {

    public static <T> T getBean(Class<T> clazz, HttpServletRequest request) {
        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        return wc.getBean(clazz);
    }
}
