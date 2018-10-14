package tcm.xxcse.basic.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import tcm.xxcse.basic.dto.ClassInfo;

import java.util.List;

public interface IClassInfoService extends IBaseService<ClassInfo>, ProxySelf<IClassInfoService> {
    public List<ClassInfo> queryData(IRequest requestContext, ClassInfo dto, int page, int pageSize);
}