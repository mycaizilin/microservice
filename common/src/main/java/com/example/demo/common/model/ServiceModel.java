package com.example.demo.common.model;

import java.util.List;

/**
 * @Author: CaiZiLin
 * @Date: 2019/4/28 20:29
 */
public class ServiceModel {

    private String apiName;
    private String serviceName;
    private String methodName;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    private List<ParameterModel>  parameterModels;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<ParameterModel> getParameterModels() {
        return parameterModels;
    }

    public void setParameterModels(List<ParameterModel> parameterModels) {
        this.parameterModels = parameterModels;
    }




}

