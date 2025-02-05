package ncl.yujiaqi.system.common.enums;

import lombok.Getter;
import ncl.yujiaqi.system.common.result.R;

import java.util.Arrays;

/**
 * 返回结果的枚举类, 业务提示
 *
 * @author leo
 */
@Getter
public enum ResultEnum {

    BUSINESS_PROCESSING_FAILED(R.EXCEPTION_CODE, R.EXCEPTION_MESSAGE),
    LOGIN_PWD_WRONG("40101", "login fail, wrong email/phone or password"),
    LOGIN_USER_DISABLED("40301", "user account is disabled"),
    DATA_NOT("40404", "no relative data"),
    LOGIN_VERIFY_FAIL("2", "login verify fail"),
    PARAM_VERIFY_FAIL("3", "param verify fail"),
    AUTH_FAILED("4", "Invalid token"),
    PARAM_NOT_FOUND("12", "param not found"),
    PARAM_FORMAT_ERROR("16", "param format error"),
    DATA_NOT_FOUND("18", "data not found"),
    ILLEGAL_OPERATION("19", "illegal operation"),
    DATA_REPEAT("20", "data repeat"),
    DUPLICATE_USERNAME("39", "duplicate account"),
    USER_NOT_FOUND("40", "user not found")
    ;
    private final String code;

    private final String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultEnum getEnum(String message) {
        return Arrays.stream(ResultEnum.values()).filter(resultEnum -> resultEnum.getMessage().equals(message)).findAny().orElse(null);
    }
}
