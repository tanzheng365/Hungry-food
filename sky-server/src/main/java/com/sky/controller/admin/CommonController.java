package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/* *
 * ClassName: CommonController
 * Package:com.sky.controller.admin
 * Description: 通用接口
 * @Author Alan
 * @Create 2023/9/15-17:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController  {

    @Autowired
    private AliOssUtil aliOssUtil;


    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){//(MultipartFile "文件名")--》文件名应该与前端文件名保持一致，才可以正常获取到文件
      log.info("上传文件",file);
        try {
            String originalFilename = file.getOriginalFilename();//获取其原始文件名
            //截取后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            //构造新的文件名称
            String objectname = UUID.randomUUID().toString() + extension;

            //文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectname);
            return Result.success(filePath);

        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
