package starrail.sharetext.persistence;

import java.util.List;

import starrail.sharetext.domain.ShareTextCriteria;
import starrail.sharetext.domain.SharetextReplyVO;

public interface SharetextReplyDAO {

	public List<SharetextReplyVO> list(Integer sh_no) throws Exception;
	
	public void create(SharetextReplyVO vo) throws Exception;
	
	public void update(SharetextReplyVO vo)throws Exception;
	
	public void delete(Integer sr_no)throws Exception;
	
	public List<SharetextReplyVO> listPage(Integer sh_no, ShareTextCriteria scri) throws Exception;
	
	public int count(Integer sh_no) throws Exception;
	
}
