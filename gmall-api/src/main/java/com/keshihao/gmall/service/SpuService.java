package com.keshihao.gmall.service;

import com.keshihao.gmall.bean.PmsProductImage;
import com.keshihao.gmall.bean.PmsProductInfo;
import com.keshihao.gmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-10-14-16:42
 */
public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId,String skuId);
}
