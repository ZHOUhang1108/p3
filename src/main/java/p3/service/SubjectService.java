package p3.service;


import p3.pojo.Subject;

import java.util.List;

public interface SubjectService {
    /**
     * 查询所有的科目
     * @return
     */
    public List<Subject> selectAll();
}
