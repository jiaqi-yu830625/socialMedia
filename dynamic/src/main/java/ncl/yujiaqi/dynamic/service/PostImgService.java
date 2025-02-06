package ncl.yujiaqi.dynamic.service;

import ncl.yujiaqi.dynamic.domain.dto.PostImgDTO;
import ncl.yujiaqi.dynamic.domain.entity.PostImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * post image table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface PostImgService extends IService<PostImg> {


    PostImg add(PostImg postImg);

    PostImg update(PostImg postImg);

    Boolean delete(Long id);

    List<PostImg> addList(Long postId, List<Long> imageDataIds);

    List<PostImg> selectByPostIds(List<Long> postIds);

    List<PostImg> selectByPostId(Long postId);

    void deleteByPostId(Long id);

    PostImgDTO convertToDto(PostImg img);
}
