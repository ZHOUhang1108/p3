package p3.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p3.controller.PageController;
import p3.mapper.SubjectMapper;
import p3.pojo.Subject;
import p3.service.SubjectService;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    Logger logger= LogManager.getLogger(PageController.class);

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public List<Subject> selectAll() {
        return subjectMapper.selectAll();
    }
}
