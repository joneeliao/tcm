package tcm.xxcse.basic.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import tcm.xxcse.basic.dto.CardApplyInfo;

import java.util.List;

public interface ICardApplyInfoService extends IBaseService<CardApplyInfo>, ProxySelf<ICardApplyInfoService> {
    public List<CardApplyInfo> queryData(IRequest requestContext, CardApplyInfo dto, int page, int pageSize);
}