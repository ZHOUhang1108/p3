package p3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p3.mapper.LeavMapper;
import p3.pojo.Leav;
import p3.service.LeavService;

import java.util.List;

@Service
public class LeavServiceImpl implements LeavService {
    @Autowired
    private LeavMapper leavMapper;

    @Override
    public int addLeav(Leav leav) {
        leavMapper.addLeav(leav);
        return leav.getId();
    }

    @Override
    public List<Leav> getTeaRecord(int userId) {
        return leavMapper.getTeaRecord(userId);
    }
}
