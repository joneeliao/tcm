package tcm.xxcse.basic.controllers;

import hap.common.service.IPubDocumentSequencesService;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import tcm.utils.StringUtils;
import tcm.xxcse.basic.dto.ClassInfo;
import tcm.xxcse.basic.service.IClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
public class ClassInfoController extends BaseController {

    @Autowired
    private IClassInfoService service;
    @Autowired
    private IPubDocumentSequencesService sequencesService;


    @RequestMapping(value = "/xxcse/class/info/query")
    @ResponseBody
    public ResponseData query(ClassInfo dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.queryData(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/xxcse/class/info/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<ClassInfo> dto, BindingResult result, HttpServletRequest request) {
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestCtx = createRequestContext(request);

        //Generate Code
        for (int i = 0; i < dto.size(); i++) {
            ClassInfo classInfo = dto.get(i);
            if (classInfo.getClassId() == null || "".equals(classInfo.getClassNum())) {
                String indexStr = classInfo.getSchoolNum() + classInfo.getCourseCode() + classInfo.getTeacherNum();
                String sequenceStr = StringUtils.appendString(sequencesService.getSeqNextVal(indexStr) + "" + "", 2, "L", "0");
                classInfo.setClassNum(indexStr + sequenceStr);
                dto.set(i, classInfo);
            }
        }

        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/xxcse/class/info/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<ClassInfo> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}