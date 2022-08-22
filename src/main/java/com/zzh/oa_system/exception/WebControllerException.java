package com.zzh.oa_system.exception;

import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 14:38
 * description:全局异常处理
 */
@ControllerAdvice
public class WebControllerException {
    /**
     * 全局异常捕捉处理
     *
     * @param ex
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public void errorHandler(Exception ex) {
        System.out.println("全局异常处理：" + ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public void errorHandler(ServiceException ex) {
        System.out.println("业务异常处理：" + ex.getMessage());
    }

}
