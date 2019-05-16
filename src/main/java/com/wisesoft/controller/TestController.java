package com.wisesoft.controller;

import com.wisesoft.bigdata.entity.InterfaceInfoEntity;
import com.wisesoft.bigdata.service.InterfaceInfoService;
import com.wisesoft.plat.entity.TSysUserEntity;
import com.wisesoft.plat.service.TSysUserService;
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
    InterfaceInfoService interfaceInfoService;
    @Autowired
    TSysUserService tSysUserService;


    @GetMapping("/testTrans")
    @Transactional
    public String testTrans() {

        InterfaceInfoEntity interfaceInfoEntity = new InterfaceInfoEntity();
        interfaceInfoEntity.setId(UuidUtil.generateUUID());
        interfaceInfoService.save(interfaceInfoEntity);

        TSysUserEntity tSysUserEntity = new TSysUserEntity();
        tSysUserEntity.setId(UuidUtil.generateUUID());
        tSysUserEntity.setUserName("test");
        tSysUserEntity.setLoginName("test");
        tSysUserEntity.setPasswd("test");
        tSysUserService.save(tSysUserEntity);
        int i =1/0;

        return "OK";

    }

    @GetMapping("/testTrans1")
    @Transactional
    public String testTrans1() {

        InterfaceInfoEntity interfaceInfoEntity = new InterfaceInfoEntity();
        interfaceInfoEntity.setId(UuidUtil.generateUUID());
        interfaceInfoService.save(interfaceInfoEntity);
        return "ok";

    }

    @GetMapping("/testTrans2")
    @Transactional
    public String testTrans2() {

        TSysUserEntity tSysUserEntity = new TSysUserEntity();
        tSysUserEntity.setId(UuidUtil.generateUUID());
        tSysUserEntity.setUserName("test");
        tSysUserEntity.setLoginName("test");
        tSysUserEntity.setPasswd("test");
        tSysUserService.save(tSysUserEntity);
        return "ok";

    }
}