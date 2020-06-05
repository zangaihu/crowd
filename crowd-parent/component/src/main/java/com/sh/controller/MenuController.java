package com.sh.controller;

import com.sh.entity.Menu;
import com.sh.service.MenuService;
import com.sh.util.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By Sunhu At 2020/6/4 10:51
 *
 * @author Sun
 */
@Controller
public class MenuController {

    @Resource
    MenuService menuService;


    @ResponseBody
    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<Menu> getAllMenu() {

        List<Menu> menuList = menuService.getAllMenu();

        //存储根节点
        Menu root = null;

        Map<Integer, Menu> menuMap = new HashMap<>();

        //存储节点map
        menuList.forEach(menu -> {
            Integer id = menu.getId();
            menuMap.put(id, menu);
        });

        for (Menu menu : menuList) {
            Integer pid = menu.getPid();
            if (pid == null) {
                root = menu;
                continue;
            }
            Menu father = menuMap.get(pid);
            father.getChildren().add(menu);
        }


        return ResultEntity.successWithData(root);
    }


    @ResponseBody
    @RequestMapping("/menu/save.json")
    public ResultEntity<String>  saveMenu(Menu menu){

        int i = menuService.insertSelective(menu);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/menu/update.json")
    public ResultEntity<String>  updateMenu(Menu menu){

        menuService.updateByPrimaryKeySelective(menu);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/menu/remove.json")
    public ResultEntity<String>  deleteMenu(@RequestParam("id") Integer id){
        int i = menuService.deleteByPrimaryKey(id);

        return ResultEntity.successWithoutData();
    }


}
