package tcm.xxcse.basic.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcm.xxcse.basic.dto.AttendClassRecord;
import tcm.xxcse.basic.mapper.AttendClassRecordMapper;
import tcm.xxcse.basic.service.IAttendClassRecordService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AttendClassRecordServiceImpl extends BaseServiceImpl<AttendClassRecord> implements IAttendClassRecordService {

    @Autowired
    AttendClassRecordMapper recordMapper;

    @Override
    public List<AttendClassRecord> queryData(IRequest requestContext, AttendClassRecord dto, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return recordMapper.queryData(dto);
    }
}