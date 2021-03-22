package com.project.app.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.app.exception.DefectNotFoundExceptin;
import com.project.app.exception.DefectRemovedException;
import com.project.app.model.Comments;
import com.project.app.model.Count;
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
private Count count;
	@Override
	public Defects updateDefectDetails(Defects defect) throws InvalidAttributesException, DefectNotFoundExceptin {
		// TODO Auto-generated method stub
		defect.setLastUpdateDate(getTimeStamp());
		defectRepository.update(defect);
		updateDefectHistory(defect);
		return defect;
		
		
	}

	private void updateDefectHistory(Defects defect) {
		// TODO Auto-generated method stub
		DefectHistory defectHistory =new DefectHistory();
		DefectStatus defectStatus = new DefectStatus();
		
		
		
			defectStatus.setStatus(defect.getStatus());
			defectStatus.setUpdateDate(defect.getLastUpdateDate());
			defectHistory.setDefectId(defect.getDefectId());
			defectStatus.setUserId(defect.getUserId());
			defectStatus.setSeverity(defect.getSeverity());
			ArrayList<DefectStatus> defectStatusnew =new ArrayList<>();
			defectStatusnew.add(defectStatus);
			defectHistory.setDefectStatus(defectStatusnew);
			defectHistoryRepo.save(defectHistory);
			}
		
	

	@Override
	public JSONObject findDefectByID(String defectID) throws DefectNotFoundExceptin {
		// TODO Auto-generated method stub
		Defects defect=defectRepository.finddefectById(defectID);
		if(defect==null) {
			throw new DefectNotFoundExceptin(defectID);
		}
		JSONObject newJson =new JSONObject(defectRepository.finddefectById(defectID));
		
		newJson.put("defectHistory",defectHistoryRepo.finddefectHistoryById(defectID));
		
		
		return newJson;
	}

	@Override
	public List getAllDefects() {
		// TODO Auto-generated method stub
		return defectRepository.findAll();
	}

	@Override
	public Defects newDefect(Defects defect) throws InvalidAttributesException {
		// TODO Auto-generated method stub
		int newCount=getCount()+1;
		defect.setDefectId("D_"+newCount);
		defect.setStatus("new");
		defect.setCreateDate(getTimeStamp());
		defect.setLastUpdateDate(getTimeStamp());
		
		updateCount(newCount);
		defectRepository.save(defect);
		updateDefectHistory(defect);
		return defect;
	}

	private void updateCount(int count) {
		// TODO Auto-generated method stub
		defectRepository.updateCountId(count);
	}

	private int getCount() {
		// TODO Auto-generated method stub
		return defectRepository.getCountById();
	}

	@Override
	public void deleteDefect(String defectID) throws InvalidAttributesException, DefectNotFoundExceptin, DefectRemovedException {
		// TODO Auto-generated method stub
		Defects defect=defectRepository.finddefectById(defectID);
		if(defect==null) {
			throw new DefectNotFoundExceptin(defectID);
		}else if(defect.getStatus().equals("close")) {
			throw new DefectRemovedException(defectID);
		}
		defect.setStatus("close");
		updateDefectHistory(defect);
		defectRepository.deleteById(defectID);
	}
	public static String getTimeStamp() {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		return sdf.format(timestamp);

	}

	
	@Override
	public void addComment(Comments comment,String defectId) throws DefectNotFoundExceptin, DefectRemovedException {
		// TODO Auto-generated method stub
		Defects defect=defectRepository.finddefectById(defectId);
		if(defect==null) {
			throw new DefectNotFoundExceptin(defectId);
		}else if(defect.getStatus().equals("close")) {
			throw new DefectRemovedException(defectId);
		}
		
		defectRepository.addComments(defectId,comment,defect);
		
	}

	@Override
	public List<DefectHistory> findDefectHistoryByID(String defectID) {
		// TODO Auto-generated method stub
		return defectHistoryRepo.finddefectHistoryById(defectID);
	}

	@Override
	public void addAttachment(Defects defect,String defectId) throws DefectNotFoundExceptin, DefectRemovedException {
		// TODO Auto-generated method stub
		Defects newdefect=defectRepository.finddefectById(defectId);
		if(newdefect==null) {
			throw new DefectNotFoundExceptin(defectId);
		}else if(newdefect.getStatus().equals("close")) {
			throw new DefectRemovedException(defectId);
		}
		
		defectRepository.addAttachment(defectId,defect,newdefect);
		
	}
}
