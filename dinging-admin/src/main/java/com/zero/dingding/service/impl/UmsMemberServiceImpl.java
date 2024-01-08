package com.zero.dingding.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zero.dingding.common.api.CommonResult;
import com.zero.dingding.common.service.RedisService;
import com.zero.dingding.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private RedisService redisService;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String telephone) {

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }

        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+telephone,sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+telephone,AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(),"获取验证码成功！");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {

        if (StrUtil.isBlank(authCode)){
            CommonResult.failed("请输入验证码");
        }

        String realAuthCode = (String) redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);

        if (authCode.equals(realAuthCode)){
            redisService.del(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
            return CommonResult.success(null, "验证码校验成功");
        }else {
            return CommonResult.failed("验证码不正确");
        }
    }
}
