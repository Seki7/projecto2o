package cn.edu.nju.o2o.web.user;

import cn.edu.nju.o2o.dao.ProductDao;
import cn.edu.nju.o2o.dto.ProductExecution;
import cn.edu.nju.o2o.entity.Order;
import cn.edu.nju.o2o.entity.PersonInfo;
import cn.edu.nju.o2o.entity.Product;
import cn.edu.nju.o2o.entity.ProductCategory;
import cn.edu.nju.o2o.service.OrderService;
import cn.edu.nju.o2o.service.PersonInfoService;
import cn.edu.nju.o2o.service.ProductService;
import cn.edu.nju.o2o.utils.Encryption;
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
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    @Autowired
    private PersonInfoService personInfoService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductDao productDao;

    //正常访问login页面
    @RequestMapping("/login")
    public String login(){
        return "user/login";
    }




    //登录
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public String checkLogin(HttpServletRequest request, Model model){
        String temp = request.getParameter("userId");
        for(int i = 0; i < temp.length(); i++){
            if(!Character.isDigit(temp.charAt(i))){
                return "user/fail";
            }
        }
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        String password = request.getParameter("password");
        PersonInfo personInfo = personInfoService.checkLogin(userId, password);
        if(personInfo != null){
            model.addAttribute("user", personInfo);
            return "user/userHomepage";
        }
        return "user/fail";
    }
     //订单前再次确认
    @RequestMapping(value = "/checkAgain", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkAgain(HttpServletRequest request, Model model){
        Map<String, Object> modelMap = new HashMap<>();
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        String password = request.getParameter("password");
        PersonInfo personInfo = personInfoService.checkLogin(userId, password);
        if(personInfo != null){
            modelMap.put("status","true");
        }else{
            modelMap.put("status","false");
        }
        return modelMap;

    }

    //获取当前session中的user
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUser(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        modelMap.put("user",user);
        return modelMap;
    }

    //更新个人基本信息
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateInfo(HttpServletRequest request, Model model){
        String userName = request.getParameter("userName");
        Integer age = Integer.valueOf(request.getParameter("age"));
        Integer gender = Integer.valueOf(request.getParameter("gender"));
        Integer userId = ((PersonInfo) request.getSession().getAttribute("user")).getUserId();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(userId);
        personInfo.setGender(gender);
        personInfo.setUserName(userName);
        personInfo.setAge(age);
        PersonInfo newUser = personInfoService.updateUser(personInfo);
        model.addAttribute("user", newUser);
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("msg","更新成功");
        //modelMap.put("user",newUser);
        return modelMap;
    }

    //修改密码
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updatePassword(HttpServletRequest request, Model model){
        Map<String, Object> modelMap = new HashMap<>();
        Integer userId = ((PersonInfo) request.getSession().getAttribute("user")).getUserId();
        String orignPassWord = request.getParameter("before");
        String newPassword = request.getParameter("after");
        newPassword = Encryption.code(newPassword);
        PersonInfo personInfo = personInfoService.checkLogin(userId,orignPassWord);
        if(personInfo == null){
            modelMap.put("msg","原密码不正确");
            return modelMap;
        }
        personInfo.setPassword(newPassword);
        personInfoService.updateUser(personInfo);
        modelMap.put("msg","更新成功");
        return modelMap;
    }
    //根据搜索条件商品
    @RequestMapping(value = "/getProductList",method = RequestMethod.GET)
    @ResponseBody
    private Map<String ,Object> getProductList(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        ProductCategory productCategory = new ProductCategory();
        Product product = new Product();
        int pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        String name = request.getParameter("productName");
        String category = request.getParameter("productCategoryId");
        if(name!= null && !name.equals("null")){
            product.setProductName(name);
        }
        if(category!= null && !category.equals("0")){
            productCategory.setProductCategoryId(Integer.valueOf(category));
        }
        product.setProductCategory(productCategory);
        ProductExecution productExecution = productService.getProductList(product,pageIndex,pageSize);
        modelMap.put("list",productExecution.getShopList());
        modelMap.put("count",productExecution.getCount());
        return modelMap;
    }

    //根据id获得一个商品信息
    @RequestMapping(value = "/getProduct",method = RequestMethod.GET)
    @ResponseBody
    public Map<String ,Object> getProduct(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        Product product = productService.queryOneProduct(productId);
        modelMap.put("product",product);
        return modelMap;
    }

    //创建订单
    @RequestMapping(value = "/createOrder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createOder(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String productName = request.getParameter("productName");
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        String userName = request.getParameter("userName");
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer buyNumber = Integer.valueOf(request.getParameter("buyNumber"));
        Double normalPrice = Double.valueOf(request.getParameter("normalPrice"));
        Product product = productDao.queryOneProduct(productId);
        if(product.getNumber() < buyNumber){
            modelMap.put("msg","fail");
            return modelMap;
        }
        product.setNumber(product.getNumber()-buyNumber);
        productDao.updateProduct(product);
        Order order = new Order();
        order.setProductName(productName);
        order.setProductId(productId);
        order.setUserName(userName);
        order.setUserId(userId);
        order.setProductNumber(buyNumber);
        order.setNormalPrice(normalPrice);
        order.setTotalPrice(normalPrice*buyNumber);
        orderService.createOrder(order);
        modelMap.put("msg","success");
        return modelMap;
    }

    //根据用户id获得用户订单信息
    @RequestMapping(value = "/getOrder",method = RequestMethod.GET)
    @ResponseBody
    public Map<String ,Object> getOrder(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        List<Order> list = orderService.getOrderByUserId(user.getUserId());
        modelMap.put("list",list);
        return modelMap;
    }




    //测试超链接跳转到另一个页面是否可以取到session值
    @RequestMapping("/anotherpage")
    public String hrefpage(){

        return "anotherpage";
    }

    //页面跳转
    @RequestMapping("/productDetail")
    public String goProductdetail(){ return "user/productDetail";}
    @RequestMapping("/manageInfo")
    public String goManageInfo(){
        return "user/manageInfo";
    }
    @RequestMapping("/userHomepage")
    public String goUserHomePage(){
        return "user/userHomepage";
    }
    @RequestMapping("/shopping")
    public String goShopping(){ return "user/shopping"; }
    @RequestMapping("/success")
    public String goSuccess(){ return "user/success"; }
    @RequestMapping("/checkOrder")
    public String goCheckOrder(){ return "user/checkOrder"; }



    //注销方法
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session, SessionStatus sessionStatus){
        //通过session.invalidata()方法来注销当前的session
        session.removeAttribute("user");//我这里是先取出httpsession中的user属性
        session.invalidate();  //然后是让httpsession失效
        sessionStatus.setComplete();//最后是调用sessionStatus方法
        return "/index";
    }


}
