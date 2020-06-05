package com.sh.mapper;

import com.sh.entity.Logs;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * (TLogs)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-03 17:25:39
 */
public interface LogsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Logs queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Logs> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param logs 实例对象
     * @return 对象列表
     */
    List<Logs> queryAll(Logs logs);

    /**
     * 新增数据
     *
     * @param logs 实例对象
     * @return 影响行数
     */
    int insert(Logs logs);

    /**
     * 修改数据
     *
     * @param logs 实例对象
     * @return 影响行数
     */
    int update(Logs logs);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}