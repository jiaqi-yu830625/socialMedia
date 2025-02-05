package ncl.yujiaqi.dynamic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import ncl.yujiaqi.system.domain.entity.BaseEntity;

/**
 * post table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "dyn_post", autoResultMap = true)
@ApiModel(value = "DynPost", description = "post table")
public class Post extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "user id")
    private Long userId;

    @ApiModelProperty(value = "post content")
    private String content;

    @ApiModelProperty(value = "public,friends,private")
    private String visibility;


}