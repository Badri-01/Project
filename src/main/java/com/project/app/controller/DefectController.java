package com.project.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.model.Comments;
import com.project.app.model.Defects;
import com.project.app.repository.DefectRepository;
import com.project.app.service.DefectServiceInt;

@RestController

@RequestMapping("/defects")
public class DefectController {
	@Autowired
	private DefectServiceInt defectService;
	
	@PutMapping("/update/{defectId}")
    public Defects updateDefect(@RequestBody Defects defect, @PathVariable String defectId) {
		defect.setDefectId(defectId);
        defectService.updateDefectDetails(defect);
        return defect;
    }
	@GetMapping("/get/{defectID}")
	public String findDefect ( @PathVariable String defectID){
	
	    return defectService.findDefectByID(defectID).toString();
	 }
	@GetMapping("/history/{defectID}")
	public JSONObject findDefectHistory ( @PathVariable String defectID){
	    return defectService.findDefectHistoryByID(defectID);
	 }

		
	@GetMapping
	public List listDefects(){
	    return defectService.getAllDefects();
	 }
	@PutMapping("/comment/{defectId}")
    public String updateComment(@RequestBody Comments comment, @PathVariable String defectId) {
		
        defectService.addComment(comment, defectId);
        return "OK";
    }
	@PostMapping
	public Defects createDefect(@Valid @RequestBody Defects defect) {
		defectService.newDefect(defect);
		return defect;
	}
	
	
	@DeleteMapping("/delete/{defectID}")
	public String DeleteById( @PathVariable String defectID){
		defectService.deleteDefect(defectID);
	    return "OK";
	 }
}
