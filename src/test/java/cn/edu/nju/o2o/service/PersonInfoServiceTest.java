package cn.edu.nju.o2o.service;

import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.PersonInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class PersonInfoServiceTest extends BaseTest {
    @Autowired
    private PersonInfoService personInfoService;

    @Ignore
    @Test
    public void testCheckLogin() {
        PersonInfo personInfo = personInfoService.checkLogin(1, "123123");
        assertEquals("qly", personInfo.getUserName());
    }

    @Ignore
    @Test
    public void testGetUserList() {
        List<PersonInfo> userList = personInfoService.getUserList();
        System.out.println(userList.size());
    }

    @Ignore
    @Test
    public void testValidateUser() {
        assertEquals(true, personInfoService.validateUser(5));
        assertEquals(false, personInfoService.validateUser(5));

    }

    @Test
    public void testInvalidateUser(){
        assertEquals(true, personInfoService.invalidateUser(6));
        assertEquals(false, personInfoService.invalidateUser(6));
    }
}
