package tcm.xxmst.basic.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import tcm.xxmst.basic.dto.TeachersDetails;
import tcm.xxmst.basic.service.ITeachersDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TeachersDetailsServiceImpl extends BaseServiceImpl<TeachersDetails> implements ITeachersDetailsService{

}