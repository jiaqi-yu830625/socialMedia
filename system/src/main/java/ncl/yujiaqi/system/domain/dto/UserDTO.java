package ncl.yujiaqi.system.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import ncl.yujiaqi.system.domain.entity.BaseEntity;
import ncl.yujiaqi.system.domain.entity.Role;

import java.util.Date;
import java.util.List;

/**
 * @Author yujiaqi
 * @Since 04/02/2025
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserDTO extends BaseEntity {
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

    private List<Role> roles;
}
