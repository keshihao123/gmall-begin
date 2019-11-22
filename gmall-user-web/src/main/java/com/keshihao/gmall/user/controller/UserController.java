package com.keshihao.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.keshihao.gmall.bean.UmsMember;
import com.keshihao.gmall.bean.UmsMemberReceiveAddress;
import com.keshihao.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-09-10-19:42
 */

@Controller
public class UserController {

    @Reference
    UserService userService;


    @RequestMapping("getReceiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId( String memberId){
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = userService.getReceiveAddressByMemberId(memberId);
        return umsMemberReceiveAddresses;
    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser(){
        List<UmsMember> umsMembers = userService.getAllUser();
        return umsMembers;
    }


    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "Hello User";
    }
}
