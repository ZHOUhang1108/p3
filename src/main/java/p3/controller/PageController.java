package p3.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

/**
 * 处理请求的页面跳转
 */
@Controller
@SessionAttributes({"test"})
public class PageController {
    Logger logger= LogManager.getLogger(PageController.class);

    /**
     * 显示首页
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String showIndex(Model model, HttpSession session){
        model.addAttribute("test","test");
        logger.info("打开index");
//        session.setAttribute("test","test");
        return "index";
    }
    /**
     * 显示登录页
     * @return
     */
    @RequestMapping("/login")
    public String showLogin(){
        logger.info("login1111");
        return"login2";
    }
    /**
     * 显示学生注册页
     * @return
     */
    @RequestMapping("/stu")
    public String showStuRegister(){
        return"stuRegister";
    }
    /**
     * 显示老师注册页
     * @return
     */
    @RequestMapping("/tea")
    public String showTeaRegister(){
        return"teaRegister";
    }

    /**
     * 认证过，但权限不足
     * @return
     */
    @RequestMapping("/unauth")
    public String showUnauth(){
        return "unauth";
    }

    /**
     * 测试页
     * @return
     */
    @RequestMapping("/test")
    public String test(){
        logger.info("test-----------");
        return"test";
    }

    /**
     * 显示后台首页
     * @return
     */
    @RequestMapping("/hou")
    public String houtai(){
        return "index2";
    }


    @RequestMapping("/{page}")
    public String showItem_list(@PathVariable("page") String page){
        return page;
    }


}
