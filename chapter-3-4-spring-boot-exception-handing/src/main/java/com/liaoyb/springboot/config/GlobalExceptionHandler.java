package com.liaoyb.springboot.config;

import com.liaoyb.springboot.dto.ErrorInfo;
import com.liaoyb.springboot.dto.Result;
import com.liaoyb.springboot.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 *
 * @author liaoyb
 * @date 2018-07-01 22:02
 */
@Slf4j
@ControllerAdvice
class GlobalExceptionHandler {
//
//    /**
//     * 返回错误页面
//     *
//     * @param req
//     * @param e
//     * @return
//     * @throws Exception
//     */
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("error");
//        return mav;
//    }
//
//
//    /**
//     * 返回json格式的数据
//     *
//     * @param req
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = CustomException.class)
//    @ResponseBody
//    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, Throwable e) {
//        ErrorInfo<String> errorInfo = new ErrorInfo<>();
//        errorInfo.setMessage(e.getMessage());
//        errorInfo.setData("错误数据");
//        errorInfo.setUrl(req.getRequestURL().toString());
//        return errorInfo;
//    }


    /**
     * 统一处理
     *
     * @param req
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public <T> Result<T> exceptionHandler(HttpServletRequest req, Throwable e) {
        log.error("resolve exception", e);
        if (e instanceof CustomException) {
            log.error("业务异常:" + e.getClass());
            return new Result<>(e);
        }
        //未知错误
        return Result.error("系统异常：\\n" + e);
    }

}
