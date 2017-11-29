package starrail.sharetext.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import starrail.sharetext.domain.ShareTextCriteria;
import starrail.sharetext.domain.ShareTextVO;
import starrail.sharetext.persistence.ShareTextDAO;

@Service
public class ShareTextServiceImpl implements ShareTextService {

	
	@Inject
	private ShareTextDAO dao;
	
	@Override
	public void regist(ShareTextVO share) throws Exception {
		dao.create(share);

	}

	@Override
	public ShareTextVO read(Integer sh_no) throws Exception {
		System.out.println("dao : " + sh_no);
		return dao.read(sh_no);
	}

	@Override
	public void modify(ShareTextVO share) throws Exception {
		dao.update(share);

	}

	@Override
	public void remove(Integer sh_no) throws Exception {
		dao.delete(sh_no);

	}

	@Override
	public List<ShareTextVO> listAll() throws Exception {
	
		return dao.listAll();
	}

	@Override
	public List<ShareTextVO> listCriteria(ShareTextCriteria scri) throws Exception {
	
		return dao.listCriteria(scri);
	}

}
