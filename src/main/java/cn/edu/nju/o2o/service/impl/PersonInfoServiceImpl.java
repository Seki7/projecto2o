package cn.edu.nju.o2o.service.impl;

import cn.edu.nju.o2o.dao.PersonInfoDao;
import cn.edu.nju.o2o.entity.PersonInfo;
import cn.edu.nju.o2o.service.PersonInfoService;
import cn.edu.nju.o2o.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo checkLogin(Integer userId, String password) {
        PersonInfo personInfo = personInfoDao.queryOnePerson(userId);
        if(personInfo != null && personInfo.getStatus() == 1 && Encryption.getSaltverifyMD5(password,personInfo.getPassword())){
            return personInfo;
        }
        return null;
    }

    @Override
    public List<PersonInfo> getUserList() {
        List<PersonInfo> userList = personInfoDao.queryPersonInfo();
        if(userList != null) return userList;
        return null;
    }

    @Override
    public boolean validateUser(Integer userId) {
        PersonInfo personInfo = personInfoDao.queryOnePerson(userId);
        if(personInfo.getStatus() == 1){
            return false;
        }
        personInfo.setStatus(1);
        personInfo.setLastEditTime(new Date());
        personInfoDao.updatePersonInfo(personInfo);
        return true;
    }

    @Override
    public boolean invalidateUser(Integer userId) {
        PersonInfo personInfo = personInfoDao.queryOnePerson(userId);
        if(personInfo.getStatus() == 0){
            return false;
        }
        personInfo.setStatus(0);
        personInfo.setLastEditTime(new Date());
        personInfoDao.updatePersonInfo(personInfo);
        return true;
    }

    @Override
    public boolean addUser(PersonInfo personInfo) {
        String orignPw = Encryption.code("123123");//默认密码为123123
        personInfo.setPassword(orignPw);
        personInfo.setCreateTime(new Date());
        personInfo.setStatus(1);//默认账号生效
        personInfo.setGender(1);//默认性别为男
        personInfo.setLastEditTime(new Date());
        personInfoDao.addPersonInfo(personInfo);
        return true;
    }

    @Override
    public PersonInfo updateUser(PersonInfo personInfo) {
        personInfo.setLastEditTime(new Date());
        personInfo.setStatus(1);
        personInfoDao.updatePersonInfo(personInfo);
        return personInfoDao.queryOnePerson(personInfo.getUserId());
    }
}
