package com.project.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.project.app.model.DefectHistory;
import com.project.app.model.Defects;

@Repository
public class DefectHistoryRepository {

	   @Autowired
	    private MongoTemplate mongoTemplate;
	   public List<DefectHistory> finddefectHistoryById(String defectId) {
			// TODO Auto-generated method stub
		   System.out.println(defectId);
			  Query query = new Query();
		        query.addCriteria(Criteria.where("defectId").is(defectId));
		       
		       
		        return mongoTemplate.find(query, DefectHistory.class);
			
		}
	   public void save(DefectHistory defectHistory) {
			// TODO Auto-generated method stub
		   List<DefectHistory> oldDefectHistory=new ArrayList<>();
		   if(finddefectHistoryById(defectHistory.getDefectId()).size()==0) {
			   mongoTemplate.save(defectHistory);
			  
		   }else {
			System.out.println("before saving defect history");
		   }
			 oldDefectHistory=   finddefectHistoryById(defectHistory.getDefectId());
			Update update =new Update();
	    	if(oldDefectHistory.get(0).getDefectStatus()==null) {
	    		update.push("defectStatus", defectHistory.getDefectStatus().get(0));
				
			}else {
				
				update.addToSet("defectStatus", defectHistory.getDefectStatus().get(0));
			}
	    	
	    	Criteria criteria = Criteria.where("defectId").is(defectHistory.getDefectId());
	    System.out.println(	mongoTemplate.updateFirst(Query.query(criteria), update, "defectsHistory"));
		   
			 System.out.println("after");
			
		}
}
