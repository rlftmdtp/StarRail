package starrail.sharetext.persistence;

import java.util.List;

import starrail.sharetext.domain.ShareTextCriteria;
import starrail.sharetext.domain.ShareTextVO;

public interface ShareTextDAO {

	public void create(ShareTextVO vo)throws Exception;
	
	public ShareTextVO read(Integer sh_no)throws Exception;
	
	public void update(ShareTextVO vo)throws Exception;
	
	public void delete(Integer sh_no)throws Exception;
	
	public List<ShareTextVO> listAll()throws Exception;
	
	public List<ShareTextVO> listPage(int page)throws Exception;
	
	public List<ShareTextVO> listCriteria(ShareTextCriteria scri)throws Exception;
	
}
