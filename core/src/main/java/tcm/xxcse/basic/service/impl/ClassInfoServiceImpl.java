package tcm.xxcse.basic.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcm.xxcse.basic.dto.ClassInfo;
import tcm.xxcse.basic.mapper.ClassInfoMapper;
import tcm.xxcse.basic.service.IClassInfoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassInfoServiceImpl extends BaseServiceImpl<ClassInfo> implements IClassInfoService {

    @Autowired
    ClassInfoMapper infoMapper;

    public List<ClassInfo> queryData(IRequest requestContext, ClassInfo dto, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return infoMapper.queryData(dto);
    }
}