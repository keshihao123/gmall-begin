package com.keshihao.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.keshihao.gmall.bean.PmsSkuInfo;
import com.keshihao.gmall.service.SkuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mr.Ke
 * @create 2019-10-18-23:56
 */

@Controller
@CrossOrigin
public class SkuController {

    @Reference
    SkuService skuService;

    @RequestMapping("saveSkuInfo")
    @ResponseBody
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo){
        // 将spuId封装给productId
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());
        skuService.saveSkuInfo(pmsSkuInfo);
        return "success";
    }
}
