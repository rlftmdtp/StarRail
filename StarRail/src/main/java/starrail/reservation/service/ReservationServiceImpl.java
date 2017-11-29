package starrail.reservation.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import starrail.reservation.domain.ReservationVO;
import starrail.reservation.persistence.ReservationDAO;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Inject
	private ReservationDAO dao;
	
	//예약등록
	@Override
	public void regist(ReservationVO reservation) throws Exception {
		dao.rCreate(reservation);
	}

	//예약정보조회
	@Override
	public ReservationVO reservationRead(Integer res_no) throws Exception {
		System.out.println("reservationService성공");
		System.out.println("res_no :" + res_no );
		return dao.reservationRead(res_no);
	}

}
