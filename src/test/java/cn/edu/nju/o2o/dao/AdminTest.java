package cn.edu.nju.o2o.dao;

import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.Admin;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminTest extends BaseTest {
    @Autowired
    private AdminDao adminDao;

    @Test
    public void testQueryOneAdmin(){
        Admin admin = adminDao.queryOneAdmin(1);
        System.out.println(admin.getAdminId());
        System.out.println(admin.getPassword());
        System.out.println(admin.getStatus());
    }
}
