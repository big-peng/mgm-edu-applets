package com.yy.server.exception.handler;

import com.yy.server.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenXiangpeng
 * @ClassName GlobalExceptionHandler
 * @date：2019/11/16
 * @version: V1.0.0
 * @description：全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map handle(Exception exception, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();

        if (exception instanceof MethodArgumentNotValidException) {
            logger.error("数据校验异常：", exception);

            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;

            String message = methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage();
            map = JsonUtil.toJsonError(-1,message);
        } else if (exception instanceof HttpMessageNotReadableException) {
            logger.error("json转换错误", exception);
            HttpMessageNotReadableException httpMessageNotReadableException = (HttpMessageNotReadableException) exception;
            logger.error("json转换错误" + httpMessageNotReadableException.getLocalizedMessage());
            map = JsonUtil.toJsonError(-1,"数据转换错误");
        } else {
            logger.error("服务运行异常：", exception);

            map = JsonUtil.toJsonError(-1,"服务运行异常");
        }
        return map;
    }
}
