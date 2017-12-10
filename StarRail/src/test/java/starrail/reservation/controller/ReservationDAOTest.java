package starrail.reservation.controller;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import starrail.reservation.domain.ReservationVO;
import starrail.reservation.persistence.ReservationDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ReservationDAOTest {

	@Inject
	private ReservationDAO dao;
	
	@Test
	public void testCreate()throws Exception {
		ReservationVO rvo = new ReservationVO();
		rvo.setM_id("gkdustks");
		rvo.setRes_sdate("2017-11-30");
		rvo.setRes_tcount("7ÀÏ");
		rvo.setI_name("¼­¿ï");
		
		dao.rCreate(rvo);
	}

/*	@Test
	public void testRead()throws Exception{
		System.out.println(dao.reservationRead(4).toString());
	}*/
}
