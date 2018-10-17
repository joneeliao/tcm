package tcm.xxmst.basic.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcm.xxmst.basic.dto.StudentsDetails;
import tcm.xxmst.basic.mapper.StudentsDetailsMapper;
import tcm.xxmst.basic.service.IStudentsDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StudentsDetailsServiceImpl extends BaseServiceImpl<StudentsDetails> implements IStudentsDetailsService {
    @Autowired
    StudentsDetailsMapper detailsMapper;

    public List<StudentsDetails> queryData(IRequest requestContext, StudentsDetails dto, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return detailsMapper.queryData(dto);
    }
}