package cn.edu.nju.o2o.web.shop;

import cn.edu.nju.o2o.dto.ProductExecution;
import cn.edu.nju.o2o.entity.*;
import cn.edu.nju.o2o.service.OrderService;
import cn.edu.nju.o2o.service.PersonInfoService;
import cn.edu.nju.o2o.service.ProductService;
import cn.edu.nju.o2o.service.ShopService;
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
@RequestMapping("/shop")
@SessionAttributes("shop")
public class ShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    //正常访问login页面
    @RequestMapping("/login")
    public String login(){
        return "/shop/login";
    }


    //表单提交过来的路径
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public String checkLogin(HttpServletRequest request, Model model){
        String temp = request.getParameter("shopId");
        for(int i = 0; i < temp.length(); i++){
            if(!Character.isDigit(temp.charAt(i))){
                return "shop/fail";
            }
        }
        Integer shopId = Integer.valueOf(request.getParameter("shopId"));
        String password = request.getParameter("password");
        Shop shop  = shopService.checkLogin(shopId, password);
        //若有user则添加到model里并且跳转到成功页面
        if(shop != null){
            model.addAttribute("shop", shop);
            return "/shop/shopHomepage";
        }
        return "/shop/fail";
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

    @RequestMapping(value = "/addProduct",method = RequestMethod.GET)
    @ResponseBody
    private Map<String ,Object> addProduct(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        Integer addNum = Integer.valueOf(request.getParameter("addNum"));
        productService.addNum(productId,addNum);
        modelMap.put("msg","success");
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

    //根据用户id获得用户订单信息
    @RequestMapping(value = "/getOrder",method = RequestMethod.GET)
    @ResponseBody
    public Map<String ,Object> getOrder(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        Shop user = (Shop) request.getSession().getAttribute("shop");
        List<Order> list = orderService.getOrderByShopId(user.getShopId());
        modelMap.put("list",list);
        return modelMap;
    }

    @RequestMapping(value = "/invalidateOrder", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> invalidateUser(HttpServletRequest request){
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        Map<String, Object> modelMap = new HashMap<>();
        Order order = new Order();
        order.setOrderId(orderId);
        order.setStatus(2);
        orderService.updateOrder(order);
        modelMap.put("msg","已拒绝");
        return modelMap;
    }

    @RequestMapping(value = "/validateOrder", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> validateUser(HttpServletRequest request){
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        Map<String, Object> modelMap = new HashMap<>();
        Order order = new Order();
        order.setOrderId(orderId);
        order.setStatus(1);
        orderService.updateOrder(order);
        modelMap.put("msg","已发货");
        return modelMap;
    }

    //添加商品
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String productName = request.getParameter("productName");
        String price = request.getParameter("normalPrice");
        Double normalPrice = Double.valueOf(request.getParameter("normalPrice"));
        Integer number = Integer.valueOf(request.getParameter("number"));
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(Integer.valueOf(request.getParameter("productCategoryId")));
        String description = request.getParameter("description");
        Shop shop = new Shop();
        shop.setShopId(1);
        Product product = new Product();
        product.setShop(shop);
        product.setDescription(description);
        product.setProductCategory(productCategory);
        product.setNumber(number);
        product.setProductName(productName);
        product.setNormalPrice(normalPrice);
        product.setNumber(number);
        productService.addProduct(product);
        modelMap.put("msg","添加成功");
        return modelMap;
    }

//    //测试超链接跳转到另一个页面是否可以取到session值
//    @RequestMapping("/anotherpage")
//    public String hrefpage(){
//
//        return "anotherpage";
//    }


    //跳转
    @RequestMapping("/productDetail")
    public String goProductdetail(){ return "shop/productDetail";}
    @RequestMapping("/productManagement")
    public String goProductManagement(){ return "shop/productManagement";}
    @RequestMapping("/orderManagement")
    public String goOrderManagement(){ return "shop/orderManagement";}
    @RequestMapping("/shopHomepage")
    public String goShopHomepage(){ return "shop/shopHomepage";}
    @RequestMapping("/prodcuctAddition")
    public String goProductAddition(){ return "shop/productAddition";}
    //注销方法
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session, SessionStatus sessionStatus){
        //通过session.invalidata()方法来注销当前的session
        session.removeAttribute("shop");//我这里是先取出httpsession中的user属性
        session.invalidate();  //然后是让httpsession失效
        sessionStatus.setComplete();//最后是调用sessionStatus方法
        return "/index";
    }


}
