package com.project.app.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
@Component
@Document("defectsHistory")
public class DefectHistory {

private String defectId;

private ArrayList<DefectStatus> defectStatus=new ArrayList<>();


public String getDefectId() {
	return defectId;
}
public void setDefectId(String defectId) {
	this.defectId = defectId;
}
public ArrayList<DefectStatus> getDefectStatus() {
	return defectStatus;
}
public void setDefectStatus(ArrayList<DefectStatus> defectStatus) {
	this.defectStatus = defectStatus;
}
}
