package tcm.xxcse.basic.controllers;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tcm.xxcse.basic.dto.AttendClassRecord;
import tcm.xxcse.basic.service.IAttendClassRecordService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AttendClassRecordController extends BaseController {

    @Autowired
    private IAttendClassRecordService service;


    @RequestMapping(value = "/xxcse/attend/class/record/query")
    @ResponseBody
    public ResponseData query(AttendClassRecord dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.queryData(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/xxcse/attend/class/record/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<AttendClassRecord> dto, BindingResult result, HttpServletRequest request) {
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/xxcse/attend/class/record/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<AttendClassRecord> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-MM-dd'T'HH:mm:ssZ example
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}