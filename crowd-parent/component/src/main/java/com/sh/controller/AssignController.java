package com.sh.controller;

import com.sh.entity.Auth;
import com.sh.entity.Role;
import com.sh.service.AdminService;
import com.sh.service.AuthService;
import com.sh.service.RoleService;
import com.sh.util.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created By Sunhu At 2020/6/4 11:46
 *
 * @author Sun
 */
@Controller
public class AssignController {

    @Resource
    RoleService roleService;

    @Resource
    AdminService adminService;

    @Resource
    AuthService authService;


    @RequestMapping("/assign/to/assign/role/page.html")
    public String toAssignRolePage(
            @RequestParam("adminId") Integer adminId, ModelMap modelMap
    ) {
        // 1.查询已分配角色
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
        // 2.查询未分配角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);
        // 3.存入模型（本质上其实是：request.setAttribute("attrName",attrValue);
        modelMap.addAttribute("assignedRoleList", assignedRoleList);
        modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);
        return "assign-role";
    }

    @RequestMapping("/assign/do/role/assign.html")
    public String saveAmdinRoleRelation(@RequestParam("adminId")Integer adminId,
                                        @RequestParam("pageNum")Integer pageNum,
                                        @RequestParam("keyword")String keyword,
                                        @RequestParam(value = "roleIdList",required = false)List<Integer> roleIdList
                                        ){

        int i=adminService.saveAmdinRoleRelation(adminId,roleIdList);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }


    @ResponseBody
    @RequestMapping("/assgin/get/all/auth.json")
    public ResultEntity<List<Auth>> getAllAuth(){


        List<Auth> authList=authService.getAllAuth();

        return ResultEntity.successWithData(authList);
    }

    /**
     * 根据角色id查询 该角色具有哪些权限
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/assign/get/assigned/auth/id/by/role/id.json")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(
            @RequestParam("roleId") Integer roleId) {
        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);
        return ResultEntity.successWithData(authIdList);
    }

    @ResponseBody
    @RequestMapping("assign/do/role/assign/auth.json")
    public ResultEntity<String> saveRoleAuthRelation(@RequestBody Map<String,List<Integer>> map){

        int i=authService.saveRoleAuthRelation(map);


        return ResultEntity.successWithoutData();


    }
}
