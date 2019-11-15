package p3.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import p3.pojo.Student;
import p3.service.StudentService;
import p3.vo.MsgResult;

@Controller
public class StudentController {
    Logger logger= LogManager.getLogger(PageController.class);
    @Autowired
    private StudentService studentService;

    @RequestMapping("/stuReg")
    @ResponseBody
    public MsgResult stuRegister(Student student){
        logger.info("stu=="+student);
        Student student1 = studentService.selectByLoginName(student.getSName());
        if(student1==null){
            /*说明可以添加用户*/
            int i = studentService.addStudent(student);
            logger.info("添加的学生id---"+i);
            return new MsgResult(200,"添加学生信息成功！",null);
        }else {
            /*说明该用户名已存在*/
            return new MsgResult(-10,"该用户名已存在",null);
        }
    }

}
