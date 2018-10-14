package tcm.xxmst.basic.controllers;

import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.hap.code.rule.service.ISysCodeRuleProcessService;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import tcm.xxmst.basic.dto.TeachersDetails;
import tcm.xxmst.basic.service.ITeachersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
public class TeachersDetailsController extends BaseController {

    @Autowired
    private ITeachersDetailsService service;
    @Autowired
    ISysCodeRuleProcessService codeRuleProcessService;


    @RequestMapping(value = "/xxmst/teachers/details/query")
    @ResponseBody
    public ResponseData query(TeachersDetails dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/xxmst/teachers/details/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<TeachersDetails> dto,
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
            TeachersDetails teacher = dto.get(i);
            if (teacher.getTeacherId() == null || "".equals(teacher.getTeacherNum())) {
                teacher.setTeacherNum("3" + codeRuleProcessService.getRuleCode("XXMST_TEACHER_NUM"));
                dto.set(i, teacher);
            }
        }
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/xxmst/teachers/details/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<TeachersDetails> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}