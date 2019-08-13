package com.wisesoft.controller;

import com.wisesoft.bigdata.entity.UserBakEntity;
import com.wisesoft.bigdata.service.UserBakService;
import com.wisesoft.plat.entity.UserEntity;
import com.wisesoft.plat.service.UserService;
import com.wisesoft.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多文件加载的例子使用的验证接口
 * <p>
 * blog: http://blog.didispace.com/spring-cloud-alibaba-nacos-config-3/
 */
@Slf4j
@RestController
public class TestController {



    @Autowired
    UserService userService;
    @Autowired
    UserBakService userBakService;


    @GetMapping("/testTrans")
    @Transactional
    public String testTrans() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UuidUtil.generateUUID());
        userEntity.setUserName("test0");
        userEntity.setLoginName("test0");
        userEntity.setPasswd("test0");
        userService.save(userEntity);

        UserBakEntity userBakEntity = new UserBakEntity();
        userBakEntity.setId(UuidUtil.generateUUID());
        userBakEntity.setUserName("test0");
        userBakEntity.setLoginName("test0");
        userBakEntity.setPasswd("test0");
        userBakService.save(userBakEntity);
        int i =1/0;

        return "OK";

    }

    @GetMapping("/testTrans1")
    @Transactional
    public String testTrans1() {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(UuidUtil.generateUUID());
        userEntity.setUserName("test1");
        userEntity.setLoginName("test1");
        userEntity.setPasswd("test1");
        userService.save(userEntity);
//        int i =1/0;
        return "ok";

    }

    @GetMapping("/testTrans2")
    @Transactional
    public String testTrans2() {
        UserBakEntity userBakEntity = new UserBakEntity();
        userBakEntity.setId(UuidUtil.generateUUID());
        userBakEntity.setUserName("test2");
        userBakEntity.setLoginName("test2");
        userBakEntity.setPasswd("test2");
        userBakService.save(userBakEntity);
//        int i =1/0;
        return "ok";

    }
}