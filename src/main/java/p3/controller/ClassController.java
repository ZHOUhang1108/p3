package p3.controller;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import p3.pojo.Grade;
import p3.pojo.Subject;
import p3.service.GradeService;
import p3.service.LessonService;
import p3.service.SubjectService;
import p3.vo.LessonResult;
import p3.vo.ResultToLesson;

import java.util.HashSet;
import java.util.List;

/**
 * 处理课程数据
 */
@Controller
public class ClassController {
    Logger logger= LogManager.getLogger(PageController.class);

    @Autowired
    private LessonService lessonService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private GradeService gradeService;

    /**
     * 课程展示页面
     * @return
     */
    @RequestMapping(value = "/cc/lessons",method = RequestMethod.GET)
    @ResponseBody
    public String showLessons(@RequestParam int pp){
        List<LessonResult> results = lessonService.selectAll();
        logger.info("test``````````````````````"+results);
        List<Subject> subjects = subjectService.selectAll();
        List<Grade> grades = gradeService.selectAll();
        ResultToLesson resultToLesson = new ResultToLesson();
        resultToLesson.setLessonResults(results);
        resultToLesson.setSubjects(subjects);
        resultToLesson.setGrades(grades);
        Gson gson=new Gson();
        String s = gson.toJson(resultToLesson);
        logger.info("s====="+s);
        return s;
    }

    @RequestMapping(value = "/cc/lessonByGradeId",method = RequestMethod.GET)
    @ResponseBody
    public String selectLessonByGradeId(@RequestParam int gradeId){
        List<LessonResult> lessonResults1 = lessonService.selectLessonByGradeId(gradeId);
        HashSet<LessonResult> lessonResults=new HashSet<>();
        for (LessonResult result:lessonResults1){
            lessonResults.add(result);
            logger.info("ssssssssss---------"+result);
        }
        Gson gson=new Gson();
        String s = gson.toJson(lessonResults);
        return s;
    }

    @RequestMapping(value = "/cc/selectLessonByGS",method = RequestMethod.GET)
    @ResponseBody
    public String selectLessonByGS(@RequestParam("gradeId") int gradeId,
                                   @RequestParam("subjectId") int subjectId){
        List<LessonResult> lessonResults = lessonService.selectLessonByGS(gradeId, subjectId);
        Gson gson=new Gson();
        String s = gson.toJson(lessonResults);
        return s;
    }



}
