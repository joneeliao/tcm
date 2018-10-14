package tcm.xxcse.basic.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import tcm.xxcse.basic.dto.ClassInfo;
import tcm.xxcse.basic.service.IClassInfoService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassInfoServiceImpl extends BaseServiceImpl<ClassInfo> implements IClassInfoService{

}