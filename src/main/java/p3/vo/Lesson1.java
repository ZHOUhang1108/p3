package p3.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson1 {
    private Integer lessonId;
    private String lessonName;
    private String trealName;
    private String crealName;
    private String subjectName;
    private Date startTime;
    private Date closeTime;
    private String lessonDetail;
    private String gradeName;
    private Integer studentNum;
    private Double price;
    private String lessonImg;
}
