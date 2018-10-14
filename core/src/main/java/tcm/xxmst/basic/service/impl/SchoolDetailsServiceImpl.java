package tcm.xxmst.basic.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import tcm.xxmst.basic.dto.SchoolDetails;
import tcm.xxmst.basic.service.ISchoolDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SchoolDetailsServiceImpl extends BaseServiceImpl<SchoolDetails> implements ISchoolDetailsService{

}