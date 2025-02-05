package ncl.yujiaqi.system.common.exception;

import lombok.Getter;
import ncl.yujiaqi.system.common.enums.ResultEnum;
import ncl.yujiaqi.system.common.result.R;

/**
 * exception
 *
 * @author yujiaqi
 */
@Getter
public class SMException extends RuntimeException {

    private final String code;

    private SMException(ResultEnum result) {
        super(result.getMessage());
        this.code = result.getCode();
    }

    private SMException(ResultEnum result, String message) {
        super(message);
        this.code = result.getCode();
    }

    private SMException(String code, String message) {
        super(message);
        this.code = code;
    }

    public static SMException build(ResultEnum result) {
        return new SMException(result);
    }

    public static SMException build(ResultEnum result, String message) {
        return new SMException(result, message);
    }

    public static SMException build(String code, String message) {
        return new SMException(code, message);
    }

    public static SMException build(String message) {
        return new SMException(R.EXCEPTION_CODE, message);
    }
}
