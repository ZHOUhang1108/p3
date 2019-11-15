package p3.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p3.controller.PageController;
import p3.mapper.GradeMapper;
import p3.mapper.TeacherMapper;
import p3.pojo.Grade;
import p3.pojo.Teacher;
import p3.service.GradeService;
import p3.service.TeacherService;
import p3.vo.TeacherPageResult;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    Logger logger= LogManager.getLogger(PageController.class);
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher selectById(int teacherId) {
        return teacherMapper.selectById(teacherId);
    }

    @Override
    public Teacher selectByloginName(String loginName) {
        return teacherMapper.selectByloginName(loginName);
    }



    @Override
    public Teacher selectByName(String tName) {
        return teacherMapper.selectByName(tName);
    }

//    @Override
//    public Teacher selectById(int teacherId) {
//        return teacherMapper.selectById(teacherId);
//    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherMapper.addTeacher(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(int teacherId) {
        teacherMapper.deleteTeacher(teacherId);
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        teacherMapper.insertTeacher(teacher);
    }

    @Override
    public List<Teacher> selectAll() {
        return teacherMapper.selectAll();
    }

    @Override
    public TeacherPageResult selectByParms(Teacher teacher,int page, int pageSize) {

        TeacherPageResult pageResult = new TeacherPageResult();
        PageHelper.startPage(page,pageSize);//拦截查询语句并进行分页
        List<Teacher> rows = teacherMapper.selectByParms(teacher);
        logger.info("====99999999===="+teacher);
        logger.info("=================999999999========"+rows);
        PageInfo pageInfo = new PageInfo(rows);//获取结果集中的分页信息
        long total = pageInfo.getTotal();//获取总记录数
        pageResult.setTotal(total);
        pageResult.setRows(rows);
        return pageResult;
    }

    @Override
    public TeacherPageResult selectByPage(int page, int pageSize) {
        TeacherPageResult pageResult = new TeacherPageResult();
        PageHelper.startPage(page,pageSize);//拦截查询语句并进行分页
        List<Teacher> teachers = teacherMapper.selectAll();
        PageInfo pageInfo = new PageInfo(teachers);//获取结果集中的分页信息
        long total = pageInfo.getTotal();//获取总记录数
        pageResult.setTotal(total);
        pageResult.setRows(teachers);
        return pageResult;
    }

    @Override
    public void deleteList(List<Integer> ids) {
        teacherMapper.deleteList(ids);
    }

    @Override
    public int addTea(Teacher teacher) {
        teacherMapper.addTea(teacher);
        return teacher.getTeacherId();
    }

    @Override
    public List<Teacher> selectAll1() {
        return teacherMapper.selectAll1();
    }

}
