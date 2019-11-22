package com.keshihao.gmall.service;

import com.keshihao.gmall.bean.PmsBaseCatalog1;
import com.keshihao.gmall.bean.PmsBaseCatalog2;
import com.keshihao.gmall.bean.PmsBaseCatalog3;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-10-10-17:15
 */
public interface CatalogService {
    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
