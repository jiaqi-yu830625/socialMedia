package ncl.yujiaqi.dynamic.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ncl.yujiaqi.dynamic.domain.entity.PostLikes;
import ncl.yujiaqi.system.domain.entity.BaseEntity;
import ncl.yujiaqi.system.domain.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author yujiaqi
 * @Since 05/02/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PostDTO extends BaseEntity {
    @ApiModelProperty(value = "user id")
    @NotNull(message = "user id cannot be null!")
    private Long userId;

    @ApiModelProperty(value = "post content")
    @NotEmpty(message = "can't post empty content!")
    private String content;

    @ApiModelProperty(value = "post images")
    private List<Long> imageDataIdList;

    @ApiModelProperty(value = "public,friends,private")
    private String visibility;

    private User user;
    private Integer likesNumber;
    private List<PostLikes> postLikes;
    private List<PostCommentDTO> postComments;
    private List<CommentUserDTO> commentUsers;

    public PostDTO(Long id, Long userId, String content, List<Long> imageDataIdList, Date createTime) {
        super.setId(id);
        super.setCreateTime(createTime);
        this.userId = userId;
        this.content = content;
        this.imageDataIdList = imageDataIdList;
    }
}
