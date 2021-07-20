package com.wlos.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.wlos.service.IStudentService;
import com.wlos.entity.Student;
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
@RequestMapping("/wlos/student")
public class StudentController {

    @Resource
    private IStudentService studentService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Student student){
        return studentService.add(student);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Integer id){
        return studentService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Student student){
        return studentService.updateData(student);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Student> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return studentService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Student findById(@PathVariable Integer id){
        return studentService.findById(id);
    }

}
