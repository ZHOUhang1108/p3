package p3.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import p3.pojo.Student;
import p3.pojo.Teacher;
import p3.service.LessonService;
import p3.service.StudentService;
import p3.service.TeacherService;
import p3.vo.LessonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    Logger logger= LogManager.getLogger(PageController.class);

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private LessonService lessonService;

    /**
     * 进入‘我的’页面
     * @return
     */
    @RequestMapping(value = "/myyy")
    public String showMy(@RequestParam int tag, HttpServletRequest request){
        if(tag==0){/*说明是学生登录*/
            logger.info("tag=0=="+tag);
            Integer userId =(Integer) request.getSession().getAttribute("userId");
            Student stu = studentService.selectById(userId);
            logger.info("stu----"+stu);
            request.getSession().setAttribute("stu",stu);
            return"stu";
        }else {/*说明是老师登录*/
            logger.info("tag=1=="+tag);
            Integer userId =(Integer) request.getSession().getAttribute("userId");
            Teacher teacher = teacherService.selectById(userId);
            if(teacher.getRoleId()==5){/*说明是管理员*/
                return "index1";
            }else{
                logger.info("teacher----"+teacher);
                request.getSession().setAttribute("tea",teacher);
                List<LessonResult> lessonResults = lessonService.selectByTid(userId);
                logger.info("lesson--tt--"+lessonResults);
                request.getSession().setAttribute("lessonResult",lessonResults);
                return "tea";
            }
        }
    }
}
