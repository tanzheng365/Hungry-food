package com.sky.controller.admin;

import com.sky.config.RedisConfiguration;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/* *
 * ClassName: ShopController
 * Package:com.sky.controller.admin
 * Description:
 * @Author Alan
 * @Create 2023/9/19-17:33
 * @Version 1.0
 */
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j

public class ShopController {

    public static final  String KEY="SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 设置店铺状态
     * @param status
     * @return
     */
    @ApiOperation(value = "店铺状态设置")
    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置店铺营业状态为：{}",status == 1?"营业中" : "打烊中" );
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("查询店铺运营状态")
    /**
     * 查询店铺运营状态
     */
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取店铺的营业状态：{}",status ==1?"店铺运营中":"店铺打样中");

        return Result.success(status);
    }
}
