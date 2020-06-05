package com.sh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sh.entity.Role;
import com.sh.mapper.RoleMapper;
import com.sh.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created By Sunhu At 2020/6/4 9:03
 * @author Sun
 */
@Service
public class RoleServiceImpl implements RoleService{

    private static final Logger logger=LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    @Override
    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteRoleByIds(List roleIds) {
        return roleMapper.deleteRoleByIds(roleIds);
    }

    @Override
    public PageInfo<Role> selectRoleByKeyWord(String keyword, Integer pageNum, Integer pageSize) {

        logger.info("============根据name获取角色列表===============");
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
        PageInfo<Role> pageInfo=new PageInfo<>(roleList);

        return pageInfo;

    }


    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        return roleMapper.getAssignedRole(adminId);
    }

    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        return roleMapper.getUnAssignedRole(adminId);
    }
}
