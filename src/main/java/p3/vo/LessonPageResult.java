package p3.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import p3.pojo.Lesson;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonPageResult {
    private List<LessonResult> lessons;
    private Long total;
}
