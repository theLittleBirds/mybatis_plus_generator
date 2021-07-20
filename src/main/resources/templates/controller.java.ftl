package ${package.Controller};

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */

@RestController
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??>:${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>public class ${table.controllerName} extends ${superControllerClass}{
<#else>public class ${table.controllerName} {
</#if>

    @Resource
    private ${table.serviceName} ${(table.serviceName?substring(1))?uncap_first};


    @ApiOperation(value = "新增${table.comment!}")
    @PostMapping()
    public int add(@RequestBody ${entity} ${entity?uncap_first}){
        return ${(table.serviceName?substring(1))?uncap_first}.add(${entity?uncap_first});
    }

    @ApiOperation(value = "删除${table.comment!}")
    @DeleteMapping("{id}")
    <#list table.fields as field>
        <#if field.keyFlag>
    public int delete(@PathVariable("id") ${field.propertyType} id){
        </#if>
    </#list>
        return ${(table.serviceName?substring(1))?uncap_first}.delete(id);
    }

    @ApiOperation(value = "更新${table.comment!}")
    @PutMapping()
    public int update(@RequestBody ${entity} ${entity?uncap_first}){
        return ${(table.serviceName?substring(1))?uncap_first}.updateData(${entity?uncap_first});
    }

    @ApiOperation(value = "查询${table.comment!}分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<${entity}> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return ${(table.serviceName?substring(1))?uncap_first}.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询${table.comment!}")
    @GetMapping("{id}")
    <#list table.fields as field>
        <#if field.keyFlag>
    public ${entity} findById(@PathVariable ${field.propertyType} id){
        </#if>
    </#list>
        return ${(table.serviceName?substring(1))?uncap_first}.findById(id);
    }

}
</#if>