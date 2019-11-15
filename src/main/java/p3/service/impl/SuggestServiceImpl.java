package p3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p3.mapper.SuggestMapper;
import p3.pojo.Suggest;
import p3.service.SuggestService;
@Service
public class SuggestServiceImpl implements SuggestService {
    @Autowired
    private SuggestMapper suggestMapper;

    @Override
    public int addSug(Suggest suggest) {
        suggestMapper.addSug(suggest);
        return suggest.getId();
    }
}
