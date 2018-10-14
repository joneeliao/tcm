package tcm.xxmst.basic.mapper;

import com.hand.hap.mybatis.common.Mapper;
import tcm.xxmst.basic.dto.SchoolDetails;

import java.util.List;

public interface SchoolDetailsMapper extends Mapper<SchoolDetails> {
    List<SchoolDetails> selectSchool(SchoolDetails dto);
}