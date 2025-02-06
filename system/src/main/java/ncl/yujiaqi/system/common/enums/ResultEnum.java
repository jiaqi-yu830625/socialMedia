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
    LOGIN_VERIFY_FAIL("1", "login verify fail"),
    PARAM_VERIFY_FAIL("2", "param verify fail"),
    AUTH_FAILED("3", "Invalid token"),
    PARAM_NOT_FOUND("4", "param not found"),
    PARAM_FORMAT_ERROR("5", "param format error"),
    DATA_NOT_FOUND("6", "data not found"),
    ILLEGAL_OPERATION("7", "illegal operation"),
    DATA_REPEAT("8", "data repeat"),
    DUPLICATE_USERNAME("9", "duplicate account"),
    USER_NOT_FOUND("10", "user not found"),
    FILE_UPLOAD_FAIL("11","file upload fail")
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
