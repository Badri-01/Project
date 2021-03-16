package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component 
@Document("defects")
public class Comments {
private String description;
private String userId;
private String defectId;
private String date;
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getDefectId() {
	return defectId;
}
public void setDefectId(String defectId) {
	this.defectId = defectId;
}

}
