package p3.service;

import p3.pojo.Leav;

import java.util.List;

public interface LeavService {
    /*添加请假数据*/
    int addLeav(Leav leav);

    List<Leav> getTeaRecord(int userId);
}
