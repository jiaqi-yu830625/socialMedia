package ncl.yujiaqi.interaction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ncl.yujiaqi.dynamic.domain.entity.PostComment;
import ncl.yujiaqi.dynamic.service.PostCommentService;
import ncl.yujiaqi.system.common.result.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * post comment table controller
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@RestController
@Api(value = "post comment table", tags = "post comment table")
@RequestMapping("/post_comment")
public class PostCommentController {

    @Resource
    private PostCommentService postCommentService;

    @PostMapping(value = "/comment")
    @ApiOperation(tags = "comment", value = "comment")
    public R<PostComment> addComment(Long postId, Long sourceId, String comment) {
        return R.success(postCommentService.addComment(postId, sourceId, comment));
    }

    @DeleteMapping(value = "/delById/{id}")
    @ApiOperation(tags = "comment", value = "delete by id")
    public R deleteById(@PathVariable("id") Long id) {
        postCommentService.delete(id);
        return R.success();
    }
}
