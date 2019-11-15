package p3.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import p3.pojo.Lesson;
import p3.service.LessonService;
import p3.vo.Lesson1;
import p3.vo.LessonPageResult;
import p3.vo.LessonResult;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LessonController {
    private static final Logger logger= LogManager.getLogger(LessonController.class);

    @Autowired
    private LessonService lessonService;

    //查新所有课程信息
    @RequestMapping(value = "/lessons",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<LessonResult>> selectAll(){
        List<LessonResult> lessons = lessonService.selectAll();
        return new ResponseEntity<List<LessonResult>>(lessons, HttpStatus.OK);
    }

    //删除课程
    @RequestMapping(value = "/lesson/{lessonId}",method = RequestMethod.DELETE)
    public ResponseEntity<Lesson> deleteLesson(@PathVariable("lessonId")int lessonId){
        if (lessonId>0){//判断数据是否有效
            Lesson lesson = lessonService.selectById(lessonId);
            if (lesson!=null){//判断是否存在此数据，可以删除
                lessonService.deleteLesson(lessonId);
                return new ResponseEntity(HttpStatus.OK);
            }else {//如果不存在，无法删除
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }else {//数据无效，请求失败
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //增加课程
    @RequestMapping(value = "/lesson",method = RequestMethod.POST)
    public ResponseEntity<Lesson> addLesson(Lesson1 lesson){
        logger.info("=========="+lesson);
        logger.info("=========="+lesson.getLessonName());
        if(!StringUtils.isEmpty(lesson.getLessonName())){//判断传入的数据是否有效
            Lesson lesson1 = lessonService.selectByName(lesson.getLessonName());
            Lesson lesson2 = new Lesson();
            lesson2.setLessonName(lesson.getLessonName());
            lesson2.setStartTime(lesson.getStartTime());
            lesson2.setCloseTime(lesson.getCloseTime());
            lesson2.setLessonDetail(lesson.getLessonDetail());
            lesson2.setStudentNum(lesson.getStudentNum());
            lesson2.setPrice(lesson.getPrice());
            lesson2.setLessonImg(lesson.getLessonImg());
            if (lesson1==null){//判断数据库中是否含有此名称的课程，如果不存在，可以添加
                Integer teacherId = lessonService.selectTeacherIdByName(lesson.getTrealName());
                lesson2.setTeacherId(teacherId);

                Integer gradeId = lessonService.selectGradeIdByName(lesson.getGradeName());
                lesson2.setGradeId(gradeId);
                Integer subjectId = lessonService.selectSubjectIdByName(lesson.getSubjectName());
                lesson2.setSubjectId(subjectId);
                lessonService.addLesson(lesson2);
                return new ResponseEntity(HttpStatus.OK);
            }else {//数据库存在此数据，无法增加
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }else {//请求数据错误，无效请求
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //修改课程信息
    @RequestMapping(value = "/lesson/{lessonId}",method = RequestMethod.PUT)
    public ResponseEntity<Lesson> updateLesson(@PathVariable("lessonId") int lessonId, Lesson1 lesson){
        Lesson lesson2 = new Lesson();
        logger.info("00000000000000000000000000000");
        lesson2.setLessonId(lessonId);
        lesson2.setPrice(lesson.getPrice());
        lesson2.setLessonName(lesson.getLessonName());
        lesson2.setStartTime(lesson.getStartTime());
        lesson2.setCloseTime(lesson.getCloseTime());
        lesson2.setLessonDetail(lesson.getLessonDetail());
        lesson2.setStudentNum(lesson.getStudentNum());
        lesson2.setLessonImg(lesson.getLessonImg());
        if (lessonId>0){//请求数据是否有效
            Lesson lesson1 = lessonService.selectById(lessonId);
            if (lesson1!=null){//请求数据在数据库中存在，可以修改
                Integer teacherId = lessonService.selectTeacherIdByName(lesson.getTrealName());
                lesson2.setTeacherId(teacherId);
                Integer counsellorId = lessonService.selectCounsellorIdByName(lesson.getCrealName());
                lesson2.setCounsellorId(counsellorId);
                Integer subjectId = lessonService.selectSubjectIdByName(lesson.getSubjectName());
                lesson2.setSubjectId(subjectId);
                Integer gradeId = lessonService.selectGradeIdByName(lesson.getGradeName());
                lesson2.setGradeId(gradeId);
                lessonService.updateLesson(lesson2);
                return new ResponseEntity(HttpStatus.OK);
            }else {//请求数据在数据库中不存在
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }else {//请求数据存在错误，请求无效
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

/*   //模糊查询课程信息
    @RequestMapping(value = "/lessons/parms",method = RequestMethod.GET)
    public ResponseEntity<List<LessonResult>> selectLessonByParms(@RequestParam("parms")String parms){
        if (parms!=null){//判断传入的参数是否为空，不为空，按参数查询
            Lesson lesson = new Lesson();
            lesson.setLessonName(parms);
            List<Lesson> lessons = lessonService.selectLessonByParms(lesson);
            return new ResponseEntity<List<LessonResult>>(lessons, HttpStatus.OK);
        }else {//传入的参数为空，查询所有的数据
            List<LessonResult> lessons = lessonService.selectAll();
            return new ResponseEntity<List<LessonResult>>(lessons, HttpStatus.OK);
        }
    }*/


    //分页
    @RequestMapping(value = "/lesson/page",method = RequestMethod.POST)
    @ResponseBody
    public LessonPageResult selectAllByPage(@RequestParam("page") int page, @RequestParam("rows")int pageSize){
        LessonPageResult lessons = lessonService.selectLessonByPage(page, pageSize);
        return lessons;
    }

    //批量删除课程信息
    @RequestMapping(value = "/lessons",method = RequestMethod.DELETE)
    public ResponseEntity deleteList(@RequestBody int[] ids){
        List<Integer> ints=new ArrayList<>();
        for (int id : ids) {
            ints.add(id);
        }
        lessonService.deleteList(ints);
        return new ResponseEntity(HttpStatus.OK);
    }
}
