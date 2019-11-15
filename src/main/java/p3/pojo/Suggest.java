package p3.pojo;

import lombok.Data;

@Data
public class Suggest {
    private int id;
    private int tId;
    private int sId;
    private String content;
    private String time;
}
