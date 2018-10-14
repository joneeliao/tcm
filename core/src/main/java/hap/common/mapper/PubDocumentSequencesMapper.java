package hap.common.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hap.common.dto.PubDocumentSequences;

public interface PubDocumentSequencesMapper extends Mapper<PubDocumentSequences> {
    PubDocumentSequences lockDocSeqNumber(PubDocumentSequences var);

    int updateDocSeq(PubDocumentSequences var);

    int insertDosSeq(PubDocumentSequences var);

    PubDocumentSequences selectRseult(PubDocumentSequences var);

    Long getSeqCurrVal(String sequenceName);

    Long getSeqNextVal(String sequenceName);
}