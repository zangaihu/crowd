package com.sh.service;

import com.github.pagehelper.PageInfo;
import com.sh.entity.Role;

import java.util.List;

/**
 * Created By Sunhu At 2020/6/4 9:03
 * @author Sun
 */
public interface RoleService{


    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    PageInfo<Role> selectRoleByKeyWord(String keyword,Integer pageNum,Integer pageSize);

    int deleteRoleByIds(List roleIds);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);
}
