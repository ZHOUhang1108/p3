package p3.service;


import p3.pojo.Lesson;
import p3.vo.LessonPageResult;
import p3.vo.LessonResult;

import java.util.List;

public interface LessonService {
    /*查询所有的课程*/
    public List<LessonResult> selectAll();
    /*根据gradeId查询lesson*/
    List<LessonResult> selectLessonByGradeId(int gradeId);
    /*根据gradeId和subjectId查询lesson*/
    List<LessonResult> selectLessonByGS(int gradeId, int subjectId);

    //查询课程全部信息
//    public List<Lesson> selectAll();

    //通过lessonName获得课程信息
    public Lesson selectByName(String lessonName);

    //通过lessonId获得课程信息
    public Lesson selectById(int lessonId);

    //删除课程
    public void deleteLesson(int lessonId);

    //通过讲师姓名查询讲师Id
    public Integer selectTeacherIdByName(String trealName);

    //通过导师姓名查询导师Id
    public Integer selectCounsellorIdByName(String crealName);

    //通过年级名字查询年级ID
    public Integer selectGradeIdByName(String gradeName);

    //通过科目名称查询科目Id
    public Integer selectSubjectIdByName(String subjectName);

    //增加课程信息
    public void addLesson(Lesson lesson);

    //修改课程信息
    public void updateLesson(Lesson lesson);

    //模糊查询课程信息
    public List<Lesson> selectLessonByParms(Lesson lesson);

    //分页
    public LessonPageResult selectLessonByPage(int page, int pageSize);

    //批量删除
    public void deleteList(List<Integer> ids);

    /*根据tID获取lesson*/
    List<LessonResult> selectByTid(int tid);
}
