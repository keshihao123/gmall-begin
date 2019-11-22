package com.keshihao.gmall.manage.mapper;

import com.keshihao.gmall.bean.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-10-16-21:24
 */
public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {
    List<PmsProductSaleAttr> selectspuSaleAttrListCheckBySku(@Param("productId") String productId,@Param("skuId") String skuId);
}
