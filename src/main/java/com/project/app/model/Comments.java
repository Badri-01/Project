package com.project.app.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component 
@Document("defects")
public class Comments {
	@NotNull(message ="Comment description should be provided")
private String description;
	@NotNull(message ="User id should be provided for adding comments")
private String userId;

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

}
