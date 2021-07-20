package com.wlos.service;

import com.wlos.entity.Teacher;
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
public interface ITeacherService extends IService<Teacher> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Teacher>
     */
    IPage<Teacher> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param teacher 
     * @return int
     */
    int add(Teacher teacher);

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
     * @param teacher 
     * @return int
     */
    int updateData(Teacher teacher);

    /**
     * id查询数据
     *
     * @param id id
     * @return Teacher
     */
    Teacher findById(Integer id);
}
