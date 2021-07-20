package com.wlos.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.wlos.service.ISchoolService;
import com.wlos.entity.School;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wlos
 * @since 2021-07-05
 */

@RestController
@RequestMapping("/wlos/school")
public class SchoolController {

    @Resource
    private ISchoolService schoolService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody School school){
        return schoolService.add(school);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Integer id){
        return schoolService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody School school){
        return schoolService.updateData(school);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<School> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return schoolService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public School findById(@PathVariable Integer id){
        return schoolService.findById(id);
    }

}
