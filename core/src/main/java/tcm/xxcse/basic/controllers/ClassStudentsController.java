package tcm.xxcse.basic.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import tcm.xxcse.basic.dto.ClassStudents;
import tcm.xxcse.basic.service.IClassStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class ClassStudentsController extends BaseController{

    @Autowired
    private IClassStudentsService service;


    @RequestMapping(value = "/xxcse/class/students/query")
    @ResponseBody
    public ResponseData query(ClassStudents dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/xxcse/class/students/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<ClassStudents> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/xxcse/class/students/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<ClassStudents> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }