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
	
	//���� ���
	@Override
	public void rCreate(ReservationVO vo) throws Exception {
		System.out.println("ReservationImpl���� ����");
		session.insert(namespace+".rCreate",vo);
	}

	//���� ���� ��ȸ
	@Override
	public ReservationVO reservationRead(Integer res_no) throws Exception {
		System.out.println(res_no);

		return session.selectOne(namespace+".reservationRead",res_no);
	}

}
