package com.sh.service.api;

import com.sh.config.SecurityAdmin;
import com.sh.entity.Admin;
import com.sh.entity.Role;
import com.sh.service.AdminService;
import com.sh.service.AuthService;
import com.sh.service.RoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created By Sunhu At 2020/6/5 9:30
 *
 * @author Sun
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    @Resource
    AdminService adminService;

    @Resource
    RoleService roleService;

    @Resource
    AuthService authService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1.根据账号名称查询Admin对象
        Admin admin = adminService.getAdminByLoginAcct(username);

        // 2.获取adminId
        Integer adminId = admin.getId();
        // 3.根据adminId查询角色信息
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
        // 4.根据adminId查询权限信息
        List<String> authNameList = authService.getAssignedAuthNameByAdminId(adminId);
        // 5.创建集合对象用来存储GrantedAuthority

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // 6.遍历assignedRoleList存入角色信息
        assignedRoleList.forEach(assignedRole -> {
            String roleName = assignedRole.getName();
            // 注意：不要忘了加前缀！
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));

        });

        // 7.遍历authNameList存入权限信息
        authNameList.forEach(authName -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(authName));

        });


        // 8.封装SecurityAdmin对象
        SecurityAdmin securityAdmin=new SecurityAdmin(admin,grantedAuthorities);
        return securityAdmin;
    }
}
