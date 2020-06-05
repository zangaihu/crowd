package com.sh.mapper;

import com.sh.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created By Sunhu At 2020/6/3 9:46
 *
 * @author Sun
 */

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> getAdminByLoginAcct(@Param("loginAcct") String loginAcct);

    List<Admin> getAdminListByKeyWord(@Param("keyword") String keyword);

    int deleteOldRelation(@Param("adminId") Integer adminId);

    int saveNewRelation(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);
}