package starrail.reservation.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import starrail.reservation.domain.ReservationVO;

@Repository
public class ReservationDAOImpl implements ReservationDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace="starrail.sharetext.mappers.reservationMapper";
	
	//예약 등록
	@Override
	public void rCreate(ReservationVO vo) throws Exception {
		System.out.println("ReservationImpl까지 성공");
		session.insert(namespace+".rCreate",vo);
	}

	//예약 정보 조회
	@Override
	public ReservationVO reservationRead(Integer res_no) throws Exception {
		System.out.println(res_no);

		return session.selectOne(namespace+".reservationRead",res_no);
	}

}
