package com.keshihao.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.keshihao.gmall.bean.UmsMember;
import com.keshihao.gmall.bean.UmsMemberReceiveAddress;
import com.keshihao.gmall.service.UserService;
import com.keshihao.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.keshihao.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-09-10-19:47
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Override
    public List<UmsMember> getAllUser() {

        return userMapper.selectAll();
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {

        Example e = new Example(UmsMemberReceiveAddress.class);
        e.createCriteria().andEqualTo("memberId",memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses = umsMemberReceiveAddressMapper.selectByExample(e);
        return umsMemberReceiveAddresses;
    }
}
