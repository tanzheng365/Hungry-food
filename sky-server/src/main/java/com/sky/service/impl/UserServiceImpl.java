package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/* *
 * ClassName: UserServiceImpl
 * Package:com.sky.service.impl
 * Description:
 * @Author Alan
 * @Create 2023/9/21-16:51
 * @Version 1.0
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */

    public static final String WX_LOGIN="https://api.weixin.qq.com/sns/jscode2session";
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeChatProperties weChatProperties;

    public User wxLogin(UserLoginDTO userLoginDTO) {

        String openid = getopenId(userLoginDTO.getCode());

        //判断openid是否为空，如果为空，则抛出业务异常，
        if (openid==null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        //不为空，判断当前用户是否为新用户
        User user = userMapper.getByOpenid(openid);
        //如果是新用户，自动完成注册
        if (user == null){
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
        userMapper.insert(user);
        }

        //返回用户对象
        return user;
    }

    /**
     * 调用微信接口服务，获得当前微信用户的openid
     * @param code
     * @return
     */
    private String getopenId(String code){

        Map<String, String> map=new HashMap<>();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("authorization_code","authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map);


        //JSON对象，fastjson包下的。
        JSONObject jsonObject = JSON.parseObject(json);
//        jsonObject.getString(要获取的名称)
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
