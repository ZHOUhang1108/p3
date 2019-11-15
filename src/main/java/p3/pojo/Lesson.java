package p3.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private int lessonId;
    private String lessonName;
    private int teacherId;
    private int counsellorId;
    private int subjectId;
    private int gradeId;
    private int studentNum;
    private Date startTime;
    private Date closeTime;
    private String lessonImg;
    private String lessonDetail;
    private Grade grade;
    private Subject subject;
    private Teacher teacher;
    private double price;
}
