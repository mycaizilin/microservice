package com.example.demo.priceservice.service.impl;

import com.example.demo.common.service.PriceService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author: CaiZiLin
 * @Date: 2019/4/29 15:04
 */
@Service
public class PriceServiceImpl implements PriceService {
    @Override
    public Integer getGoodPrice(String name) {
        return 100;
    }
}
