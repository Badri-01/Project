package com.project.app.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
@Component
@Document("defectsHistory")
public class DefectHistory {

private String defectId;
@Autowired
private List<DefectStatus> defectStatus;


public String getDefectId() {
	return defectId;
}
public void setDefectId(String defectId) {
	this.defectId = defectId;
}
public List<DefectStatus> getDefectStatus() {
	return defectStatus;
}
public void setDefectStatus(List<DefectStatus> defectStatus) {
	this.defectStatus = defectStatus;
}
}
