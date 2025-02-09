package ncl.yujiaqi.dynamic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ncl.yujiaqi.system.domain.entity.BaseEntity;

/**
 * post image table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "dyn_post_img", autoResultMap = true)
@ApiModel(value = "DynPostImg", description = "post image table")
public class PostImg extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "post id")
    private Long postId;

    @ApiModelProperty(value = "file id")
    private Long fileId;

}