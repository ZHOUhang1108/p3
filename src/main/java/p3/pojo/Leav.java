package p3.pojo;

import lombok.Data;

@Data
public class Leav {
    private int id;
    private int teaId;
    private String reason;
    private String now;
    private String startTime;
    private String endTime;
    private int stuId;
    private int status;
}
