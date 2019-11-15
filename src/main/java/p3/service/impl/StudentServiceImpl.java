package p3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p3.mapper.StudentMapper;
import p3.pojo.Student;
import p3.service.StudentService;
import p3.vo.UserVo;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student selectByLoginName(String loginName) {
        return studentMapper.selectByLoginName(loginName);
    }

    @Override
    public Student selectById(int userId) {
        return studentMapper.selectById(userId);
    }

    @Override
    public Student selectByPassword(String password) {
        return studentMapper.selectByPassword(password);
    }

    @Override
    public int addStudent(Student student) {
        studentMapper.addStudent(student);
        return student.getStudentId();
    }
}
