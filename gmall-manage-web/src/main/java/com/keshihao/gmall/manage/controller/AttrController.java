package com.keshihao.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.keshihao.gmall.bean.PmsBaseAttrInfo;
import com.keshihao.gmall.bean.PmsBaseAttrValue;
import com.keshihao.gmall.bean.PmsBaseSaleAttr;
import com.keshihao.gmall.bean.PmsProductSaleAttr;
import com.keshihao.gmall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-10-11-16:58
 */

@Controller
@CrossOrigin
public class AttrController {

    @Reference
    AttrService attrService;

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id){

        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.attrInfoList(catalog3Id);

        return pmsBaseAttrInfos;
    }

    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String attrInfoList(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){

        String success = attrService.saveAttrInfo(pmsBaseAttrInfo);

        return "success";
    }

    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){

        List<PmsBaseAttrValue> pmsBaseAttrValues = attrService.getAttrValueList(attrId);

        return pmsBaseAttrValues;
    }

    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = attrService.baseSaleAttrList();
        return pmsBaseSaleAttrs;
    }

}
