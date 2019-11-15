package p3.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import p3.pojo.Suggest;
@Mapper
@Repository
public interface SuggestMapper {
    /*添加建议内容*/
    void addSug(Suggest suggest);
}
