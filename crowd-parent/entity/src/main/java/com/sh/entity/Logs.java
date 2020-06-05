package com.sh.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created By Sunhu At 2020/6/3 16:54
 *
 * @author Sun
 */
@Data
public class Logs {

    Integer id;
    String userName;
    String description;
    String operation;
    String args;
    String ip;
    Date opTime;
}
