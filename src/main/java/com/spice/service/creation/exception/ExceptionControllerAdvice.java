package com.spice.service.creation.exception;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spice.service.creation.response.FieldErrorDTO;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.response.ValidationErrorDTO;
import com.spice.service.creation.utility.ErrorCode;

@ControllerAdvice(annotations=RestController.class)
public class ExceptionControllerAdvice {

	@Autowired
	private MessageSource messageSource;  
	
	@ExceptionHandler(value = { GenericException.class })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseObj genericException(HttpServletRequest request,GenericException ex) {
			 return new ResponseObj(null,ex.getStatus(), ex.getErrorDescription(),ex.getCode());
	}
	
	@ExceptionHandler(value = {Exception.class })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseObj handleException(HttpServletRequest request, Exception ex) {
		ex.printStackTrace();
		int code = ErrorCode.INTERNAL_SYSTEM_ERROR.getStatus();
		String errorMessage = ErrorCode.INTERNAL_SYSTEM_ERROR.getMessage(messageSource, null,
				LocaleContextHolder.getLocale());
		return new ResponseObj(null, "Failure",errorMessage,code);
	}
	
	@ExceptionHandler(value = { EmptyResultDataAccessException.class })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseObj EmptyResultDataHandlr(HttpServletRequest request,EmptyResultDataAccessException ex) {
			 return new ResponseObj(null,"Success","No Record Found",125);
	}
	
	@ExceptionHandler(value = { ConstraintException.class })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseObj handleException(HttpServletRequest request, ConstraintException ex) {
		Locale aLocale = Locale.forLanguageTag("en");
		String message = messageSource.getMessage("105", new Object[] { ex.getMessage() }, aLocale);
		 return new ResponseObj(null,"Failure",message,105);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseObj processValidationError(MethodArgumentNotValidException ex, Locale locale) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationErrorDTO errorDTO = processFieldErrors(fieldErrors, locale);
        return new ResponseObj(null ,"Failed",errorDTO.getFieldError().getMessage(),120);
    }
 
    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors, Locale locale) {
        ValidationErrorDTO dto = new ValidationErrorDTO();
        String defaultMessage = "105";
		String field = "";
        for (FieldError fieldError: fieldErrors) {
        	defaultMessage = fieldError.getDefaultMessage();
			field = fieldError.getField();
			String errorMessage = messageSource.getMessage(defaultMessage, new Object[] { field }, locale);
			dto.setFieldError(new FieldErrorDTO(field, errorMessage));
        }
        return dto;
    }
 }