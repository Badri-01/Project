package com.project.app.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.app.model.Comments;
import com.project.app.model.DefectHistory;
import com.project.app.model.DefectStatus;
import com.project.app.model.Defects;
import com.project.app.repository.DefectHistoryRepository;
import com.project.app.repository.DefectRepository;
@Component
public class DefectServiceImpl implements DefectServiceInt{
@Autowired
DefectRepository defectRepository;
@Autowired
DefectHistoryRepository defectHistoryRepo;
@Autowired
DefectHistory defectHistory;
@Autowired
DefectStatus defectStatus;
private int count=1;
	@Override
	public void updateDefectDetails(Defects defect) {
		// TODO Auto-generated method stub
		defect.setLastUpdateDate(getTimeStamp());
		updateDefectHistory(defect);
		defectRepository.update(defect);
		
	}

	private void updateDefectHistory(Defects defect) {
		// TODO Auto-generated method stub
		Defects oldDefect=defectRepository.finddefectById(defect.getDefectId());
		
		if(!oldDefect.getStatus().equals(defect.getStatus())||!oldDefect.getUserId().equals(defect.getUserId())) {
			if(oldDefect.getStatus().equalsIgnoreCase("open")) {
				defectStatus.setFromDate(oldDefect.getCreateDate());
				
			}else {
				defectStatus.setFromDate(oldDefect.getLastUpdateDate());
			}
			defectStatus.setStatus(oldDefect.getStatus());
			defectStatus.setToDate(defect.getLastUpdateDate());
			defectHistory.setDefectId(defect.getDefectId());
			defectStatus.setUserId(oldDefect.getUserId());
			defectStatus.setSeverity(oldDefect.getSeverity());
			defectHistory.getDefectStatus().add(defectStatus);
			System.out.println("test");
			defectHistoryRepo.save(defectHistory);
		}
		
	}

	@Override
	public JSONObject findDefectByID(String defectID) {
		// TODO Auto-generated method stub
		JSONObject newJson =new JSONObject(defectRepository.finddefectById(defectID));
		System.out.println(newJson);
		newJson.put("defectHistory",defectHistoryRepo.finddefectHistoryById(defectID));
		
		System.out.println(newJson);
		return newJson;
	}

	@Override
	public List getAllDefects() {
		// TODO Auto-generated method stub
		return defectRepository.findAll();
	}

	@Override
	public void newDefect(Defects defect) {
		// TODO Auto-generated method stub
		
		defect.setCreateDate(getTimeStamp());
		defectRepository.save(defect);
	}

	@Override
	public void deleteDefect(String defectID) {
		// TODO Auto-generated method stub
		defectRepository.deleteById(defectID);
	}
	public static String getTimeStamp() {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		return sdf.format(timestamp);

	}

	
	@Override
	public void addComment(Comments comment,String defectId) {
		// TODO Auto-generated method stub
		Defects defect=defectRepository.finddefectById(defectId);
		if(defect.getComment()==null) {
			List<Comments> commentList=new ArrayList<>();
			commentList.add(comment);
			defect.setComment(commentList);
		}else {
			List<Comments> commentList=	defect.getComment();;
		commentList.add(comment);
		defect.setComment(commentList);
		}
		defectRepository.save(defect);
		
	}

	@Override
	public JSONObject findDefectHistoryByID(String defectID) {
		// TODO Auto-generated method stub
		return new JSONObject(defectHistoryRepo.finddefectHistoryById(defectID));
	}
}
