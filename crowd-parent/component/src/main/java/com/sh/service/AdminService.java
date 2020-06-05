package com.sh.service;

import com.github.pagehelper.PageInfo;
import com.sh.entity.Admin;

import java.util.List;

/**
 * Created By Sunhu At 2020/6/3 9:46
 * @author Sun
 */
public interface AdminService{


    int deleteByPrimaryKey(Integer id);

    void insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin getAdminByLoginAcct(String loginAcct, String userPassword);

    PageInfo<Admin> getAdminListByKeyWordPaging(String keyword,Integer pageNum,Integer pageSize);


    int saveAmdinRoleRelation(Integer adminId, List<Integer> roleIdList);

    Admin getAdminByLoginAcct(String username);

    void saveAdmin(Admin admin);
}
