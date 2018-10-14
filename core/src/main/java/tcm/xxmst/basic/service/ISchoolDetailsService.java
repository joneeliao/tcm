package tcm.xxmst.basic.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import tcm.xxmst.basic.dto.SchoolDetails;

import java.util.List;

public interface ISchoolDetailsService extends IBaseService<SchoolDetails>, ProxySelf<ISchoolDetailsService> {
    public List<SchoolDetails> selectSchool(IRequest requestContext, SchoolDetails dto, int page, int pageSize);
}