package ncl.yujiaqi.dynamic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ncl.yujiaqi.dynamic.domain.entity.Post;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * post table Mapper 接口
 * </p>
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface PostMapper extends BaseMapper<Post> {

    List<Post> getByUserId(@Param("userId") Long userId);

    void delById(@Param("id") Long id);
}
