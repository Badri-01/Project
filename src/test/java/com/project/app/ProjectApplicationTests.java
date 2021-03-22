package com.project.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;


import javax.naming.directory.InvalidAttributesException;

import org.json.JSONException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.app.exception.DefectNotFoundExceptin;
import com.project.app.exception.ProjectNotfoundexception;
import com.project.app.model.DefectHistory;
import com.project.app.model.Defects;
import com.project.app.model.Projectmodel;
import com.project.app.repository.DefectHistoryRepository;
import com.project.app.repository.DefectRepository;
import com.project.app.repository.Repository;
import com.project.app.service.DefectServiceImpl;
import com.project.app.service.DefectServiceInt;


@RunWith(SpringRunner.class)
@SpringBootTest()
@ComponentScan(basePackages = "com.project.app.services")
@AutoConfigureMockMvc
public class ProjectApplicationTests {

	@TestConfiguration
	static class ProjectServiceImplTestContextConfiguration {

		@Bean
		public DefectServiceInt defectService() {
			return new DefectServiceImpl();
		}
	}

	@Autowired
	private DefectServiceInt defectService;

	@MockBean
	private Repository repo;
	@MockBean
	private DefectRepository defectRepo;
	@MockBean
	private DefectHistoryRepository defectHistoryRepo;

	@Test
	public void testDefectById() throws ParseException, JSONException, DefectNotFoundExceptin, InvalidAttributesException {

		String id = "D-1";
		Defects defect = new Defects();
	defect=	setDefect(defect);
		List<DefectHistory> historyList = new ArrayList<>();

		when(defectRepo.finddefectById(id)).thenReturn(defect);
		when(defectHistoryRepo.finddefectHistoryById(id)).thenReturn(historyList);

		assertEquals(" ", defectService.findDefectByID(id));
	}
private Defects setDefect(Defects defect) throws InvalidAttributesException {
		// TODO Auto-generated method stub
		defect.setDefectId("D_1");
		defect.setCreateDate("2020_12_09");
		defect.setProjectId("P_01");
		defect.setTestCaseId("T_03");
		defect.setActualResult("update assets");
		defect.setExpectedResult("deleted result");
		defect.setSeverity(2);
		defect.setStatus("new");
		defect.setLastUpdateDate("2020_03_21");
		return defect;
	}
//"D-1", "2021-12-04", "P-01", "t_03", "update assets", "no assets updated", "u_03",
//	"new", "2021_03_12", 0, null, null
	@Test
	public void testCreateDefect() throws ParseException, InvalidAttributesException {

		Defects defect = new Defects();
		defect=	setDefect(defect);
		when(defectRepo.save(defect)).thenReturn(defect);
		assertEquals(defect, defectService.newDefect(defect));
	}

	@Test
	public void testFindAllDefects() throws ParseException, InvalidAttributesException {
		Defects defect = new Defects();
		defect=	setDefect(defect);
		List<Defects> defectList = new ArrayList<>();
		defectList.add(defect);
		when(defectRepo.findAll()).thenReturn(defectList);
		assertEquals(defectList, defectService.getAllDefects());
	}

	@Test
	public void testUpdateDefect() throws ParseException, InvalidAttributesException, DefectNotFoundExceptin{
		
		
		Defects defect = new Defects();
		defect=	setDefect(defect);
		
		when(defectRepo.update(defect)).thenReturn(defect);
		
		assertEquals(defect, defectService.updateDefectDetails(defect));
	}
}