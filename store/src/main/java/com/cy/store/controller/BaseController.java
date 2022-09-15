package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.JsonResult;
import com.cy.store.service.ex.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class BaseController {

    public static final int OK = 200;

    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> HandlerException(Throwable ex){
        JsonResult<Void> voidJsonResult = new JsonResult<>();
        //判断是什么异常并存入记录异常信息的实体类中
        if (ex instanceof UsernameDuplicateException){
            voidJsonResult.setState(4000);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof UserNoFoundException){
            voidJsonResult.setState(4001);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof PasswordNotMatchException){
            voidJsonResult.setState(4002);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof UpdateException){
            voidJsonResult.setState(4003);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof AccessDeniedException){
            voidJsonResult.setState(4004);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof AddressNotFoundException){
            voidJsonResult.setState(4005);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof DeleteException){
            voidJsonResult.setState(4006);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof ProductNotFoundException){
            voidJsonResult.setState(4007);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof ProductStatusException){
            voidJsonResult.setState(4008);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof AddressCountLimitException){
            voidJsonResult.setState(4009);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof CartNotFoundException){
            voidJsonResult.setState(4010);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof InsertException){
            voidJsonResult.setState(5000);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof FileEmptyException){
            voidJsonResult.setState(6001);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof FileSizeException){
            voidJsonResult.setState(6002);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof FileStateException){
            voidJsonResult.setState(6003);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof FileTypeException){
            voidJsonResult.setState(6004);
            voidJsonResult.setMessage(ex.getMessage());
        }else if (ex instanceof FileUploadIOException){
            voidJsonResult.setState(6005);
            voidJsonResult.setMessage(ex.getMessage());
        }
        return voidJsonResult;
    }
}
