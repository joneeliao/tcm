package tcm.xxcse.basic.mapper;

import com.hand.hap.mybatis.common.Mapper;
import tcm.xxcse.basic.dto.CardApplyInfo;

import java.util.List;

public interface CardApplyInfoMapper extends Mapper<CardApplyInfo>{
    List<CardApplyInfo> queryData(CardApplyInfo dto);
}