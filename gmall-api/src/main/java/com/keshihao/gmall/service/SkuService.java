package com.keshihao.gmall.service;

import com.keshihao.gmall.bean.PmsSkuInfo;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-10-19-13:48
 */
public interface SkuService {
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuById(String skuId);

    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);
}
