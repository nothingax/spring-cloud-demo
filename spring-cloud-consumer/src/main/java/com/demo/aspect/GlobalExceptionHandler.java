package com.demo.aspect;

import com.demo.common.WebResp;
import com.demo.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description: rest controller 统一异常处理
 *
 *
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/20 3:08 PM
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private boolean includeQueryString = true;

    private boolean includeClientInfo = true;

    private boolean includeHeaders = true;

    private boolean includePayload = true;
    private int maxPayloadLength = 50;

    /**
     * 业务异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BizException exceptionHandle(BizException e) {
        throw e;
    }


    /**
     * 其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WebResp exceptionHandle(Exception e, HttpServletRequest request) {

        // log.error(request.getParameterMap().toString());
        log.error("系统异常 ====================================", e);
        // log.error(getBeforeMessage(request));
        //
        return WebResp.error(e.getMessage());
        // throw new BizException(e.getMessage(), e.getCause());
    }








    private String getBeforeMessage(HttpServletRequest request) {
        StringBuilder msg = new StringBuilder();
        msg.append("uri=").append(request.getRequestURI());

        if (this.includeQueryString) {
            String queryString = request.getQueryString();
            if (queryString != null) {
                msg.append('?').append(queryString);
            }
        }
        if (includeClientInfo) {
            String client = request.getRemoteAddr();
            if (StringUtils.hasLength(client)) {
                msg.append(";client=").append(client);
            }
            HttpSession session = request.getSession(false);
            if (session != null) {
                msg.append(";session=").append(session.getId());
            }
            String user = request.getRemoteUser();
            if (user != null) {
                msg.append(";user=").append(user);
            }
        }

        if (includeHeaders) {
            HttpHeaders headers = new ServletServerHttpRequest(request).getHeaders();
            msg.append(";headers=").append(headers);
        }

        if (includePayload) {
            String payload = getMessagePayload(request);
            if (payload != null) {
                msg.append(";payload=").append(payload);
            }
        }

        return msg.toString();
    }


    private String getMessagePayload(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper =
                WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, maxPayloadLength);
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                }
                catch (UnsupportedEncodingException ex) {
                    return "[unknown]";
                }
            }
        }
        return null;
    }

}
