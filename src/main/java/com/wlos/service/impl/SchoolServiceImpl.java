package com.wlos.service.impl;

import com.wlos.entity.School;
import com.wlos.mapper.SchoolMapper;
import com.wlos.service.ISchoolService;
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
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {

    @Override
    public  IPage<School> findListByPage(Integer page, Integer pageCount){
        IPage<School> wherePage = new Page<>(page, pageCount);
        School where = new School();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(School school){
        return baseMapper.insert(school);
    }

    @Override
    public int delete(Integer id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(School school){
        return baseMapper.updateById(school);
    }

    @Override
    public School findById(Integer id){
        return  baseMapper.selectById(id);
    }
}
