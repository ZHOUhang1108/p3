package p3.vo;

import lombok.Data;

@Data
public class UserVo {
    private String loginName;
    private String password;
    private int userId;
    private int tag;
}
