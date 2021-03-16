package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DefectHistory;
import com.example.demo.model.Defects;

@Repository
public class DefectHistoryRepository {

	   @Autowired
	    private MongoTemplate mongoTemplate;
	   public List<DefectHistory> finddefectHistoryById(String defectId) {
			// TODO Auto-generated method stub
		   System.out.println(defectId);
			  Query query = new Query();
		        query.addCriteria(Criteria.where("defectId").is(defectId));
		        List<DefectHistory> defect =mongoTemplate.find(query, DefectHistory.class);
		        System.out.println(   defect.get(0).getDefectId());
		        return mongoTemplate.find(query, DefectHistory.class);
			
		}
	   public void save(DefectHistory defectHistory) {
			// TODO Auto-generated method stub
			System.out.println("before saving defect history");
			 mongoTemplate.save(defectHistory);
			 System.out.println("after");
			
		}
}
