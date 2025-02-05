package ncl.yujiaqi.dynamic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import ncl.yujiaqi.system.domain.entity.BaseEntity;

/**
 * user follows table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "dyn_user_follows", autoResultMap = true)
@ApiModel(value = "DynUserFollows", description = "user follows table")
public class UserFollows extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "user id")
    private Long userId;

    @ApiModelProperty(value = "follower id")
    private Long followerId;

    @ApiModelProperty(value = "followee id")
    private Long followeeId;


}