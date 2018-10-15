package tcm.xxcse.basic.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcm.xxcse.basic.dto.ClassSchedule;
import tcm.xxcse.basic.mapper.ClassScheduleMapper;
import tcm.xxcse.basic.service.IClassScheduleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassScheduleServiceImpl extends BaseServiceImpl<ClassSchedule> implements IClassScheduleService {

    @Autowired
    ClassScheduleMapper classScheduleMapper;


    @Override
    public List<ClassSchedule> queryData(IRequest requestContext, ClassSchedule dto, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return classScheduleMapper.queryData(dto);
    }
}