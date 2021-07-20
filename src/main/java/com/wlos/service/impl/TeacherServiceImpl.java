package com.wlos.service.impl;

import com.wlos.entity.Teacher;
import com.wlos.mapper.TeacherMapper;
import com.wlos.service.ITeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Override
    public  IPage<Teacher> findListByPage(Integer page, Integer pageCount){
        IPage<Teacher> wherePage = new Page<>(page, pageCount);
        Teacher where = new Teacher();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Teacher teacher){
        return baseMapper.insert(teacher);
    }

    @Override
    public int delete(Integer id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Teacher teacher){
        return baseMapper.updateById(teacher);
    }

    @Override
    public Teacher findById(Integer id){
        return  baseMapper.selectById(id);
    }
}
