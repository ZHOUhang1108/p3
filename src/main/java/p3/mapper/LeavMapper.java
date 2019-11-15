package p3.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import p3.pojo.Leav;

import java.util.List;

@Mapper
@Repository
public interface LeavMapper {
    /*添加请假数据*/
    void addLeav(Leav leav);
    /*查询tea请假数据*/
    List<Leav> getTeaRecord(@Param("teaId") int userId);
}
