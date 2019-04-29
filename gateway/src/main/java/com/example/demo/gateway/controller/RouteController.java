package com.example.demo.gateway.controller;


import com.example.demo.common.model.ParameterModel;
import com.example.demo.common.model.ResultModel;
import com.example.demo.common.model.ServiceModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: CaiZiLin
 * @Date: 2019/4/28 18:29
 */
@RestController
public class RouteController {

    private final static List<ServiceModel> serviceModels = new ArrayList<>();
    private final static Map<String,GenericService>  genericServiceMap=new HashMap<>();
    private final static String GATEWAYNAME="gateway";
    
    static {
        ParameterModel parameterModel = new ParameterModel();
        parameterModel.setName("name");
        parameterModel.setType("java.lang.String");
        List<ParameterModel> parameterModelList = new ArrayList<>();
        parameterModelList.add(parameterModel);

        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setApiName("api.service.goodservice");
        serviceModel.setServiceName("com.example.demo.common.service.GoodService");
        serviceModel.setMethodName("getGoodList");

        serviceModel.setParameterModels(parameterModelList);
        serviceModels.add(serviceModel);
    }

    @RequestMapping(value = "/route", method = RequestMethod.GET)
    public ResultModel execute(@RequestParam String api, @RequestParam String data) {

        Optional<ServiceModel> serviceModelOptional = serviceModels.stream().filter(x -> x.getApiName().equals(api)).findFirst();
        ResultModel resultModel=new ResultModel();

        if (!serviceModelOptional.isPresent()) {
            resultModel.setDescription("api不存在");
        }
        ServiceModel serviceModel=serviceModelOptional.get();

        GenericService genericService= genericServiceMap.get(api);
        if(genericService==null){
            ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
            reference.setInterface(serviceModel.getServiceName());
            reference.setGeneric(true);
            genericService = reference.get();
            genericServiceMap.put(api,genericService);
        }
        Object result = genericService.$invoke(serviceModel.getMethodName(),getTypeList(serviceModel).toArray(new String[]{}), dataToValueList(serviceModel,data).toArray());

        resultModel.setData(result);
        resultModel.setDescription(GATEWAYNAME);
        return resultModel;
    }

    /**
     * 获取参数类型列表
     * @param serviceModel
     * @return
     */
    private List<String> getTypeList(ServiceModel serviceModel) {
        List<ParameterModel> parameterModelList = serviceModel.getParameterModels();
        if (CollectionUtils.isEmpty(parameterModelList)) {
            return null;
        }
        return parameterModelList.stream().map(x -> x.getType()).collect(Collectors.toList());
    }

    /**
     * 获取data中的值列表
     * @param serviceModel
     * @param data
     * @return
     */
    private List<Object> dataToValueList(ServiceModel serviceModel, String data) {
        Map<String, Object> parameterMap = jsonToMap(data);
        List<ParameterModel> parameterModelList = serviceModel.getParameterModels();

        if (CollectionUtils.isEmpty(parameterModelList)) {
            return null;
        }
        List<Object> valueList = new ArrayList<>();

        parameterModelList.stream().forEach(x -> {
            valueList.add(parameterMap.get(x.getName()));
        });
        return valueList;
    }

    /**
     * 将map格式的string转成map对象
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Map.class);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

}
