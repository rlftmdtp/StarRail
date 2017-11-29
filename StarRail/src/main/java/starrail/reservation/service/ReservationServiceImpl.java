package starrail.reservation.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import starrail.reservation.domain.ReservationVO;
import starrail.reservation.persistence.ReservationDAO;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Inject
	private ReservationDAO dao;
	
	//������
	@Override
	public void regist(ReservationVO reservation) throws Exception {
		dao.rCreate(reservation);
	}

	//����������ȸ
	@Override
	public ReservationVO reservationRead(Integer res_no) throws Exception {
		System.out.println("reservationService����");
		System.out.println("res_no :" + res_no );
		return dao.reservationRead(res_no);
	}

}
