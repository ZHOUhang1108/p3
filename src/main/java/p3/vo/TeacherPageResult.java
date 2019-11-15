package p3.vo;

import lombok.Data;
import p3.pojo.Teacher;

import java.util.List;

@Data
public class TeacherPageResult {
    private List<Teacher> rows;//数据信息集合
    private long Total;//总数据数量
}
