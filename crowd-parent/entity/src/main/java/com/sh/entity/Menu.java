package com.sh.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Sunhu At 2020/6/4 10:47
 * @author Sun
 */
@Data
public class Menu {
    private Integer id;

    private Integer pid;

    private String name;

    private String url;

    private String icon;

    List<Menu> children=new ArrayList<>();

    private Boolean open=true;
}