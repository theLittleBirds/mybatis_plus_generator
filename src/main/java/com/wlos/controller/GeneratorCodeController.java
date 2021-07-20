package com.wlos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlos.common_generator.CodeGenerator;
import com.wlos.entity.RequestModel;
import com.wlos.mybatis_plus_generator.MySQL8CodeGenerator;
import com.wlos.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Method;

/**
 * @CreateBy zd
 * @CreateDate 2021/6/29 11:31
 */

@RestController
@RequestMapping("/code")
public class GeneratorCodeController{

    @Autowired
    private MySQL8CodeGenerator mySQL8CodeGenerator;

    /**
     * MybatisPlus 根据freemarker模板，生成对应的文件
     * @param tableName 表名
     * @return
     */
    @PostMapping("/generatorcode")
    public String generatorCode(@RequestParam String tableName) {
        String[] tableNames = tableName.split(",");
        mySQL8CodeGenerator.generatorCode(tableNames);
        return "success";
    }

    /**
     * 根据freemarker模板，生成通用的数据查询模板
     * @return
     */
    @PostMapping("/commoncode")
    public String commonCode() {
        CodeGenerator.genCodeByCustomModelName();
        return "success";
    }

    /**
     * 适配器：分页查询 转发
     * @param requestModel
     * @return
     */
    @PostMapping("/adapterQueryList")
    public Object queryByPage(@RequestBody RequestModel requestModel){
        try {
            if(StringUtils.isEmpty(requestModel)){
                throw new Exception("参数为空");
            }
            Object resultBean = null;
            //从ApplicationContext中取出已创建好的的对象
            //不可直接反射创建serviceimpl对象，因为反射创建出来的对象无法实例化dao接口
            ApplicationContext applicationContext = BeanUtils.getApplicationContext();

            String serviceName = requestModel.getServiceName();
            Class beanService = Class.forName(serviceName);
            String methodName = requestModel.getMethodName();
            Integer page = requestModel.getPage();
            Integer pageCount = requestModel.getPageCount();
            Method method = beanService.getDeclaredMethod(methodName,new Class[]{Integer.class,Integer.class});
            resultBean =  method.invoke(applicationContext.getBean(beanService),page,pageCount);
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 适配器：单个查询/单个删除 转发
     * @param requestModel
     * @return
     */
    @PostMapping("/adapterQueryOrDeleteOne")
    public Object queryOrDeleteOne(@RequestBody RequestModel requestModel){
        try {
            if(StringUtils.isEmpty(requestModel)){
                throw new Exception("参数为空");
            }
            Object resultBean = null;
            ApplicationContext applicationContext = BeanUtils.getApplicationContext();

            String serviceName = requestModel.getServiceName();
            Class beanService = Class.forName(serviceName);
            String methodName = requestModel.getMethodName();
            Object objectId = requestModel.getParameterValue();
            if(objectId instanceof String){
                String id = String.valueOf(objectId);
                Method method = beanService.getDeclaredMethod(methodName,String.class);
                resultBean =  method.invoke(applicationContext.getBean(beanService),id);
            }else if(objectId instanceof Long){
                Long id = Long.valueOf(String.valueOf(objectId)) ;
                Method method = beanService.getDeclaredMethod(methodName,Long.class);
                resultBean =  method.invoke(applicationContext.getBean(beanService),id);
            }else if(objectId instanceof Integer){
                Integer id = Integer.parseInt(String.valueOf(objectId)) ;
                Method method = beanService.getDeclaredMethod(methodName,Integer.class);
                resultBean =  method.invoke(applicationContext.getBean(beanService),id);
            }
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 适配器：添加/删除 转发
     * @param requestModel
     * @return
     */
    @PostMapping("/adapterAddOrUpd")
    public Object addOrUpdate(@RequestBody RequestModel requestModel){
        try {
            if(StringUtils.isEmpty(requestModel)){
                throw new Exception("参数为空");
            }
            Object resultBean = null;
            ApplicationContext applicationContext = BeanUtils.getApplicationContext();
            //controller
            String serviceName = requestModel.getServiceName();
            Class beanService = Class.forName(serviceName);
            //entity
            String entityName = requestModel.getEntityName();
            Class entityClass = Class.forName(entityName);
            //method
            String methodName = requestModel.getMethodName();
            //entityValue
            Object parameterValue = requestModel.getParameterValue();

            ObjectMapper objectMapper = new ObjectMapper();
            Method method = beanService.getDeclaredMethod(methodName,entityClass);
            resultBean =  method.invoke(applicationContext.getBean(beanService),objectMapper.convertValue(parameterValue,entityClass));
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
