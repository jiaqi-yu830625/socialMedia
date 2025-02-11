package ncl.yujiaqi.interaction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ncl.yujiaqi.dynamic.service.PostLikesService;
import ncl.yujiaqi.dynamic.domain.dto.LikeDTO;
import ncl.yujiaqi.system.common.result.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * post likes table controller
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@RestController
@Api(value = "post likes table", tags = "post likes table")
@RequestMapping("/likes")
public class PostLikesController {

    @Resource
    private PostLikesService postLikesService;

    @PostMapping(value = "/like/{postId}")
    @ApiOperation(tags = "like post", value = "like post")
    public R likePostByPostId(@PathVariable("postId") Long postId) {
        return R.success(postLikesService.addByPostId(postId));
    }

    @DeleteMapping(value = "/like/{postId}")
    @ApiOperation(tags = "unlike post", value = "unlike post")
    public R unlikePostByPostId(@PathVariable("postId") Long postId) {
        postLikesService.deleteByUserAndPost(postId);
        return R.success();
    }

    @GetMapping(value = "/countLikesNum/{userId}")
    public R<LikeDTO> getLikeNumber(@PathVariable("userId") Long userId){
        return R.success(postLikesService.getLikeNumber(userId));

    }

}
