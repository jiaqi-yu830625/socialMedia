package ncl.yujiaqi.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * user table
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "user", autoResultMap = true)
@ApiModel(value = "User", description = "user table")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "username")
    private String username;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "password；encode")
    private String password;

    @ApiModelProperty(value = "phone")
    private String phone;

    @ApiModelProperty(value = "avatar")
    private String avatar;

    @ApiModelProperty(value = "gender；0：secret，1：male；2: female")
    private Boolean gender;

    @ApiModelProperty(value = "birthday")
    private Date birthday;

    @ApiModelProperty(value = "default: true")
    private Boolean isActive;

    @ApiModelProperty(value = "personal bio")
    private String bio;


}