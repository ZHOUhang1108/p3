package p3.service;


import org.apache.ibatis.annotations.Param;
import p3.pojo.Student;
import p3.vo.UserVo;

public interface StudentService {
    Student selectByLoginName(String loginName);

    Student selectById(int userId);

    Student selectByPassword(String password);

    int addStudent(Student student);

}
