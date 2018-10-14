package tcm.xxmst.basic.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import tcm.xxmst.basic.dto.StudentsDetails;
import tcm.xxmst.basic.service.IStudentsDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class StudentsDetailsServiceImpl extends BaseServiceImpl<StudentsDetails> implements IStudentsDetailsService{

}