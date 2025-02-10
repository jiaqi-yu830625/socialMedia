package ncl.yujiaqi.dynamic.service;

import ncl.yujiaqi.dynamic.domain.dto.PostDTO;
import ncl.yujiaqi.dynamic.domain.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * post table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface PostService extends IService<Post>  {


    PostDTO add(PostDTO postDto);

    Post update(Post post);

    Boolean delete(Long id);

    List<PostDTO> pageByUserId(Long userId);

    PostDTO selectById(Long id);

    Boolean deleteById(Long id);

    List<PostDTO> pageAll();
}
