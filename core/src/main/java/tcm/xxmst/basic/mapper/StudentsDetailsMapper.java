package tcm.xxmst.basic.mapper;

import com.hand.hap.mybatis.common.Mapper;
import tcm.xxmst.basic.dto.StudentsDetails;

import java.util.List;

public interface StudentsDetailsMapper extends Mapper<StudentsDetails>{
    public List<StudentsDetails> queryData(StudentsDetails dto);
}