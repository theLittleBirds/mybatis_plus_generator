package com.wlos.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.wlos.service.IWlosEntitySourceService;
import com.wlos.entity.WlosEntitySource;
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
 * @since 2021-07-07
 */

@RestController
@RequestMapping("/wlos/wlosEntitySource")
public class WlosEntitySourceController {

    @Resource
    private IWlosEntitySourceService wlosEntitySourceService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody WlosEntitySource wlosEntitySource){
        return wlosEntitySourceService.add(wlosEntitySource);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") String id){
        return wlosEntitySourceService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody WlosEntitySource wlosEntitySource){
        return wlosEntitySourceService.updateData(wlosEntitySource);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<WlosEntitySource> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return wlosEntitySourceService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public WlosEntitySource findById(@PathVariable String id){
        return wlosEntitySourceService.findById(id);
    }

}
