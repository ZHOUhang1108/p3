package p3.vo;

import lombok.Data;
import p3.pojo.Grade;
import p3.pojo.Subject;

import java.util.List;

@Data
public class ResultToLesson {
    private List<LessonResult> lessonResults;
    private List<Subject> subjects;
    private List<Grade> grades;
}
