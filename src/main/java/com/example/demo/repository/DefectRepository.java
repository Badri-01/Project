package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Comments;
import com.example.demo.model.DefectHistory;
import com.example.demo.model.Defects;

@Repository
public class DefectRepository {
	
	

	    @Autowired
	    private MongoTemplate mongoTemplate;
	   
       
	    public List<Defects> findAll() {
	        return mongoTemplate.findAll(Defects.class);
	    }

	    public  Defects save(Defects defect) {
	    	System.out.println(defect.getComment().get(0).getDescription());
	    	
	        mongoTemplate.save(defect);
	        return defect;
	    }

	    public Defects update(Defects defect){
	       ;
	       
	       Defects oldDefect= mongoTemplate.findById(defect.getDefectId(), Defects.class);
	     
	    	   if(defect.getStatus()!=null&&!oldDefect.getStatus().equals(defect.getStatus())) {
	    		   oldDefect.setStatus(defect.getStatus());
	    	   }
	    	   if(defect.getSeverity()!=0&&oldDefect.getSeverity()!=defect.getSeverity()) {
	    		   oldDefect.setSeverity(defect.getSeverity());
	    	   }
	    	   if(defect.getUserId()!=null&&!oldDefect.getUserId().equals(defect.getUserId())) {
	    		   oldDefect.setUserId(defect.getUserId());
	    	   }
	    	   if(defect.getActualResult()!=null&&!oldDefect.getActualResult().equals(defect.getActualResult())) {
	    		   oldDefect.setActualResult(defect.getActualResult());
	    	   }
	    	   if(oldDefect.getComment()==null||(defect.getComment()!=null&&!oldDefect.getComment().equals(defect.getComment()))) {
	    		   if(oldDefect.getComment()==null) {
	    				List<Comments> commentList=new ArrayList<>();
	    				commentList.addAll((defect.getComment()));
	    				System.out.println("entered");
	    				System.out.println(commentList.get(0).getDescription());
	    				oldDefect.setComment(commentList);
	    			}else {
	    				List<Comments> commentList=	oldDefect.getComment();;
	    			commentList.addAll((defect.getComment()));
	    			oldDefect.setComment(commentList);
	    			}
	    	   }
	       
	        return mongoTemplate.save(oldDefect);
	    }

	    public List<Defects> finddefectByName(String empName){
	        Query query = new Query();
	        query.addCriteria(Criteria.where("name").is(empName));
	        return mongoTemplate.find(query, Defects.class);
	    }
	    public Defects finddefectById(String defectId){
	        
	        return mongoTemplate.findById(defectId, Defects.class);
	    }
// change the status cancel no removal
	    public void deleteById(String defectID) {
	    Defects defect=	mongoTemplate.findById(defectID, Defects.class);
	    defect.setStatus("close");
	        mongoTemplate.save(defect);
	    }

		

		
	}

