package ncl.yujiaqi.system.common.result;

import lombok.Getter;
import lombok.ToString;
import ncl.yujiaqi.system.common.enums.ResultEnum;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * @param <T>
 * @author drools
 */
@ToString
@Getter
public final class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String SUCCESS_CODE = "20000";
    public static final String SUCCESS_MESSAGE = "success";
    public static final String EXCEPTION_CODE = "5000";
    public static final String EXCEPTION_MESSAGE = "exception";

    private T data;
    private String code;
    private String msg;

    private R() {
        super();
    }

    private R(T data) {
        super();
        this.data = data;
    }

    private R(ResultEnum resultEnum) {
        super();
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

    public R(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public R(T data, String code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    private R(ResultEnum resultEnum, String msg) {
        super();
        this.code = resultEnum.getCode();
        this.msg = msg;
    }

    public R(ResultEnum resultEnum, String msg, T data) {
        super();
        this.code = resultEnum.getCode();
        this.msg = msg;
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> R<T> success() {
        return new R<>(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public static <T> R<T> success(T t) {
        return new R<>(t, SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public static <T> R<T> fail(ResultEnum resultEnum) {
        return new R<>(resultEnum);
    }

    public static <T> R<T> fail(ResultEnum resultEnum, String message) {
        return new R<>(resultEnum, message);
    }

    public static <T> R<T> build(String code, String message) {
        return new R<>(code, message);
    }

    public static <T> R<T> fail(BindingResult result) {
        return fail(ResultEnum.PARAM_VERIFY_FAIL, result.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(",")));
    }

    public static <T> R<T> fail(ResultEnum resultEnum, String message, T t) {
        return new R(resultEnum, message, t);
    }
}
