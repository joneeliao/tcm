package tcm.xxcse.basic.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import tcm.xxcse.basic.dto.ClassSchedule;
import tcm.xxcse.basic.service.IClassScheduleService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassScheduleServiceImpl extends BaseServiceImpl<ClassSchedule> implements IClassScheduleService{

}