package com.youpd.phototsrv.config;

import com.youpd.phototsrv.model.BaseResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public BaseResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
    LOGGER.error("{}", e);
    BaseResponse r = new BaseResponse();
    r.setResultMessage(e.getMessage());
    if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
      r.setResultCode("404");
    } else {
      r.setResultCode("500");
    }
    r.setResult(null);
    r.setSuccess(false);
    return r;
  }
}