package com.keshihao.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.keshihao.gmall.bean.PmsBaseCatalog1;
import com.keshihao.gmall.bean.PmsBaseCatalog2;
import com.keshihao.gmall.bean.PmsBaseCatalog3;
import com.keshihao.gmall.service.CatalogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-10-10-11:30
 */

@Controller
@CrossOrigin
public class CatalogController {

    @Reference
    CatalogService catalogService;

    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1(){

        List<PmsBaseCatalog1> catalog1 = catalogService.getCatalog1();
        return catalog1;
    }

    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id){

        List<PmsBaseCatalog2> catalog2s = catalogService.getCatalog2(catalog1Id);
        return catalog2s;
    }

    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id){

        List<PmsBaseCatalog3> catalog3s = catalogService.getCatalog3(catalog2Id);
        return catalog3s;
    }

}
