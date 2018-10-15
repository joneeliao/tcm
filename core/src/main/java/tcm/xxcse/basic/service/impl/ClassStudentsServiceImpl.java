package tcm.xxcse.basic.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcm.xxcse.basic.dto.ClassStudents;
import tcm.xxcse.basic.mapper.ClassStudentsMapper;
import tcm.xxcse.basic.service.IClassStudentsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassStudentsServiceImpl extends BaseServiceImpl<ClassStudents> implements IClassStudentsService {

    @Autowired
    ClassStudentsMapper studentsMapper;

    public List<ClassStudents> queryData(IRequest requestContext, ClassStudents dto, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return studentsMapper.queryData(dto);
    }
}