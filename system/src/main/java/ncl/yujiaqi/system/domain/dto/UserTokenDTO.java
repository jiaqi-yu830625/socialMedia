package ncl.yujiaqi.system.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ncl.yujiaqi.system.domain.entity.User;

/**
 * user table
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "UserTokenDTO", description = "user table")
public class UserTokenDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "user")
    private User user;

    @ApiModelProperty(value = "token")
    private String token;


}