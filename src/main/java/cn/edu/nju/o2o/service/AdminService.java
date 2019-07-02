package cn.edu.nju.o2o.service;

import cn.edu.nju.o2o.entity.Admin;

public interface AdminService {
    public Admin checkLogin(Integer adminId, String password);
}
