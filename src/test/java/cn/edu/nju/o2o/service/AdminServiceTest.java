package cn.edu.nju.o2o.service;

import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.Admin;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceTest extends BaseTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void testCheckLogin(){
        Admin admin = adminService.checkLogin(1,"123123");
        if(admin != null) System.out.println("successssssss");

    }
}
