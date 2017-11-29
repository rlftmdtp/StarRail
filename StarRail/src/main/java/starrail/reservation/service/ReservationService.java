package starrail.reservation.service;

import starrail.reservation.domain.ReservationVO;

public interface ReservationService {

	public void regist(ReservationVO reservation)throws Exception;
	public ReservationVO reservationRead(Integer res_no)throws Exception;
	
}
