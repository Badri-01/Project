package com.project.app.service;

import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.project.app.exception.DefectNotFoundExceptin;
import com.project.app.exception.DefectRemovedException;
import com.project.app.model.Comments;
import com.project.app.model.DefectHistory;
import com.project.app.model.Defects;
@Service
public interface DefectServiceInt {

	Defects updateDefectDetails(Defects defect) throws InvalidAttributesException, DefectNotFoundExceptin;
    JSONObject findDefectByID(String defectID) throws DefectNotFoundExceptin;
    List getAllDefects();
    Defects newDefect(Defects defect) throws InvalidAttributesException;
    void deleteDefect(String defectID) throws DefectNotFoundExceptin, InvalidAttributesException, DefectRemovedException;
    void addComment(Comments comment,String defectId)throws DefectNotFoundExceptin, DefectRemovedException;
	List<DefectHistory> findDefectHistoryByID(String defectID);
	void addAttachment(Defects defect, String defectID) throws DefectNotFoundExceptin, DefectRemovedException;
}
