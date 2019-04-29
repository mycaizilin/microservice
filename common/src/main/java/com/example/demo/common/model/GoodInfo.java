package com.example.demo.common.model;

import java.io.Serializable;

/**
 * @Author: CaiZiLin
 * @Date: 2019/4/29 14:28
 */
public class GoodInfo implements Serializable {
    private String name;
    private String description;
    private Integer price;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
