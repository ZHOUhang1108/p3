package p3.vo;

import lombok.Data;

@Data
public class TeaLeavRecord {
    private int id;
    private int stuId;
    private String trealName;
    private String reason;
    private String now;
    private String startTime;
    private String endTime;
    private int status;
}
