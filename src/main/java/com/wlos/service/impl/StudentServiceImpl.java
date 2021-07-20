package com.wlos.service.impl;

import com.wlos.entity.Student;
import com.wlos.mapper.StudentMapper;
import com.wlos.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wlos
 * @since 2021-07-05
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Override
    public  IPage<Student> findListByPage(Integer page, Integer pageCount){
        IPage<Student> wherePage = new Page<>(page, pageCount);
        Student where = new Student();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Student student){
        return baseMapper.insert(student);
    }

    @Override
    public int delete(Integer id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Student student){
        return baseMapper.updateById(student);
    }

    @Override
    public Student findById(Integer id){
        return  baseMapper.selectById(id);
    }
}
