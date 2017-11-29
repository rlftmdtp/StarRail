package starrail.sharetext.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import starrail.sharetext.domain.ShareTextCriteria;
import starrail.sharetext.domain.SharetextReplyVO;
import starrail.sharetext.persistence.SharetextReplyDAO;
@Service
public class SharetextReplyServiceImpl implements SharetextReplyService {

	@Inject
	private SharetextReplyDAO dao;
	
	@Override
	public void addReply(SharetextReplyVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public List<SharetextReplyVO> listReply(Integer sh_no) throws Exception {
		
		return dao.list(sh_no);
	}

	@Override
	public void modifyReply(SharetextReplyVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void removeReply(Integer sr_no) throws Exception {
		dao.delete(sr_no);

	}

	@Override
	public List<SharetextReplyVO> listReplyPage(Integer sh_no, ShareTextCriteria scri) throws Exception {
		
		return dao.listPage(sh_no, scri);
	}

	@Override
	public int count(Integer sh_no) throws Exception {
		
		return dao.count(sh_no);
	}

}
