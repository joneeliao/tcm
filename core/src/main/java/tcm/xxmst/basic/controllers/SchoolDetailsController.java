package tcm.xxmst.basic.controllers;

import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.code.rule.service.ISysCodeRuleProcessService;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import tcm.xxmst.basic.dto.SchoolDetails;
import tcm.xxmst.basic.service.ISchoolDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SchoolDetailsController extends BaseController {

    @Autowired
    private ISchoolDetailsService service;
    @Autowired
    ISysCodeRuleProcessService codeRuleProcessService;


    @RequestMapping(value = "/xxmst/school/details/query")
    @ResponseBody
    public ResponseData query(SchoolDetails dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectSchool(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/xxmst/school/details/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<SchoolDetails> dto,
                               BindingResult result,
                               HttpServletRequest request) throws CodeRuleException {
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestCtx = createRequestContext(request);

        //Generate Code
        for (int i = 0; i < dto.size(); i++) {
            SchoolDetails school = dto.get(i);
            if (school.getSchoolId() == null || "".equals(school.getSchoolNumber())) {
                school.setSchoolNumber("1" + codeRuleProcessService.getRuleCode("XXMST_SCHOOL_NUM"));
                dto.set(i, school);
            }
        }
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/xxmst/school/details/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<SchoolDetails> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}