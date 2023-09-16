package com.sky.config;

/* *
 * ClassName: OssConfiguration
 * Package:com.sky.config
 * Description:配置类，用于创建AliOssUtil对象
 * @Author Alan
 * @Create 2023/9/15-17:28
 * @Version 1.0
 */

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j

public class OssConfiguration {
    @Bean
    @ConditionalOnMissingBean //条件对象，没有此bean才创建bean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
        log.info("开始创建阿里云文件上传工具类对象{}",aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }
}