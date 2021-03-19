package com.project.app.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.project.app.model.Historymodel;
import com.project.app.model.Testcasemodel;
import com.project.app.repository.Historyrepo;
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
		int noOftestcaseopened=0;
		int noOftestcasepassed=0;
		int noOftestcasefailed=0;
		List<Testcasemodel> testcasesList=testcaserepo.findAll();
		Historymodel history=new Historymodel();
		history.setTimeofscheduler("startoftheday");
		history.setTotalnooftestcases(testcasesList.size());
		for(Testcasemodel testcase:testcasesList) {
			if(testcase.getStatus().equals("Notstarted")) {
				noOftestcaseopened+=1;
				
			}
			else if(testcase.getStatus().equals("Passed")) {
				noOftestcasepassed+=1;
			}
			else if(testcase.getStatus().equals("Failed")) {
				noOftestcasefailed+=1;
			}
		}
			history.setNooftestcasepassed(noOftestcasepassed);
			history.setNooftestcasesfailed(noOftestcasefailed);
			history.setNooftestcasesopened(noOftestcaseopened);
			repo.save(history);
		
	}
	@Scheduled(cron="0 0 17 * * *")
	void endoftheday() {
		int noOftestcaseopened=0;
		int noOftestcasepassed=0;
		int noOftestcasefailed=0;
		List<Testcasemodel> testcasesList=testcaserepo.findAll();
		Historymodel history=new Historymodel();
		history.setTimeofscheduler("endoftheday");
		history.setTotalnooftestcases(testcasesList.size());
		for(Testcasemodel testcase:testcasesList) {
			if(testcase.getStatus().equals("Notstarted")) {
				noOftestcaseopened+=1;
				
			}
			else if(testcase.getStatus().equals("Passed")) {
				noOftestcasepassed+=1;
			}
			else if(testcase.getStatus().equals("Failed")) {
				noOftestcasefailed+=1;
			}
		}
			history.setNooftestcasepassed(noOftestcasepassed);
			history.setNooftestcasesfailed(noOftestcasefailed);
			history.setNooftestcasesopened(noOftestcaseopened);
			repo.save(history);
}
}
