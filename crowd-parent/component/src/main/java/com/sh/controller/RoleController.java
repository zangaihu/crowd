package com.sh.controller;

import com.github.pagehelper.PageInfo;
import com.sh.entity.Role;
import com.sh.service.RoleService;
import com.sh.util.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created By Sunhu At 2020/6/4 9:04
 *
 * @author Sun
 */

@Controller
public class RoleController {


    @Resource
    RoleService roleService;

    //@ResponseJSONP

    //@PreAuthorize("hasRole('部长')")
    @ResponseBody
    @RequestMapping("/role/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getRole(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                @RequestParam(value = "keyword", defaultValue = "") String keyword) {

        PageInfo<Role> rolePageInfo = roleService.selectRoleByKeyWord(keyword, pageNum, pageSize);


        return ResultEntity.successWithData(rolePageInfo);


    }


    /**
     * 新增角色
     * @param role 角色实体
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/save.json")
    public ResultEntity<String> saveRole(Role role){

        int insert = roleService.insert(role);

        return ResultEntity.successWithoutData();
    }

    /**
     *  更新角色
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/update.json")
    public ResultEntity<String> updateRole(Role role)
    {
        int i = roleService.updateByPrimaryKey(role);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("role/remove/by/role/id/array.json")
    public ResultEntity<String> deleteRole(@RequestBody List<Integer> roleIds) throws IOException {

        System.out.println(roleIds);

        //List ids= objectMapper.readValue(roleIds, List.class);
        roleService.deleteRoleByIds(roleIds);
        return ResultEntity.successWithoutData();
    }
}
