package tcm.xxcse.basic.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import tcm.xxcse.basic.dto.ClassStudents;

import java.util.List;

public interface IClassStudentsService extends IBaseService<ClassStudents>, ProxySelf<IClassStudentsService> {
    public List<ClassStudents> queryData(IRequest requestContext, ClassStudents dto, int page, int pageSize);
}