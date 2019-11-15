package p3.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;
import p3.pojo.Leav;
import p3.pojo.Student;
import p3.pojo.Suggest;
import p3.pojo.Teacher;
import p3.service.LeavService;
import p3.service.LessonService;
import p3.service.SuggestService;
import p3.service.TeacherService;
import p3.vo.LessonResult;
import p3.vo.MsgResult;
import p3.vo.TeacherPageResult;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TeacherController {
    private static final Logger logger= LogManager.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private LeavService leavService;
    @Autowired
    private SuggestService suggestService;
    @Autowired
    private LessonService lessonService;




    //讲师注册
    @RequestMapping(value = "/teacher",method = RequestMethod.POST)
    public ResponseEntity<Teacher> addTeacher(Teacher teacher){
        if (!StringUtils.isEmpty(teacher.getTName())){//判断传入的数据是否为空
            Teacher teacher1 = teacherService.selectByName(teacher.getTName());
            if (teacher1==null){//数据库中不存在此数据，可以添加
                 teacherService.addTeacher(teacher);
                return new ResponseEntity(HttpStatus.OK);
            }else {//数据库存在此数据，不能添加
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }else {//无效请求
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //通过id更新讲师信息
    @RequestMapping(value = "/teacher/{teacherId}",method = RequestMethod.PUT)
    public ResponseEntity<Teacher> updateTeacher( Teacher teacher){
        if (teacher.getTeacherId()>0){
            Teacher teacher1 = teacherService.selectById(teacher.getTeacherId());
            if (teacher1!=null){//数据库有此信息，能补全讲师信息
                teacherService.updateTeacher(teacher);
                return new ResponseEntity(HttpStatus.OK);
            }else{//数据库没有此信息，不能补全信息
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }else{//数据错误，无效请求
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //通过id删除讲师信息
    @RequestMapping(value = "/teacher/{teacherId}",method = RequestMethod.DELETE)
    public ResponseEntity deleteTeacher(@PathVariable("teacherId")int teacherId){
        if(teacherId>0){//判断数据是否有效
            Teacher teacher = teacherService.selectById(teacherId);
            if (teacher!=null){//判断数据库是否存在此数据，存在，可以删除
                teacherService.deleteTeacher(teacherId);
                return new ResponseEntity(HttpStatus.OK);
            }else {//不存在，删除不了
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }else {//无效数据，请求无效
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //增加讲师
    @RequestMapping(value = "/teachers",method = RequestMethod.POST)
    public ResponseEntity<Teacher> insertTeacher(Teacher teacher){
        if (!StringUtils.isEmpty(teacher.getTName())){
            Teacher teacher1 = teacherService.selectByName(teacher.getTName());
            if (teacher1==null){
                teacherService.insertTeacher(teacher);
                return new ResponseEntity(HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    //查询所有讲师信息
    @RequestMapping(value = "/teachers",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Teacher>> selectAll(){
        List<Teacher> teachers = teacherService.selectAll();
        return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
    }

    //模糊查询所有讲师信息
    @RequestMapping(value = "/teacher/page",method = RequestMethod.POST)
    @ResponseBody
    public TeacherPageResult selectAll(String test,@RequestParam("page") int page, @RequestParam("rows")int pageSize){
        logger.info("parms============"+test);
        Teacher teacher = new Teacher();
        teacher.setTName(test);
        logger.info("==========="+teacher);
        if (test!=null){//传入的数据不为空,进行模糊查询
            TeacherPageResult teachers = teacherService.selectByParms(teacher,page,pageSize);
            logger.info("teachers=========="+teachers);
            return teachers;
        }else {//数据为空，进行查询所有讲师信息
            TeacherPageResult teachers = teacherService.selectByPage(page, pageSize);
            return teachers;
        }
    }

    //分页
    /*@RequestMapping(value = "/teacher/page",method = RequestMethod.GET)
    @ResponseBody
    public TeacherPageResult selectByPage(@RequestParam("page") int page, @RequestParam("pageSize")int pageSize){
        TeacherPageResult teachers = teacherService.selectByPage(page, pageSize);
        logger.info("teacher----"+teachers);
        return teachers;
    }*/

    //批量删除讲师信息
    @RequestMapping(value = "/teachers",method = RequestMethod.DELETE)
    public ResponseEntity deleteList(@RequestBody int[] ids){
        List<Integer> ints=new ArrayList<>();
        for (int id : ids) {
            ints.add(id);
        }
        teacherService.deleteList(ints);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 老师账户注册--邮箱验证
     * @param teacher
     * @return
     */
    @RequestMapping("/teaReg")
    @ResponseBody
    public MsgResult teaRegister(Teacher teacher){
        logger.info("tea=="+teacher);
        Teacher teacher1 = teacherService.selectByloginName(teacher.getTName());
        if(teacher1==null){
            /*说明可以添加用户*/
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0;i<4;i++){
                int random =(int) (Math.random()*10);
                stringBuilder.append(random);
                logger.info("random---"+random);
                logger.info("stringbuilder--"+stringBuilder);
            }
            String s = stringBuilder.toString();
            try {
                sendMail("test",s,teacher.getTemail());
                return new MsgResult(200,"验证码发送成功",s);
            } catch (Exception e) {
                return new MsgResult(-11,"邮箱有问题",e);
            }
            /*int i = teacherService.addTea(teacher);*/
        }else {
            /*说明该用户名已存在*/
            return new MsgResult(-10,"该用户名已存在",null);
        }
    }
    /**
     * 老师账户注册--注册加表
     * @param teacher
     * @return
     */
    @RequestMapping("/teaReg2")
    @ResponseBody
    public MsgResult teaRegister2(Teacher teacher){
        logger.info("tea=="+teacher);
        Teacher teacher1 = teacherService.selectByloginName(teacher.getTName());
        if(teacher1==null){
            /*说明可以添加用户*/
            int i = teacherService.addTea(teacher);
            return new MsgResult(200,"用户注册成功",null);
        }else {
            /*说明该用户名已存在*/
            return new MsgResult(-10,"该用户名已存在",null);
        }
    }
    /**
     * 老师请假
     * @param leav
     * @return
     */
    @RequestMapping("/leav")
    @ResponseBody
    public MsgResult teaLeav(Leav leav,HttpSession session){
        int userId =(int) session.getAttribute("userId");
        leav.setTeaId(userId);
        logger.info("leav---"+leav);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        String now = simpleDateFormat.format(date);
        leav.setNow(now);
        leav.setStatus(0);
        int i = leavService.addLeav(leav);
        logger.info("请假id==="+i);
        return new MsgResult(200,"请假提交成功",null);
    }

    /**
     * 老师请假记录
     * @return
     */
    @RequestMapping(value = "/tLeavRecord",method = RequestMethod.GET)
    @ResponseBody
    public MsgResult tRecord(HttpSession session){
        int userId =(int) session.getAttribute("userId");
        List<Leav> teaRecord = leavService.getTeaRecord(userId);
        logger.info("teaRecord----"+teaRecord);
        return new MsgResult(200,"老师请假记录",teaRecord);
    }

    /**
     * 老师自我信息修改
     * @param files
     * @param session
     * @param teacher1
     * @return
     */
    @RequestMapping("/filesUpload")
    //requestParam要写才知道是前台的那个数组
    public String filesUpload(@RequestParam("myfiles") MultipartFile[] files, HttpSession session,Teacher teacher1) {
        logger.info("teacher1===="+teacher1);
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                if (!file.isEmpty()) {
                    try {
                        int tId=(int)session.getAttribute("userId");
                        Teacher teacher = teacherService.selectById(tId);
                        String imgName="t"+tId+file.getOriginalFilename();
                        teacher.setTeacherImg(imgName);
                        teacher.setSchool(teacher1.getSchool());
                        teacher.setProduce(teacher1.getProduce());
                        teacher.setTrealName(teacher1.getTrealName());
                        teacher.setProfession(teacher1.getProfession());
                        teacherService.updateTeacher(teacher);
                        Teacher teacher2 = teacherService.selectById(tId);
                        logger.info("tea2==="+teacher2);
                        String filePath ="D:\\1906\\p30\\src\\main\\resources\\static\\images/" + imgName;
                        File saveDir = new File(filePath);
                        file.transferTo(saveDir);
                        session.setAttribute("tea",teacher2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "tea";//跳转的页面
    }

    /**
     * 老师的反馈意见
     * @param suggest
     * @param session
     * @return
     */
    @RequestMapping(value = "/tSug",method = RequestMethod.POST)
    @ResponseBody
    public MsgResult tSuggest(Suggest suggest,HttpSession session){
        int userId =(int) session.getAttribute("userId");
        suggest.setTId(userId);
        Date date = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        suggest.setTime(time);
        logger.info("suggest"+suggest);
        int i = suggestService.addSug(suggest);

        return new MsgResult(200,"t反馈成功！",null);
    }

    /**
     * 老师课堂滑动条-失败！逻辑有问题！实现方法有问题
     * @param session
     * @return
     */
    @RequestMapping(value = "/tClass",method = RequestMethod.GET)
    @ResponseBody
    public MsgResult tClass(HttpSession session){
        int userId =(int) session.getAttribute("userId");
        List<LessonResult> lessonResults = lessonService.selectByTid(userId);
        return new MsgResult(200,"查询成功",lessonResults);
    }

    /**
     * javax.mail--邮件发送方法
     * @param subject
     * @param content
     * @param toEmailAddres
     * @throws Exception
     */
    private static void sendMail(String subject, String content, String toEmailAddres) throws Exception {

        String host = "smtp.qq.com";        //邮箱服务器地址
        String port = "25";                        //发送邮件的端口
        String auth = "false";                     //是否需要进行身份验证,视调用的邮箱而定，比方说QQ邮箱就需要，否则就会发送失败
        String protocol = "smtp";                  //传输协议
        String mailFrom = "1227855866@qq.com";   //发件人邮箱
        String personalName = "IPDs";         //发件人邮箱别名
        String username = "1227855866@qq.com";   //发件人邮箱用户名
        String password = "vbnilrevpswhbaef";               //发件人邮箱密码
        String mailDebug = "false";                //是否开启debug
        String contentType = null;                 //邮件正文类型

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", auth == null ? "true" : auth);
        props.put("mail.transport.protocol", protocol == null ? "smtp" : protocol);
        props.put("mail.smtp.port", port == null ? "25" : port);
        props.put("mail.debug", mailDebug == null ? "false" : mailDebug);
        Session mailSession = Session.getInstance(props);

        // 设置session,和邮件服务器进行通讯。
        MimeMessage message = new MimeMessage(mailSession);
        // 设置邮件主题
        message.setSubject(subject);
        // 设置邮件正文
        message.setContent(content, contentType == null ? "text/html;charset=UTF-8" : contentType);
        // 设置邮件发送日期
        message.setSentDate(new Date());
        InternetAddress address = new InternetAddress(mailFrom, personalName);
        // 设置邮件发送者的地址
        message.setFrom(address);
        // 设置邮件接收方的地址
        message.setRecipients(Message.RecipientType.TO, toEmailAddres);
        Transport transport = null;
        transport = mailSession.getTransport();
        message.saveChanges();

        transport.connect(host, username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
