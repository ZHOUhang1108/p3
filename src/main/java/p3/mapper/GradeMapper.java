package p3.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import p3.pojo.Grade;

import java.util.List;

@Mapper
@Repository
public interface GradeMapper {
    /**
     * 查询并返回所有的年级
     * @return
     */
    public List<Grade> selectAll();
}
