package p3.controller;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import p3.pojo.Student;
import p3.pojo.Teacher;
import p3.service.StudentService;
import p3.service.TeacherService;
import p3.vo.Tagger;
import p3.vo.UserVo;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    Logger logger= LogManager.getLogger(PageController.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 学生登陆控制
     * @param
     * @return
     */
    @RequestMapping(value = "/login1")
    @ResponseBody
    public String Login(UserVo s, HttpSession session){
        try {
            logger.info("到了吗？"+s);
            logger.info("i==="+s.getTag());
            if(s.getTag()==0){/*说明是学生*/
                logger.info("loginName---"+s.getLoginName()+";pwd=="+s.getPassword());
                Student stu = studentService.selectByLoginName(s.getLoginName());
                logger.info("vo-----"+stu);
                if (stu.getSPassword().equals(s.getPassword())){
                    session.setAttribute("userId",stu.getStudentId());
                    Tagger tagger = new Tagger(true);
                    logger.info("认证完成了----------------");
                    Gson gson=new Gson();
                    String s1 = gson.toJson(tagger);
                    return s1;
                }
            }else if (s.getTag()==1){/*说明是老师*/
                Teacher tea = teacherService.selectByloginName(s.getLoginName());
                logger.info("tea----------"+tea);
                if (tea.getTPassword().equals(s.getPassword())){

                    session.setAttribute("userId",tea.getTeacherId());
                    Tagger tagger = new Tagger(true);
                    logger.info("认证完成了----------------");
                    Gson gson=new Gson();
                    String s1 = gson.toJson(tagger);
                    return s1;
                }
            }
            /*生成令牌信息*//*
            UsernamePasswordToken token = new UsernamePasswordToken(s.getLoginName(),s.getPassword());
            *//*生成认证主体对象*//*
            Subject subject = SecurityUtils.getSubject();
            *//*执行安全策略*//*

            subject.login(token);
            if(subject.isAuthenticated()){
                *//*通过认证流程*//*
                UserVo vo = studentService.selectByLoginName(s.getLoginName());
                session.setAttribute("userId",vo.getUserId());
                Tagger tagger = new Tagger(true);
                logger.info("认证完成了----------------");
                Gson gson=new Gson();
                String s1 = gson.toJson(tagger);
                return s1;
            }*/
        } catch (AuthenticationException e) {
            e.printStackTrace();
            logger.info("认证失败！");
        }
        return"no";
    }

}
