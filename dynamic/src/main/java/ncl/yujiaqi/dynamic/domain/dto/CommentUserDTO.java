package ncl.yujiaqi.dynamic.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ncl.yujiaqi.system.domain.entity.BaseEntity;

/**
 * @Author yujiaqi
 * @Since 06/02/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentUserDTO extends BaseEntity {
    @ApiModelProperty(value = "username")
    private String username;

    @ApiModelProperty(value = "avatar")
    private String avatar;

    public CommentUserDTO(Long id, String username, String avatar) {
        super.setId(id);
        this.username = username;
        this.avatar = avatar;
    }
}
