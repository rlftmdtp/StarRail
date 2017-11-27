package starrail.message.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import starrail.message.domain.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Inject
	public SqlSession session;
	
	private static String namespace = "railro.review.mapper.MessageMapper";
	
	@Override
	public void insertMessage(MessageVO message) throws Exception {
		session.insert(namespace +".insertMessage", message); 
	}

	@Override
	public Integer selectMsg_no() throws Exception {
		return session.selectOne(namespace+".selectMsg_no");
	}

	@Override
	public List<MessageVO> listMessage(String m_id) throws Exception {
		System.out.println("daoid : " + m_id);
		return session.selectList(namespace+".list", m_id);
	}

	@Override
	public void delete(int msg_no) throws Exception {
		session.delete(namespace + ".delete", msg_no);
	}

	@Override
	public Integer msg_hit(int msg_no) throws Exception {
		return session.update(namespace+".msg_hit", msg_no);
	}

	@Override
	public Integer update_hit(String m_id) throws Exception {
		return session.selectOne(namespace+".update_hit", m_id);
	}

	@Override
	public MessageVO detail(int msg_no) throws Exception {
		return session.selectOne(namespace+".detail", msg_no);
	}

	@Override
	public List<MessageVO> sendemail(String m_id) throws Exception {
		return session.selectList(namespace+".sendemail", m_id);
	}



	

}
