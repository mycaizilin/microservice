package com.example.demo.goodservice1.service.impl;

/**
 * @Author: CaiZiLin
 * @Date: 2019/4/29 14:44
 */

import com.example.demo.common.model.GoodInfo;
import com.example.demo.common.service.GoodService;
import com.example.demo.common.service.PriceService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    private final String SERVICENAME="goodservice1";

    @Reference(check=false)
    PriceService priceService;

    @Override
    public List<GoodInfo> getGoodList(String name) {
        List<GoodInfo>  goodInfoList=new ArrayList<>();
        GoodInfo goodInfo=new GoodInfo();
        goodInfo.setName(name);
        goodInfo.setDescription(SERVICENAME);

        Integer price=  priceService.getGoodPrice(name);
        goodInfo.setPrice(price);

        goodInfoList.add(goodInfo);
        return goodInfoList;
    }
}