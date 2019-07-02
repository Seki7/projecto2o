package cn.edu.nju.o2o.web.admin;

import cn.edu.nju.o2o.entity.Admin;
import cn.edu.nju.o2o.entity.PersonInfo;
import cn.edu.nju.o2o.service.AdminService;
import cn.edu.nju.o2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@SessionAttributes("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PersonInfoService personInfoService;

    //正常访问login页面
    @RequestMapping("/login")
    public String login() {
        return "/admin/login";
    }


    //验证登录
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public String checkLogin(HttpServletRequest request, Model model) {
        //调用service方法
        Integer shopId = Integer.valueOf(request.getParameter("adminId"));
        String password = request.getParameter("password");
        Admin admin = adminService.checkLogin(shopId, password);
        //若有user则添加到model里并且跳转到成功页面
        if (admin != null) {
            model.addAttribute("admin", admin);
            return "admin/userManagement";
        }
        return "/admin/fail";
    }

    //添加用户
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String userName = request.getParameter("userName");
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserName(userName);
        personInfoService.addUser(personInfo);
        modelMap.put("msg","添加成功");
        return modelMap;
    }




    //获取全部用户列表
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserList(HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        Map<String, Object> modelMap = new HashMap<>();
        List<PersonInfo> userList = personInfoService.getUserList();
        modelMap.put("list", userList);
        modelMap.put("admin", admin);
        return modelMap;
    }

    @RequestMapping(value = "/validateUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> validateUser(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Map<String, Object> modelMap = new HashMap<>();
        if(personInfoService.validateUser(userId)){
            modelMap.put("msg","更新成功");
        }else{
            modelMap.put("msg","无效更新");
        }
        return modelMap;
    }

    @RequestMapping(value = "/invalidateUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> invalidateUser(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Map<String, Object> modelMap = new HashMap<>();
        if(personInfoService.invalidateUser(userId)){
            modelMap.put("msg","更新成功");
        }else{
            modelMap.put("msg","无效更新");
        }
        return modelMap;
    }



    //注销方法
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session, SessionStatus sessionStatus){
        session.removeAttribute("admin");//我这里是先取出httpsession中的user属性
        session.invalidate();  //然后是让httpsession失效
        sessionStatus.setComplete();//最后是调用sessionStatus方法
        return "/index";
    }
}
