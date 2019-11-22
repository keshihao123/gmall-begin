package com.keshihao.gmall.manage.mapper;

import com.keshihao.gmall.bean.PmsSkuInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-10-19-13:52
 */
public interface PmsSkuInfoMapper extends Mapper<PmsSkuInfo> {
    List<PmsSkuInfo> selectSkuSaleAttrValueListBySpu(String productId);
}
