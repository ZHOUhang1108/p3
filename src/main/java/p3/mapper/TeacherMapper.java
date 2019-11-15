package p3.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import p3.pojo.Student;
import p3.pojo.Teacher;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {
    /*根据ID查询老师信息*/
    Teacher selectById(@Param("teacherId") int teacherId);
    /*根据loginName查老师*/
    Teacher selectByloginName(@Param("loginName") String loginName);
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
    public List<Teacher> selectByParms(Teacher teacher);

    //批量删除讲师信息
    public void deleteList(List<Integer> ids);

    /*添加老师信息*/
    void addTea(Teacher teacher);

    /*查询所有老师*/
    List<Teacher> selectAll1();
}
