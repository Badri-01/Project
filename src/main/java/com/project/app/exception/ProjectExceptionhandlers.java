package com.project.app.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProjectExceptionhandlers {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleValidationException(MethodArgumentNotValidException ex) {
		String field=ex.getFieldError().getField();
		return field+" "+ex.getFieldError(field).getDefaultMessage();
	}
	@ExceptionHandler(ProjectNotfoundexception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleProjectNotfoundException(ProjectNotfoundexception ex){
		return "Project with "+ex.getMessage()+" not found";
		
	}
	@ExceptionHandler(RequirementNotFoundexception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleRequirementNotfoundException(RequirementNotFoundexception ex){
		return "Requirement with "+ex.getMessage()+" not found";
		
	}
	@ExceptionHandler(TestcaseNotfoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleTestcaseNotfoundException(TestcaseNotfoundException ex){
		return "Testcase with "+ex.getMessage()+" not found";
		
	}
	
}
