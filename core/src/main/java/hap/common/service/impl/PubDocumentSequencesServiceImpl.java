package hap.common.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hap.common.dto.PubDocumentSequences;
import hap.common.mapper.PubDocumentSequencesMapper;
import hap.common.service.IPubDocumentSequencesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class PubDocumentSequencesServiceImpl extends BaseServiceImpl<PubDocumentSequences> implements IPubDocumentSequencesService {
    @Autowired
    PubDocumentSequencesMapper pubDocumentSequencesMapper;

    public PubDocumentSequences lockSequence(IRequest request, PubDocumentSequences pubDocumentSequences) {
        PubDocumentSequences resultSeqNumber = this.pubDocumentSequencesMapper.lockDocSeqNumber(pubDocumentSequences);
        return resultSeqNumber;
    }

    public PubDocumentSequences updateSequenct(IRequest request, PubDocumentSequences pubDocumentSequences) {
        pubDocumentSequencesMapper.updateDocSeq(pubDocumentSequences);
        return pubDocumentSequences;
    }

    public PubDocumentSequences insertSequence(IRequest request, PubDocumentSequences pubDocumentSequences) {
        pubDocumentSequencesMapper.insertDosSeq(pubDocumentSequences);
        return pubDocumentSequences;
    }

    public void setSequence(String name, Long length, String preFix, String concatChar, String cycType, PubDocumentSequences pubDocumentSequences) {
        pubDocumentSequences.setName(name);
        pubDocumentSequences.setCycType(cycType);
        pubDocumentSequences.setLength(length);
        pubDocumentSequences.setPrefixChar(preFix);
        pubDocumentSequences.setConcatChar(concatChar);
        pubDocumentSequences.setType("a");
        pubDocumentSequences.setEnabledFlag("y");
    }

    public String getDateStr(PubDocumentSequences pubDocumentSequences) {
        String dateStr = "";
        Date now = new Date();
        if (pubDocumentSequences.getCycType().equals("day")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            dateStr = sdf.format(now);
        } else if (pubDocumentSequences.getCycType().equals("month")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            dateStr = sdf.format(now);
        } else if (pubDocumentSequences.getCycType().equals("year")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            dateStr = sdf.format(now);
        } else if (pubDocumentSequences.getCycType().equals("always")) {
            dateStr = "";
        }
        return dateStr;
    }

    public PubDocumentSequences processSequence(IRequest request, PubDocumentSequences pubDocumentSequences) {
        PubDocumentSequences getResultSequence = ((IPubDocumentSequencesService) this.self()).lockSequence(request, pubDocumentSequences);
        String dateStr = getDateStr(pubDocumentSequences);
        if (getResultSequence != null) {
            if (getResultSequence.getInclCycPrefix().equals(dateStr)) {
                getResultSequence.setNextSeqValue(getResultSequence.getNextSeqValue() + 1);
                ((IPubDocumentSequencesService) this.self()).updateSequenct(request, getResultSequence);
            } else {
                getResultSequence = pubDocumentSequences;
                getResultSequence.setInclCycPrefix(dateStr);
                getResultSequence.setNextSeqValue(1L);
                ((IPubDocumentSequencesService) this.self()).updateSequenct(request, getResultSequence);
            }
        } else {
            getResultSequence = pubDocumentSequences;
            getResultSequence.setNextSeqValue(1L);
            getResultSequence.setInclCycPrefix(dateStr);
            ((IPubDocumentSequencesService) this.self()).insertSequence(request, getResultSequence);
        }
        return getResultSequence;
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public String getDocSequence(IRequest request, String name, String preFix, Long length, String concatChar, String cycType) {
        PubDocumentSequences pubDocumentSequences = new PubDocumentSequences();
        setSequence(name, length, preFix, concatChar, cycType, pubDocumentSequences);
        String sequence = "";

        if (pubDocumentSequences.getName() != null) {
            PubDocumentSequences result = new PubDocumentSequences();
            result = ((IPubDocumentSequencesService) this.self()).processSequence(request, pubDocumentSequences);
            String seqNumber = result.getNextSeqValue().toString();
            Integer len = Integer.parseInt(String.valueOf(result.getLength()));
            if (seqNumber != null && seqNumber.length() < length) {
                sequence = result.getPrefixChar() + StringUtils.leftPad(seqNumber, len, '0');
            } else {
                sequence = result.getPrefixChar() + seqNumber;
            }
        }
        return sequence;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public String getSeqNumber(IRequest request, String name, String preFix, Long length, String concatChar, String cycType) {
        PubDocumentSequences pubDocumentSequences = new PubDocumentSequences();
        setSequence(name, length, preFix, concatChar, cycType, pubDocumentSequences);
        String seqNumber = "";
        if (pubDocumentSequences.getName() != null) {
            PubDocumentSequences result = new PubDocumentSequences();
            result = ((IPubDocumentSequencesService) this.self()).processSequence(request, pubDocumentSequences);
            seqNumber = result.getNextSeqValue().toString();
        }
        return seqNumber;
    }

    public Long getSeqCurrVal(String sequenceName) {
        return pubDocumentSequencesMapper.getSeqCurrVal(sequenceName);
    }

    public Long getSeqNextVal(String sequenceName) {
        return pubDocumentSequencesMapper.getSeqNextVal(sequenceName);
    }
}