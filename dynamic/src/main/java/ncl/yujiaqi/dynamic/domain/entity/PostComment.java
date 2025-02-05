package ncl.yujiaqi.dynamic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import ncl.yujiaqi.system.domain.entity.BaseEntity;

/**
 * post comment table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "dyn_post_comment", autoResultMap = true)
@ApiModel(value = "DynPostComment", description = "post comment table")
public class PostComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "user id")
    private Long userId;

    @ApiModelProperty(value = "post id")
    private Long postId;

    @ApiModelProperty(value = "comment")
    private String comment;

    @ApiModelProperty(value = "parent comment id(use to comment the comment)")
    private Long parentCommentId;


}