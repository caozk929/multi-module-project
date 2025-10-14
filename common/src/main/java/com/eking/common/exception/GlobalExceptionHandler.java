package com.eking.common.exception;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.eking.common.mvc.BaseResp;
import com.eking.common.util.QYWXUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>描述：
 * <p>全局异常拦截器，
 * <p>此处需要注意的是包路径问题
 * <p>对外提供的FeignClient， 理论上仍然是RestController 如果消费者调用出现问题，
 * <p>会进入该全局异常拦截器，消费者获取的数据出现问题
 * <p>因此需要严格区分对外提供的接口以及前端直接访问的接口 同时强制指定前端访问接口的包路径
 *
 * @author 00158719
 * @version 1.0
 * @since 2023/6/19 10:26
 */
@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 参数校验
     *
     * @param exception MethodArgumentNotValidException异常
     * @return 统一的返回数据结构
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public final BaseResp<?> handlerMethodArgumentNotValidException(Exception exception) {
        log.error("服务异常: {}", exception.getMessage(), exception);
        MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
        FieldError fieldError = methodArgumentNotValidException.getBindingResult().getFieldError();
        assert fieldError != null;
        BaseResp<?> baseResp = BaseResp.failure();
        baseResp.setCode(CommonError.ERROR_PARAMS.getCode());
        baseResp.setMsg(fieldError.getDefaultMessage());
        return baseResp;
    }

    /**
     * 对BaseRuntimeException异常的处理
     *
     * @param exception BaseRuntimeException异常
     * @return 统一的返回数据结构
     */
    @ExceptionHandler(CustomException.class)
    public final BaseResp<?> handler(Exception exception) {
        CustomException customException = (CustomException) exception;
        log.error("服务异常: {}", customException.getMessage(), customException);
        BaseResp<?> baseResp = BaseResp.failure();
        baseResp.setCode(customException.getCode());
        baseResp.setMsg(customException.getMessage());
        return baseResp;

    }


    /**
     * 对Exception异常的处理
     *
     * @param exception Exception异常
     * @return 统一的返回数据结构
     */
    @ExceptionHandler(Exception.class)
    public final BaseResp<?> handlerException(Exception exception) {
        log.error("服务异常: {}", exception.getMessage(), exception);
        BaseResp<?> baseResp = BaseResp.failure();
        baseResp.setCode(CommonError.ERROR_PARAMS.getCode());
        baseResp.setMsg(exception.getMessage());
        QYWXUtil.sendMsg("服务异常:" + ExceptionUtil.stacktraceToString(exception));
        return baseResp;

    }

}
