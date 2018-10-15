package tcm.xxcse.basic.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import tcm.xxcse.basic.dto.ClassSchedule;

import java.util.List;

public interface IClassScheduleService extends IBaseService<ClassSchedule>, ProxySelf<IClassScheduleService> {
    public List<ClassSchedule> queryData(IRequest requestContext, ClassSchedule dto, int page, int pageSize);
}