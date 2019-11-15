package p3.service;


import p3.pojo.Grade;

import java.util.List;

public interface GradeService {
    /**
     * 查询并返回所有的年级
     * @return
     */
    public List<Grade> selectAll();
}
