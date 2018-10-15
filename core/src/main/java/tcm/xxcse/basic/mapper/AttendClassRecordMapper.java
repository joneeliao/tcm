package tcm.xxcse.basic.mapper;

import com.hand.hap.mybatis.common.Mapper;
import tcm.xxcse.basic.dto.AttendClassRecord;

import java.util.List;

public interface AttendClassRecordMapper extends Mapper<AttendClassRecord> {
    public List<AttendClassRecord> queryData(AttendClassRecord dto);
}