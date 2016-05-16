package com.dreamy.lgh.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangyongxing on 16/4/8.
 */
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(CustomSimpleMappingExceptionResolver.class);

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
     * #logException(java.lang.Exception, javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected void logException(Exception ex, HttpServletRequest request) {
        // super.logException(ex, request);
        String uri = request.getRequestURI();
        log.error("Uncaught exception for  at [" + request.getMethod() + ":" + uri + "]", ex);
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                              Exception ex) {
        request.setAttribute("errorMessage", ex.getMessage());

        return new ModelAndView("/error/error");
    }
}
