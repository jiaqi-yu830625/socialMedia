package ncl.yujiaqi.dynamic.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ncl.yujiaqi.dynamic.domain.entity.PostComment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yujiaqi
 * @Since 05/02/2025
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class PostCommentDTO {

    private PostCommentUserDTO postComment;
    // storage children comment
    private List<PostCommentUserDTO> children = new ArrayList<>();
}
