package com.wlos.service.impl;

import com.wlos.entity.WlosEntitySource;
import com.wlos.mapper.WlosEntitySourceMapper;
import com.wlos.service.IWlosEntitySourceService;
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
 * @since 2021-07-07
 */
@Service
public class WlosEntitySourceServiceImpl extends ServiceImpl<WlosEntitySourceMapper, WlosEntitySource> implements IWlosEntitySourceService {

    @Override
    public  IPage<WlosEntitySource> findListByPage(Integer page, Integer pageCount){
        IPage<WlosEntitySource> wherePage = new Page<>(page, pageCount);
        WlosEntitySource where = new WlosEntitySource();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(WlosEntitySource wlosEntitySource){
        return baseMapper.insert(wlosEntitySource);
    }

    @Override
    public int delete(String id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(WlosEntitySource wlosEntitySource){
        return baseMapper.updateById(wlosEntitySource);
    }

    @Override
    public WlosEntitySource findById(String id){
        return  baseMapper.selectById(id);
    }
}
