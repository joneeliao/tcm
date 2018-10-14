package tcm.xxmst.basic.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcm.xxmst.basic.dto.SchoolDetails;
import tcm.xxmst.basic.mapper.SchoolDetailsMapper;
import tcm.xxmst.basic.service.ISchoolDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SchoolDetailsServiceImpl extends BaseServiceImpl<SchoolDetails> implements ISchoolDetailsService {

    @Autowired
    SchoolDetailsMapper schoolDetailsMapper;

    public List<SchoolDetails> selectSchool(IRequest requestContext, SchoolDetails dto, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return schoolDetailsMapper.selectSchool(dto);
    }

}