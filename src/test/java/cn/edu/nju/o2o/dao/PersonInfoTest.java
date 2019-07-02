package cn.edu.nju.o2o.dao;


import cn.edu.nju.o2o.BaseTest;
import cn.edu.nju.o2o.entity.PersonInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class PersonInfoTest extends BaseTest {
    @Autowired
    private PersonInfoDao personInfoDao;
    //@Test
    public void testQueryPersonInfo(){
        List<PersonInfo> personInfos = personInfoDao.queryPersonInfo();
        assertEquals(1,personInfos.size());
        assertEquals("123123", personInfos.get(0).getPassword());
    }
    //@Test
    public void testQueryOnePerson(){
        PersonInfo personInfo = personInfoDao.queryOnePerson(1);
        assertEquals("123123",personInfo.getPassword());
    }
    @Test
    public void testAddPersonInfo(){
        PersonInfo personInfo = new PersonInfo();
        personInfo.setAge(22);
        personInfo.setUserName("test");
        personInfo.setPassword("123123");
        personInfo.setDiscount(1.0000);
        personInfo.setGender(1);
        personInfo.setCreateTime(new Date());
        personInfo.setLastEditTime(new Date());
        int i = personInfoDao.addPersonInfo(personInfo);
        assertEquals(1,i);
    }
    @Ignore
    @Test
    public void testUpdatePersonInfo(){
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(6);
        personInfo.setUserName("updateTest");
        personInfo.setLastEditTime(new Date());
        int i = personInfoDao.updatePersonInfo(personInfo);
        assertEquals(1, i);
    }
    //@Test
    public void testDeletePersonInfo(){
        int i = personInfoDao.deletePersonInfo(4);
        assertEquals(1,i);
    }

}
