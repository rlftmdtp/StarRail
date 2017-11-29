package starrail.sharetext.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import starrail.sharetext.domain.ShareTextCriteria;
import starrail.sharetext.domain.ShareTextVO;

@Repository
public class ShareTextDAOImpl implements ShareTextDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "starrail.sharetext.mappers.ShareMapper";
	
	@Override
	public void create(ShareTextVO vo) throws Exception {
		session.insert(namespace+".create",vo);
	}

	@Override
	public ShareTextVO read(Integer sh_no) throws Exception {
		System.out.println(sh_no);
		return session.selectOne(namespace+".read",sh_no);
	}

	@Override
	public void update(ShareTextVO vo) throws Exception {
	
		session.update(namespace+".update",vo);

	}

	@Override
	public void delete(Integer sh_no) throws Exception {
		
		session.delete(namespace+".delete", sh_no);
		
	}

	@Override
	public List<ShareTextVO> listAll() throws Exception {
		
		return session.selectList(namespace+".listAll");
	}

	@Override
	public List<ShareTextVO> listPage(int page) throws Exception {
		
		if(page <=0) {
			page = 1;
			
		}
		
		page = (page -1) * 5;
		
		return session.selectList(namespace+".listPage",page);
	}

	@Override
	public List<ShareTextVO> listCriteria(ShareTextCriteria scri) throws Exception {
		
		return session.selectList(namespace+".listCriteria", scri);
	}

}
