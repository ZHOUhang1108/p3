package p3.vo;

import lombok.Data;

@Data
public class LessonResult {
    private int LessonId;
    private String lessonName;
    private String subjectName;
    private String gradeName;
    private String trealName;
    private String crealName;
    private String price;
    private int studentNum;
    private String lessonDetail;
    private String startTime;
    private String closeTime;
}
