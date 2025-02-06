package ncl.yujiaqi.dynamic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ncl.yujiaqi.dynamic.domain.dto.PostDTO;
import ncl.yujiaqi.dynamic.service.PostService;
import ncl.yujiaqi.system.common.result.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * post table controller
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@RestController
@Api(value = "post table", tags = "post table")
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;

    @PostMapping(value = "/post")
    @ApiOperation(tags = "post", value = "post")
    public R<PostDTO> post(@Validated @RequestBody PostDTO postDTO, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail(result);
        }
        return R.success(postService.add(postDTO));
    }

    @GetMapping(value = "/getByUserId/{userId}")
    @ApiOperation(tags = "getByUserId", value = "get posts by userId")
    public R<List<PostDTO>> pageByUserId(@PathParam("userId") Long userId) {
        return R.success(postService.pageByUserId(userId));
    }

    @GetMapping(value = "/getById/{id}")
    @ApiOperation(tags = "getById", value = "get posts by id")
    public R<PostDTO> getById(@PathParam("id") Long id){
        return R.success(postService.selectById(id));
    }

}
