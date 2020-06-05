package com.sh.mapper;

import com.sh.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created By Sunhu At 2020/6/4 9:03
 *
 * @author Sun
 */
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleByKeyword(@Param("keyword") String keyword);

    int deleteRoleByIds(List roleIds);

    List<Role> getAssignedRole(@Param("adminId") Integer adminId);

    List<Role> getUnAssignedRole(@Param("adminId") Integer adminId);
}