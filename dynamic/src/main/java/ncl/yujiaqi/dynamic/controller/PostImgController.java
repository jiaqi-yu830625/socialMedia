package ncl.yujiaqi.dynamic.controller;

import io.swagger.annotations.Api;
import ncl.yujiaqi.dynamic.domain.entity.PostImgData;
import ncl.yujiaqi.dynamic.service.PostImgDataService;
import ncl.yujiaqi.system.common.enums.ResultEnum;
import ncl.yujiaqi.system.common.result.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * @Author yujiaqi
 * @Since 06/02/2025
 */
@RestController
@Api(value = "post image table", tags = "post image table")
@RequestMapping("/postImg")
public class PostImgController {

    @Resource
    private PostImgDataService postImgDataService;

    @PostMapping("/upload")
    public R<PostImgData> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            PostImgData img = new PostImgData();
            img.setFileName(file.getOriginalFilename())
                    .setFileData(file.getBytes())
                    .setCreateTime(new Date());
            return R.success(postImgDataService.add(img));
        } catch (IOException e) {
            e.printStackTrace();
            return R.fail(ResultEnum.FILE_UPLOAD_FAIL, "File upload failed!");
        }
    }
}
