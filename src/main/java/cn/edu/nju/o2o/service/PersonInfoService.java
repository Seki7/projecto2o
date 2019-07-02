package cn.edu.nju.o2o.service;

import cn.edu.nju.o2o.entity.PersonInfo;

import java.util.List;

public interface PersonInfoService {
    /**
     * 验证登录
     * @param userId
     * @param password
     * @return 验证通过返回该用户实体类，不通过返回null
     */
    PersonInfo checkLogin(Integer userId, String password);

    /**
     * 获取用户列表
     * @return
     */
    List<PersonInfo> getUserList();

    /**
     * 使得用户状态status变为可用
     * @param uesrId
     * @return
     */
    boolean validateUser(Integer uesrId);

    /**
     * 使得用户状态status变为不可用
     * @param userId
     * @return
     */
    boolean invalidateUser(Integer userId);


    /**
     * 添加用户
     * @param personInfo
     * @return true表示添加成功
     */
    boolean addUser(PersonInfo personInfo);

    /**
     * 更新个人信息
     * @param personInfo
     * @return 更新后的个人信息
     */
    PersonInfo updateUser(PersonInfo personInfo);
}
