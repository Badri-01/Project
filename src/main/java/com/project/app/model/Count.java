package com.project.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document("count")
@Component
public class Count {
private int count;
@Id
private String countId;
public int getCount() {
	return count;
}

public void setCount(int count) {
	this.count = count;
}

public String getCountId() {
	return countId;
}

public void setCountId(String countId) {
	this.countId = countId;
}
}
