package com.keshihao.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.keshihao.gmall.bean.PmsSkuAttrValue;
import com.keshihao.gmall.bean.PmsSkuImage;
import com.keshihao.gmall.bean.PmsSkuInfo;
import com.keshihao.gmall.bean.PmsSkuSaleAttrValue;
import com.keshihao.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.keshihao.gmall.manage.mapper.PmsSkuImageMapper;
import com.keshihao.gmall.manage.mapper.PmsSkuInfoMapper;
import com.keshihao.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.keshihao.gmall.service.SkuService;
import com.keshihao.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author Mr.Ke
 * @create 2019-10-19-13:51
 */

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        // 插入SkuInfo
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
        // 插入平台属性关联
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }
        // 插入销售属性关联
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }
        // 插入图片信息
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }

    }

    public PmsSkuInfo getSkuByIdFromDb(String skuId){
        // 根据SkuId获取商品对象
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo skuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);

        // 根据SkuId获取SkuImageList
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(pmsSkuImage);

        skuInfo.setSkuImageList(pmsSkuImages);
        return skuInfo;
    }

    @Override
    public PmsSkuInfo getSkuById(String skuId) {

        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();

        // 链接缓存
        Jedis jedis = redisUtil.getJedis();

        // 查询缓存
        String skuKey = "sku:" + skuId + ":info";
        String skuJson = jedis.get(skuKey);
        // 把json字符串转化成Java类对象
        if(StringUtils.isNotBlank(skuJson)){ //if(skuJson!=null&&!skuJson.equals(""))
            pmsSkuInfo = JSON.parseObject(skuJson, PmsSkuInfo.class);
        }else {
            // 如果缓存中没有，查询mysql

            // 设置分布式锁
            String OK = jedis.set("sku:" + skuId + ":lock", "1", "nx", "px", 10);
            if(StringUtils.isNotBlank(OK) && OK.equals("OK")){
                // 设置成功，有权在10s的过期时间内访问数据库
                pmsSkuInfo = getSkuByIdFromDb(skuId);
                if(pmsSkuInfo!=null){
                    // mysql查询结果存入redis
                    jedis.set("sku:" + skuId + ":info",JSON.toJSONString(pmsSkuInfo));
                }else {
                    // 数据库中不存在该sku信息
                    // 为了防止缓存穿透，null或者空字符串值设置给redis
                    jedis.setex("sku:" + skuId + ":info",60,JSON.toJSONString(""));
                }

            }else {
                // 设置失败,自旋（该线程在睡眠几秒后，重新尝试访问本方法）
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 注意有没return的区别，没有return会在新的线程里重新访问该方法，return则在本线程中重新在访问一次该方法
                return getSkuById(skuId);
            }



        }

        jedis.close();
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {

        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectSkuSaleAttrValueListBySpu(productId);
        return pmsSkuInfos;

    }
}
