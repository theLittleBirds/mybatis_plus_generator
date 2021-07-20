package ${basePackage}.controller;

import ${basePackage}.common_generator.result.Result;
import ${basePackage}.service.${modelNameUpperCamel}Service;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* Created by ${author} on ${date}.
*/

@RestController
@RequestMapping("/object")
public class ObjectController {

    @Autowired
    private ObjectService objectService;

    @PostMapping("/v1/executeQuerySql")
    public Result execute(@RequestParam String sql) {
        return Result.success(objectService.executeQuerySql(sql));
    }

    @PostMapping("/v1/executeOperateSql")
    public Result executeOperateSql(@RequestParam String sql) {
        return Result.success(objectService.executeOperateSql(sql));
    }
}
