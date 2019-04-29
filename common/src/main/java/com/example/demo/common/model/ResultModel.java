package com.example.demo.common.model;

import java.io.Serializable;

/**
 * @Author: CaiZiLin
 * @Date: 2019/4/29 15:33
 */
public class ResultModel implements Serializable {

    private Object data;
    private String description;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
