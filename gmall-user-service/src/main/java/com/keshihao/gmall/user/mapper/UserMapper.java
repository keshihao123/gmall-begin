package com.keshihao.gmall.user.mapper;

import com.keshihao.gmall.bean.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-09-10-19:52
 */
public interface UserMapper extends Mapper<UmsMember> {
    List<UmsMember> selectAllUser();
}
