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
 * @Author yujiaqi
 * @Since 06/02/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "dyn_file", autoResultMap = true)
@ApiModel(value = "DynFile", description = "image file table")
public class PostImgData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "file name")
    private String fileName;

    @ApiModelProperty(value = "file data")
    private byte[] fileData;

}
