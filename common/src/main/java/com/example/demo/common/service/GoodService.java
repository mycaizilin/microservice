package com.example.demo.common.service;

import com.example.demo.common.model.GoodInfo;

import java.util.List;

/**
 * @Author: CaiZiLin
 * @Date: 2019/4/29 14:26
 */
public interface GoodService {
    List<GoodInfo> getGoodList(String name);
}
