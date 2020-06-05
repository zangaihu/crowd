package com.sh.service.impl;

import com.sh.entity.Auth;
import com.sh.mapper.AuthMapper;
import com.sh.service.AuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created By Sunhu At 2020/6/4 14:36
 *
 * @author Sun
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthMapper authMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return authMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Auth record) {
        return authMapper.insert(record);
    }

    @Override
    public int insertSelective(Auth record) {
        return authMapper.insertSelective(record);
    }

    @Override
    public Auth selectByPrimaryKey(Integer id) {
        return authMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Auth record) {
        return authMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Auth record) {
        return authMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Auth> getAllAuth() {
        List<Auth> authList = authMapper.getAllAuth();
        return authList;
    }


    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        List<Integer> AssignedAuthId = authMapper.getAssignedAuthIdByRoleId(roleId);

        return AssignedAuthId;


    }

    @Override
    public int saveRoleAuthRelation(Map<String, List<Integer>> map) {

        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        //先清除在保存

        authMapper.deleteOldRoleAuthRelation(roleId);

        List<Integer> authIdArray = map.get("authIdArray");
        if (!authIdArray.isEmpty()){
            authMapper.saveRoleAuthRelation(roleId,authIdArray);
        }


        return 1;
    }

    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {

            List<String> authName=authMapper.getAssignedAuthNameByAdminId(adminId);
        return authName;
    }
}
