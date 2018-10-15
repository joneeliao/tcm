package tcm.xxcse.basic.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import tcm.xxcse.basic.dto.AttendClassRecord;

import java.util.List;

public interface IAttendClassRecordService extends IBaseService<AttendClassRecord>, ProxySelf<IAttendClassRecordService> {
    public List<AttendClassRecord> queryData(IRequest requestContext, AttendClassRecord dto, int page, int pageSize);
}