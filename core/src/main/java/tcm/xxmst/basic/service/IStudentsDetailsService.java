package tcm.xxmst.basic.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import tcm.xxmst.basic.dto.StudentsDetails;

import java.util.List;

public interface IStudentsDetailsService extends IBaseService<StudentsDetails>, ProxySelf<IStudentsDetailsService> {
    public List<StudentsDetails> queryData(IRequest requestContext, StudentsDetails dto, int page, int pageSize);
}