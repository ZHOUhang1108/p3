package p3.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import p3.pojo.Student;
import p3.vo.UserVo;

@Mapper
@Repository
public interface StudentMapper {
    Student selectByLoginName(@Param("loginName") String loginName);

    Student selectById(@Param("userId") int userId);

    Student selectByPassword(@Param("password") String password);

    void addStudent(Student student);
}
