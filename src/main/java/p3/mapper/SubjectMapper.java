package p3.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import p3.pojo.Subject;

import java.util.List;

@Mapper
@Repository
public interface SubjectMapper {
    /**
     * 查询所有的科目
     * @return
     */
    public List<Subject> selectAll();
}
