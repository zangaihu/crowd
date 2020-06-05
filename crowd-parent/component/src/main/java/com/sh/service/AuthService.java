package com.sh.service;

import com.sh.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * Created By Sunhu At 2020/6/4 14:36
 * @author Sun
 */
public interface AuthService{


    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

        List<Auth> getAllAuth();

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    int saveRoleAuthRelation(Map<String, List<Integer>> map);

    List<String> getAssignedAuthNameByAdminId(Integer adminId);
}
