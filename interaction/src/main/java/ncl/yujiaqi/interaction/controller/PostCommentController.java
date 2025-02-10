package ncl.yujiaqi.interaction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ncl.yujiaqi.dynamic.domain.entity.PostComment;
import ncl.yujiaqi.dynamic.service.PostCommentService;
import ncl.yujiaqi.dynamic.domain.dto.CommentDTO;
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
@RequestMapping("/comment")
public class PostCommentController {

    @Resource
    private PostCommentService postCommentService;

    @PostMapping(value = "/comment")
    @ApiOperation(tags = "comment", value = "comment")
    public R<PostComment> addComment(@RequestBody CommentDTO commentDTO) {
        return R.success(postCommentService.addComment(commentDTO));
    }

    @DeleteMapping(value = "/delById/{id}")
    @ApiOperation(tags = "comment", value = "delete by id")
    public R deleteById(@PathVariable("id") Long id) {
        postCommentService.delete(id);
        return R.success();
    }
}
