package ncl.yujiaqi.dynamic.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yujiaqi
 * @Since 10/02/2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long postId;
    private Long sourceId;
    private String comment;
}
