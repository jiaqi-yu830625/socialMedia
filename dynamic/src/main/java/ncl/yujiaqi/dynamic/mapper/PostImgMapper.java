package ncl.yujiaqi.dynamic.mapper;

import ncl.yujiaqi.dynamic.domain.entity.PostImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * post image table Mapper 接口
 * </p>
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface PostImgMapper extends BaseMapper<PostImg> {

    List<PostImg> selectByPostIds(@Param("postIds") List<Long> postIds);

    List<PostImg> selectByPostId(@Param("postId") Long postId);

    void deleteByPostId(@Param("postId") Long postId);
}
