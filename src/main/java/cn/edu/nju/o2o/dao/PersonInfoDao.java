package cn.edu.nju.o2o.dao;

import cn.edu.nju.o2o.entity.Area;
import cn.edu.nju.o2o.entity.PersonInfo;

import java.util.List;

public interface PersonInfoDao {
    /**
     * 列出个人信息表
     * @return personInfos
     */
    List<PersonInfo> queryPersonInfo();
    /**
     * 查询指定userId的用户信息
     * @param  id
     * @return PersonInfo
     */
    PersonInfo queryOnePerson(Integer id);
    /**
     * 插入一条用户信息
     * @param personInfo
     * @return 1 表示插入成功， 0表示插入失败
     */
    int addPersonInfo(PersonInfo personInfo);

    /**
     * 更新一条用户信息
     * @param personInfo
     * @return 1表示更新成功， 0表示更新失败
     */
    int updatePersonInfo(PersonInfo personInfo);

    /**
     * 删除一条用户信息
     * @param userId
     * @return 1表示删除成功，0表示删除失败
     */
    int deletePersonInfo(Integer userId);

}
