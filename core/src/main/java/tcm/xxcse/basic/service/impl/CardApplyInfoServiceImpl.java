package tcm.xxcse.basic.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcm.xxcse.basic.dto.CardApplyInfo;
import tcm.xxcse.basic.mapper.CardApplyInfoMapper;
import tcm.xxcse.basic.service.ICardApplyInfoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CardApplyInfoServiceImpl extends BaseServiceImpl<CardApplyInfo> implements ICardApplyInfoService {

    @Autowired
    CardApplyInfoMapper infoMapper;

    public List<CardApplyInfo> queryData(IRequest requestContext, CardApplyInfo dto, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return infoMapper.queryData(dto);

    }
}