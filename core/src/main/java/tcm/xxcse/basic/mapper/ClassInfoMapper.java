package tcm.xxcse.basic.mapper;

import com.hand.hap.mybatis.common.Mapper;
import tcm.xxcse.basic.dto.ClassInfo;

import java.util.List;

public interface ClassInfoMapper extends Mapper<ClassInfo> {
    public List<ClassInfo> queryData(ClassInfo dto);
}