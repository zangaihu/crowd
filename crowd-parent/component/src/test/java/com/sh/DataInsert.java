package com.sh;

import com.sh.entity.Admin;
import com.sh.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created By Sunhu At 2020/6/3 16:05
 *
 * @author Sun
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataInsert {

@Autowired
    AdminMapper adminMapper;

    @Test
    public void test() {

        for (int i = 0; i < 20; i++) {
            adminMapper.insert(new Admin(null, "loginAcct" + i, "userPswd" + i, "userName" + i, "email" + i + "@qq.com", null));
        }
    }
}
