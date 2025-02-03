package ncl.yujiaqi.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * user role table
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "user_role", autoResultMap = true)
@ApiModel(value = "UserRole", description = "user role table")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "user id")
    private Long userId;

    @ApiModelProperty(value = "user id")
    private Long roleId;


}