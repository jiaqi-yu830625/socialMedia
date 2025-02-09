package ncl.yujiaqi.dynamic.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ncl.yujiaqi.dynamic.domain.entity.PostImgData;
import ncl.yujiaqi.system.domain.entity.BaseEntity;

/**
 * @Author yujiaqi
 * @Since 06/02/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PostImgDTO extends BaseEntity {
    @ApiModelProperty(value = "post id")
    private Long postId;

    @ApiModelProperty(value = "file id")
    private Long fileId;

    private PostImgData imgData;

}
