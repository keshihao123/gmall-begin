package com.keshihao.gmall.service;

import com.keshihao.gmall.bean.UmsMember;
import com.keshihao.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-09-10-19:43
 */
public interface UserService {
    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}
