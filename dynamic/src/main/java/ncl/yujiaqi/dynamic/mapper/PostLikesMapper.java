package ncl.yujiaqi.dynamic.mapper;

import ncl.yujiaqi.dynamic.domain.entity.PostLikes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * post likes table Mapper 接口
 * </p>
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface PostLikesMapper extends BaseMapper<PostLikes> {

    List<PostLikes> selectByPostIds(@Param("postIds") List<Long> postIds);

    List<PostLikes> selectByPostId(@Param("postId") Long postId);

    void deleteByPostId(@Param("postId") Long postId);
}
