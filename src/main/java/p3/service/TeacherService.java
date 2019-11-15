package p3.service;


import p3.pojo.Teacher;
import p3.vo.TeacherPageResult;

import java.util.List;

public interface TeacherService {
    /*根据id查询老师*/
    Teacher selectById(int teacherId);
    /*根据loginName查信息*/
    Teacher selectByloginName(String loginName);
    //通过用户名查询讲师
    public Teacher selectByName(String tName);

    //通过id查询讲师
//    public Teacher selectById(int teacherId);

    //讲师进行注册
    public void addTeacher(Teacher teacher);

    //通过id动态更新讲师信息
    public void updateTeacher(Teacher teacher);

    //通过id删除讲师信息
    public void deleteTeacher(int teacherId);

    //增加讲师
    public void insertTeacher(Teacher teacher);

    //查询所有讲师信息
    public List<Teacher> selectAll();

    //模糊查询讲师信息
    public TeacherPageResult selectByParms(Teacher teacher,int page, int pageSize);

    //分页
    public TeacherPageResult selectByPage(int page, int pageSize);

    //批量删除讲师信息
    public void deleteList(List<Integer> ids);

    /*添加老师信息*/
    int addTea(Teacher teacher);

    /*查询所有老师*/
    List<Teacher> selectAll1();
}
