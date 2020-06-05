package com.sh.service;

import com.sh.entity.Menu;

import java.util.List;

/**
 * Created By Sunhu At 2020/6/4 10:47
 * @author Sun
 */
public interface MenuService{


    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

        List<Menu> getAllMenu();
    }
