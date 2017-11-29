package starrail.reservation.persistence;

import starrail.reservation.domain.ReservationVO;

public interface ReservationDAO {

	public void rCreate(ReservationVO vo)throws Exception;
	public ReservationVO reservationRead(Integer res_no)throws Exception;
}
