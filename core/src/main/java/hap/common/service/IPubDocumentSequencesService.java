package hap.common.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hap.common.dto.PubDocumentSequences;

public interface IPubDocumentSequencesService extends IBaseService<PubDocumentSequences>, ProxySelf<IPubDocumentSequencesService> {
    PubDocumentSequences lockSequence(IRequest var1, PubDocumentSequences var2);

    PubDocumentSequences updateSequenct(IRequest var1, PubDocumentSequences var2);

    PubDocumentSequences processSequence(IRequest var1, PubDocumentSequences var2);

    PubDocumentSequences insertSequence(IRequest var1, PubDocumentSequences var2);

    String getDocSequence(IRequest request, String name, String preFix, Long length, String concatChar, String cycType);

    public String getSeqNumber(IRequest request, String name, String preFix, Long length, String concatChar, String cycType);

    Long getSeqCurrVal(String sequenceName);
    Long getSeqNextVal(String sequenceName);
}