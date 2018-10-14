package tcm.xxcse.basic.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import tcm.xxcse.basic.dto.AttendClassRecord;
import tcm.xxcse.basic.service.IAttendClassRecordService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AttendClassRecordServiceImpl extends BaseServiceImpl<AttendClassRecord> implements IAttendClassRecordService{

}