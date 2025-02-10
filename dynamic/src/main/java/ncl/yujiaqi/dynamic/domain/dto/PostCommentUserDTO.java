package ncl.yujiaqi.dynamic.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ncl.yujiaqi.system.domain.entity.BaseEntity;

/**
 * @Author yujiaqi
 * @Since 10/02/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentUserDTO extends BaseEntity {
    @ApiModelProperty(value = "user id")
    private Long userId;

    @ApiModelProperty(value = "post id")
    private Long postId;

    @ApiModelProperty(value = "comment")
    private String comment;

    @ApiModelProperty(value = "parent comment id(use to comment the comment)")
    private Long parentCommentId;

    private CommentUserDTO commentUserDTO;
}
