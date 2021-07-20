package com.wlos.service;

import com.wlos.entity.School;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wlos
 * @since 2021-07-05
 */
public interface ISchoolService extends IService<School> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<School>
     */
    IPage<School> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param school 
     * @return int
     */
    int add(School school);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
     int delete(Integer id);

    /**
     * 修改
     *
     * @param school 
     * @return int
     */
    int updateData(School school);

    /**
     * id查询数据
     *
     * @param id id
     * @return School
     */
    School findById(Integer id);
}
