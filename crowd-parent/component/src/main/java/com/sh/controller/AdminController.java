package com.sh.controller;

import com.github.pagehelper.PageInfo;
import com.sh.constant.CrowdConstant;
import com.sh.entity.Admin;
import com.sh.service.AdminService;
import com.sh.systemlog.OpLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created By Sunhu At 2020/6/3 10:13
 *
 * @author Sun
 */
@Controller
public class AdminController {


    @Autowired
    AdminService adminService;


    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam(name = "loginAcct") String loginAcct,
                          @RequestParam(name = "userPassword") String userPassword,
                          HttpSession session) {
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPassword);
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/to/main/page.html";

    }

    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session) {

        // 强制Session失效
        session.invalidate();

        return "redirect:/admin/to/login/page.html";
    }

    @OpLog(description = "分页获取用户")
    @RequestMapping("/admin/get/page.html")
    public String getAdminByKeyWordPaging(@RequestParam(name = "keyword",defaultValue = "")String keyword,
                                          @RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                          @RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize,
                                          ModelMap modelMap){

        PageInfo<Admin> list = adminService.getAdminListByKeyWordPaging(keyword, pageNum, pageSize);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,list);
        return "admin-page";
    }

    @RequestMapping("/admin/save.html")
    public String saveAdmin(Admin admin){

        adminService.saveAdmin(admin);

        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }


    @RequestMapping("/admin/to/edit/page.html")
    public String toEdit(@RequestParam("adminId")Integer adminId,
                         ModelMap modelMap){

        Admin admin = adminService.selectByPrimaryKey(adminId);
        modelMap.addAttribute("admin",admin);

        return "admin-edit";
    }


    @RequestMapping("/admin/update.html")
    public String updateAdmin(Admin admin,
                              Integer pageNum,String keyword){

        adminService.updateByPrimaryKeySelective(admin);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(
            @PathVariable("adminId") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword
    ) {

        // 执行删除
        adminService.deleteByPrimaryKey(adminId);

        // 页面跳转：回到分页页面

        // 尝试方案1：直接转发到admin-page.jsp会无法显示分页数据
        // return "admin-page";crowd

        // 尝试方案2：转发到/admin/get/page.html地址，一旦刷新页面会重复执行删除浪费性能
        // return "forward:/admin/get/page.html";

        // 尝试方案3：重定向到/admin/get/page.html地址
        // 同时为了保持原本所在的页面和查询关键词再附加pageNum和keyword两个请求参数
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }


}
