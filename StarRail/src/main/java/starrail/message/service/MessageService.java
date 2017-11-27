package starrail.message.service;

import java.util.List;

import starrail.message.domain.MessageVO;

public interface MessageService {

	public void regist(MessageVO message) throws Exception;
	
	public List<MessageVO> messageList(String m_id) throws Exception;

	public List<MessageVO> sendemail(String m_id) throws Exception;

	public void remove(int msg_no) throws Exception;
	
	public Integer msg_hit(int msg_no) throws Exception;
	
	public Integer update_hit(String m_id) throws Exception;
	
	public MessageVO read(int msg_no) throws Exception;
}
