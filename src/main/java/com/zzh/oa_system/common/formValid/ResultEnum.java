package com.zzh.oa_system.common.formValid;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 14:45
 * description:异常结果抛出  枚举类
 */
public enum ResultEnum {
    //失败状态码
    ERROR(2, "验证失败"),
    //成功状态码
    SUCCESS(200, "成功"),
    //找不到参数 状态码
    NONETYPE(1, "找不到参数"),
    ;

    private Integer code;
    private String message;


    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
