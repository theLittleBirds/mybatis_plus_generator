package com.wlos.service;

import com.wlos.entity.WlosEntitySource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wlos
 * @since 2021-07-07
 */
public interface IWlosEntitySourceService extends IService<WlosEntitySource> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<WlosEntitySource>
     */
    IPage<WlosEntitySource> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param wlosEntitySource 
     * @return int
     */
    int add(WlosEntitySource wlosEntitySource);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
     int delete(String id);

    /**
     * 修改
     *
     * @param wlosEntitySource 
     * @return int
     */
    int updateData(WlosEntitySource wlosEntitySource);

    /**
     * id查询数据
     *
     * @param id id
     * @return WlosEntitySource
     */
    WlosEntitySource findById(String id);
}
