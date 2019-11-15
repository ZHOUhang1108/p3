package p3.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p3.controller.PageController;
import p3.mapper.GradeMapper;
import p3.pojo.Grade;
import p3.service.GradeService;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    Logger logger= LogManager.getLogger(PageController.class);

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> selectAll() {
        return gradeMapper.selectAll();
    }
}
