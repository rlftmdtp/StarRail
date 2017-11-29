package starrail.sharetext.service;

import java.util.List;

import starrail.sharetext.domain.ShareTextCriteria;
import starrail.sharetext.domain.ShareTextVO;

public interface ShareTextService {

	public void regist(ShareTextVO share)throws Exception;
	
	public ShareTextVO read(Integer sh_no)throws Exception;
	
	public void modify(ShareTextVO share)throws Exception;
	
	public void remove(Integer sh_no)throws Exception;
	
	public List<ShareTextVO> listAll()throws Exception;
	
	public List<ShareTextVO> listCriteria(ShareTextCriteria scri)throws Exception;
	
	
}
