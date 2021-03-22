package com.project.app.controller;

import java.util.List;

import javax.naming.directory.InvalidAttributesException;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.exception.DefectNotFoundExceptin;
import com.project.app.exception.DefectRemovedException;
import com.project.app.exception.user.UserNotFoundException;
import com.project.app.model.Comments;
import com.project.app.model.DefectHistory;
import com.project.app.model.Defects;

import com.project.app.service.DefectServiceInt;

@RestController

@RequestMapping("/defects")
public class DefectController {
	@Autowired
	private DefectServiceInt defectService;
	
	@PutMapping("/update/{defectId}")
    public Defects updateDefect(@RequestBody Defects defect, @PathVariable String defectId) throws InvalidAttributesException, DefectNotFoundExceptin {
		defect.setDefectId(defectId);
        defectService.updateDefectDetails(defect);
        return defect;
    }
	@GetMapping("/get/{defectID}")
	public String findDefect ( @PathVariable String defectID) throws DefectNotFoundExceptin{
	
	    return defectService.findDefectByID(defectID).toString();
	 }
	@GetMapping("/history/{defectID}")
	public List<DefectHistory> findDefectHistory ( @PathVariable String defectID){
	    return defectService.findDefectHistoryByID(defectID);
	 }

		
	@GetMapping
	public List listDefects(){
	    return defectService.getAllDefects();
	 }
	@PutMapping("/comment/{defectId}")
    public String updateComment(@Valid @RequestBody Comments comment, @PathVariable String defectId) throws DefectNotFoundExceptin, DefectRemovedException {
		
        defectService.addComment(comment, defectId);
        return "OK";
    }
	@PostMapping
	public Defects createDefect(@Valid @RequestBody Defects defect) throws InvalidAttributesException {
		defectService.newDefect(defect);
		return defect;
	}
	@PutMapping("/addAttachment/{defectId}")
	public Defects addAttachments( @RequestBody Defects defect,@PathVariable String defectId) throws InvalidAttributesException, DefectNotFoundExceptin, DefectRemovedException {
		System.out.println(defectId);
		defectService.addAttachment(defect,defectId);
		return defect;
	}
	@ExceptionHandler(DefectNotFoundExceptin.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleDefectNotFoundException(DefectNotFoundExceptin ex)
	{
	  return "Defect "+ex.getMessage()+" not found";
	}
	@ExceptionHandler(DefectRemovedException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody String handleDefectRemovedException(DefectRemovedException ex)
	{
	  return "Defect "+ex.getMessage()+"is closed";
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody String handleValidationException(MethodArgumentNotValidException ex) {
		String field=ex.getFieldError().getField();
		return "Field: "+ex.getFieldError(field).getDefaultMessage();
	}
	@ExceptionHandler(InvalidAttributesException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody String handleInValidAttributesException(InvalidAttributesException ex) {
		return  ex.getMessage();
		
	}
	
	@DeleteMapping("/delete/{defectID}")
	public String DeleteById( @PathVariable String defectID) throws InvalidAttributesException, DefectNotFoundExceptin, DefectRemovedException{
		defectService.deleteDefect(defectID);
	    return "OK";
	 }
}
