package cn.edu.nju.o2o.entity;

import java.util.Date;

public class PersonInfo {
    //主键Id
    private Integer userId;
    //密码
    private String password;
    //昵称
    private String userName;
    //折扣 （0，1】 默认为1， 实际价格 = 标价 * discount
    private Double discount;
    //年龄
    private Integer age;
    //性别 0 为女性 1为男性
    private Integer gender;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date lastEditTime;
    //状态
    private int status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
