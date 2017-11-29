package starrail.map.controller;

import static org.junit.Assert.*;

import java.lang.reflect.Member;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import starrail.course.domain.CourseVO;
import starrail.main.domain.UserVO;
import starrail.map.persistence.MapDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MapDAOTest {
	
	@Inject
	private MapDAO dao;
	
	@Test
	public void testCourseList() throws Exception{
		
		UserVO userVO = new UserVO();
		userVO.setM_id("rlftmdtp");
		
		dao.courseList(userVO.getM_id());
	}

}
