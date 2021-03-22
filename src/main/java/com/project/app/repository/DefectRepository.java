package com.project.app.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.project.app.exception.DefectNotFoundExceptin;
import com.project.app.model.Comments;
import com.project.app.model.Count;
import com.project.app.model.DefectHistory;
import com.project.app.model.Defects;  
import com.project.app.service.DefectServiceImpl;

@Repository
public class DefectRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Defects> findAll() {
		return mongoTemplate.findAll(Defects.class);
	}

	public Defects save(Defects defect) {

		mongoTemplate.save(defect);

		return defect;
	}

	public Defects addComments(String defectId, Comments comment, Defects defect) {
		comment.setDate(DefectServiceImpl.getTimeStamp());
		Update update = new Update();
		if (defect.getComment() == null) {
			update.push("comment", comment);

		} else {

			update.addToSet("comment", comment);
		}

		Criteria criteria = Criteria.where("_id").is(defectId);
		System.out.println(mongoTemplate.updateFirst(Query.query(criteria), update, "defects"));

		return defect;
	}

	public Defects update(Defects defect) throws InvalidAttributesException, DefectNotFoundExceptin {

		Defects oldDefect = mongoTemplate.findById(defect.getDefectId(), Defects.class);
		if (oldDefect == null) {
			throw new DefectNotFoundExceptin(defect.getDefectId());
		}

		Update update = new Update();
		if (defect.getStatus() != null && !oldDefect.getStatus().equals(defect.getStatus())) {
			update.set("status", defect.getStatus());
			oldDefect.setStatus(defect.getStatus());
		}
		if (defect.getSeverity() != 0 && oldDefect.getSeverity() != defect.getSeverity()) {
			update.set("severity", defect.getSeverity());
			oldDefect.setSeverity(defect.getSeverity());
		}
		if (defect.getUserId() != null && !oldDefect.getUserId().equals(defect.getUserId())) {
			oldDefect.setUserId(defect.getUserId());
			update.set("userId", defect.getUserId());
		}
		if (defect.getActualResult() != null && !oldDefect.getActualResult().equals(defect.getActualResult())) {
			oldDefect.setActualResult(defect.getActualResult());
			update.set("actualResult", defect.getActualResult());
		}
		if (oldDefect.getComment() == null && defect.getComment() != null
				|| (defect.getComment() != null && !oldDefect.getComment().equals(defect.getComment()))) {
			System.out.println("entered comment updation");
			for (int p = 0; p < defect.getComment().size(); p++) {
				addComments(defect.getDefectId(), defect.getComment().get(p), oldDefect);
			}
		}
		update.set("lastUpdateDate", defect.getLastUpdateDate());
		Criteria criteria = Criteria.where("_id").is(defect.getDefectId());
		System.out.println(mongoTemplate.updateFirst(Query.query(criteria), update, "defects"));
		return defect;
	}

	public Defects finddefectById(String defectId) {

		return mongoTemplate.findById(defectId, Defects.class);
	}

// change the status cancel no removal
	public void deleteById(String defectID) throws InvalidAttributesException {
		Defects defect = mongoTemplate.findById(defectID, Defects.class);
		defect.setStatus("close");
		mongoTemplate.save(defect);
	}

	public int getCountById() {
		// TODO Auto-generated method stub

		Count count = mongoTemplate.findById("Count", Count.class);
		Count newCount = new Count();
		if (count == null) {
			newCount.setCountId("Count");

			newCount.setCount(0);
			mongoTemplate.save(newCount);
			return 0;
		}
		return count.getCount();
	}

	public void updateCountId(int count) {
		// TODO Auto-generated method stub
		Count countNew = mongoTemplate.findById("Count", Count.class);
		countNew.setCount(count);
		mongoTemplate.save(countNew);
	}

	public void addAttachment(String defectId, Defects defect, Defects newdefect) {
		// TODO Auto-generated method stub
		Update update = new Update();
		if (newdefect.getAttachementlinks() == null) {
			update.push("attachementLinks", defect.getAttachementlinks().get(0));

		} else {

			update.addToSet("attachementLinks", defect.getAttachementlinks().get(0));
		}

		Criteria criteria = Criteria.where("_id").is(defectId);
		System.out.println(mongoTemplate.updateFirst(Query.query(criteria), update, "defects"));

	}

}
