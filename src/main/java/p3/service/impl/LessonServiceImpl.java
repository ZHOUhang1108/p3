package p3.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p3.controller.PageController;
import p3.mapper.LessonMapper;
import p3.pojo.Lesson;
import p3.service.LessonService;
import p3.vo.LessonPageResult;
import p3.vo.LessonResult;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    Logger log= LogManager.getLogger(PageController.class);

    @Autowired
    private LessonMapper lessonMapper;

    @Override
    public List<LessonResult> selectAll() {
        return lessonMapper.selectAll();
    }

    @Override
    public List<LessonResult> selectLessonByGradeId(int gradeId) {
        return lessonMapper.selectLessonByGradeId(gradeId);
    }

    @Override
    public List<LessonResult> selectLessonByGS(int gradeId, int subjectId) {
        return lessonMapper.selectLessonByGS(gradeId, subjectId);
    }
//    @Override
//    public List<Lesson> selectAll() {
//        return lessonMapper.selectAll();
//    }

    @Override
    public Lesson selectByName(String lessonName) {
        return lessonMapper.selectByName(lessonName);
    }

    @Override
    public Lesson selectById(int lessonId) {
        return lessonMapper.selectById(lessonId);
    }

    @Override
    public void deleteLesson(int lessonId) {
        lessonMapper.deleteLesson(lessonId);
    }

    @Override
    public Integer selectTeacherIdByName(String trealName) {
        return lessonMapper.selectTeacherIdByName(trealName);
    }

    @Override
    public Integer selectCounsellorIdByName(String crealName) {
        return lessonMapper.selectCounsellorIdByName(crealName);
    }

    @Override
    public Integer selectGradeIdByName(String gradeName) {
        return lessonMapper.selectGradeIdByName(gradeName);
    }

    @Override
    public Integer selectSubjectIdByName(String subjectName) {
        return lessonMapper.selectSubjectIdByName(subjectName);
    }

    @Override
    public void addLesson(Lesson lesson) {
        lessonMapper.addLesson(lesson);
    }

    @Override
    public void updateLesson( Lesson lesson) {
        log.info(lesson.toString());
        lessonMapper.updateLesson(lesson);
    }

    @Override
    public List<Lesson> selectLessonByParms(Lesson lesson) {
        return lessonMapper.selectLessonByParms(lesson);
    }

    @Override
    public LessonPageResult selectLessonByPage(int page, int pageSize) {
        LessonPageResult lessonPageResult = new LessonPageResult();
        PageHelper.startPage(page,pageSize);//拦截查询语句并进行分页
        List<LessonResult> lessons = lessonMapper.selectAll();
        PageInfo pageInfo = new PageInfo(lessons);//获取分页中的信息
        long total = pageInfo.getTotal();//获取结果集中的所有信息
        lessonPageResult.setTotal(total);
        lessonPageResult.setLessons(lessons);
        return lessonPageResult;
    }

    @Override
    public void deleteList(List<Integer> ids) {
        lessonMapper.deleteList(ids);
    }

    @Override
    public List<LessonResult> selectByTid(int tid) {
        return lessonMapper.selectByTid(tid);
    }


}
