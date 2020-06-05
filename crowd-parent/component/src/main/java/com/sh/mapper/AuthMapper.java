package com.sh.mapper;

import com.sh.entity.Auth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created By Sunhu At 2020/6/4 14:36
 *
 * @author Sun
 */
public interface AuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Auth> getAllAuth();

    List<Integer> getAssignedAuthIdByRoleId(@Param("roleId") Integer roleId);

    int deleteOldRoleAuthRelation(@Param("roleId") Integer roleId);

    int saveRoleAuthRelation(@Param("roleId") Integer roleId, @Param("authIdArray") List<Integer> authIdArray);

    List<String> getAssignedAuthNameByAdminId(@Param("adminId") Integer adminId);
}