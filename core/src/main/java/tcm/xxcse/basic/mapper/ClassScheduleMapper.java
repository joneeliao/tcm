package tcm.xxcse.basic.mapper;

import com.hand.hap.mybatis.common.Mapper;
import tcm.xxcse.basic.dto.ClassSchedule;

import java.util.List;

public interface ClassScheduleMapper extends Mapper<ClassSchedule> {
    public List<ClassSchedule> queryData(ClassSchedule dto);
}