package tcm.xxcse.basic.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import tcm.xxcse.basic.dto.ClassStudents;
import tcm.xxcse.basic.service.IClassStudentsService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassStudentsServiceImpl extends BaseServiceImpl<ClassStudents> implements IClassStudentsService{

}