package cn.edu.nju.o2o.service.impl;


import cn.edu.nju.o2o.dao.AdminDao;
import cn.edu.nju.o2o.entity.Admin;
import cn.edu.nju.o2o.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;


    @Override
    public Admin checkLogin(Integer adminId, String password) {
        Admin admin = adminDao.queryOneAdmin(adminId);
        if(admin != null && admin.getPassword().equals(password)){
            return admin;
        }
        return null;
    }
}
