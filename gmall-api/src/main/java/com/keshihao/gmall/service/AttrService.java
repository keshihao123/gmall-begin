package com.keshihao.gmall.service;

import com.keshihao.gmall.bean.PmsBaseAttrInfo;
import com.keshihao.gmall.bean.PmsBaseAttrValue;
import com.keshihao.gmall.bean.PmsBaseSaleAttr;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-10-11-17:53
 */
public interface AttrService {

    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);


    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();
}
