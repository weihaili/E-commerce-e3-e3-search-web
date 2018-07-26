package cn.kkl.mall.search.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Admin
 * global exception handler
 * deal step:
 * 1. print console
 * 2. write log
 * 3. send email and send message(use jmail tool) use third party provide webService
 * 4. display fail page
 * 
 * logger level sort from low to high:
 * debug
 * info
 * caveat warn
 * error
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class); 

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		exception.printStackTrace();
		logger.error( "system occur exception", exception);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error/exception");
		
		return modelAndView;
	}

}
