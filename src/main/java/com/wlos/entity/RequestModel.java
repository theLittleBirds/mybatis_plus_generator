package com.wlos.entity;

import lombok.Data;

/**
 * @CreateBy zd
 * @CreateDate 2021/7/5 15:52
 */

@Data
public class RequestModel {

    /**
     * 服务名称，需要带上包路径，例如：com.wlos.controller.SchoolController
     */
    private String serviceName;

    /**
     * 方法名称，例如：add,update,delete,findListByPage,findById
     */
    private String methodName;

    /**
     * 分页参数：page
     */
    private Integer page;

    /**
     * 分页参数：pageCount
     */
    private Integer pageCount;

    /**
     * 实体名称，例如，School，Student
     */
    private String entityName;

    /**
     * 参数值：对象Json
     */
    private Object parameterValue;
}
