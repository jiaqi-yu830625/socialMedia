package ncl.yujiaqi.dynamic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import ncl.yujiaqi.dynamic.domain.dto.PostImgDTO;
import ncl.yujiaqi.dynamic.domain.entity.PostImg;
import ncl.yujiaqi.dynamic.mapper.PostImgMapper;
import ncl.yujiaqi.dynamic.service.PostImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * post image table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Service
public class PostImgServiceImpl extends ServiceImpl<PostImgMapper, PostImg> implements PostImgService {


    @Override
    public PostImg add(PostImg postImg) {
        baseMapper.insert(postImg);
        return postImg;
    }

    @Override
    public PostImg update(PostImg postImg) {
        baseMapper.updateById(postImg);
        return postImg;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    @Transactional
    public List<PostImg> addList(Long postId, List<Long> imageDataIds) {
        List<PostImg> imgs = new ArrayList<>(imageDataIds.size());
        imageDataIds.forEach(dataId -> {
            PostImg postImg = new PostImg(postId, dataId);
            add(postImg);
            imgs.add(postImg);
        });
        return imgs;
    }

    @Override
    public List<PostImg> selectByPostIds(List<Long> postIds) {
        return baseMapper.selectByPostIds(postIds);
    }

    @Override
    public List<PostImg> selectByPostId(Long postId) {
        return baseMapper.selectByPostId(postId);

    }

    @Override
    public void deleteByPostId(Long postId) {
        baseMapper.deleteByPostId(postId);
    }

    @Override
    public PostImgDTO convertToDto(PostImg img) {
        PostImgDTO postImgDTO = new PostImgDTO();
        BeanUtil.copyProperties(img, postImgDTO);
        return postImgDTO;
    }
}
