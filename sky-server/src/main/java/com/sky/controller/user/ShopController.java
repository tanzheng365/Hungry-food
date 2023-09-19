package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController("userShopController")
@RequestMapping("/user/shop")
@Api(tags = "店铺相关接口")
@Slf4j

public class ShopController {

    public static final  String KEY="SHOP_STATUS";
    @Autowired
    private RedisTemplate redisTemplate;


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
