package ncl.yujiaqi.interaction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ncl.yujiaqi.dynamic.service.PostLikesService;
import ncl.yujiaqi.system.common.result.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

/**
 * post likes table controller
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@RestController
@Api(value = "post likes table", tags = "post likes table")
@RequestMapping("/post_likes")
public class PostLikesController {

    @Resource
    private PostLikesService postLikesService;

    @PostMapping(value = "/like/{postId}")
    @ApiOperation(tags = "like post", value = "like post")
    public R likePostByPostId(@PathParam("postId") Long postId) {
        return R.success(postLikesService.addByPostId(postId));
    }

    @PostMapping(value = "/unlike/{postId}")
    @ApiOperation(tags = "unlike post", value = "unlike post")
    public R unlikePostByPostId(@PathParam("postId") Long postId) {
        postLikesService.deleteByUserAndPost(postId);
        return R.success();
    }

}
