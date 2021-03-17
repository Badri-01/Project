package com.project.app.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.project.app.model.Historymodel;
import com.project.app.model.Projectmodel;
import com.project.app.model.Testcasemodel;
import com.project.app.repository.Historyrepo;
import com.project.app.repository.Repository;
import com.project.app.repository.Testcaserepo;

@EnableScheduling
@Configuration
@ConditionalOnProperty(name="scheduler.enabled",matchIfMissing = true)
public class Scheduler {
	@Autowired
	private Testcaserepo testcaserepo;
	@Autowired
	private Historyrepo repo;
	
	@Scheduled(cron = "0 0 7 * * *")
	void startofthday() {
		int noOftestcasesopened=0;
		List<Testcasemodel> testcasesList=testcaserepo.findAll();
		Historymodel history=new Historymodel();
		history.setTimeofscheduler("startoftheday");
		for(Testcasemodel testcase:testcasesList) {
			if(testcase.getStatus().equals("Notstarted")) {
				noOftestcasesopened+=1;
				
			}
			history.setNooftestcasesopened(noOftestcasesopened);
			repo.save(history);
		}
		
	}
	@Scheduled(cron="0 0 17 * * *")
	void endoftheday() {
		int noOftestcasesopened=0;
		List<Testcasemodel> testcasesList=testcaserepo.findAll();
		Historymodel history=new Historymodel();
		history.setTimeofscheduler("endoftheday");
		for(Testcasemodel testcase:testcasesList) {
			if(testcase.getStatus().equals("Notstarted")) {
				noOftestcasesopened+=1;
				
			}
		}
			history.setNooftestcasesopened(noOftestcasesopened);
			repo.save(history);
}
}
