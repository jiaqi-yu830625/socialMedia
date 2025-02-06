package ncl.yujiaqi.dynamic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ncl.yujiaqi.dynamic.domain.entity.PostComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * post comment table Mapper 接口
 * </p>
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface PostCommentMapper extends BaseMapper<PostComment> {

    List<PostComment> selectByPostIds(@Param("postIds") List<Long> postIds);

    List<PostComment> selectByPostId(@Param("postId") Long postId);

    void deleteByPostId(@Param("postId") Long postId);
}
