package priv.jesse.mall.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import priv.jesse.mall.entity.pojo.ResultBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.Set;

/**
 * 统一异常处理
 * 在Controller中抛出的异常，GlobalExceptionHandler中定义的处理方法可以起作用
 * 其他的业务层异常也可以单独处理
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 默认的异常处理
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        //记录日志
        LOGGER.error(e.getMessage(), e);
        e.printStackTrace();
        ResultBean<String> r = new ResultBean<>(e);
        r.setData(req.getRequestURI());
        return r;
    }

    @ExceptionHandler(value = RuntimeException.class)
    public void runtimeExceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception {
        //记录日志
        LOGGER.error(e.getMessage(), e);
        req.setAttribute("msg", e.getMessage());
        //转发到error页面
        req.getRequestDispatcher("/mall/user/error.html").forward(req, res);
    }

    /**
     * 处理validation异常
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ResultBean<String> validationExceptionHandler(HttpServletRequest req, ConstraintViolationException e) throws Exception {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage() + ",");
        }
        LOGGER.error(strBuilder.toString(), e);
        ResultBean<String> r = new ResultBean(strBuilder.toString());
        r.setData(req.getRequestURI());
        return r;
    }


}
