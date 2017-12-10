package starrail.sharetext.controller;

import java.util.List;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import starrail.sharetext.domain.ShareTextCriteria;
import starrail.sharetext.domain.ShareTextVO;
import starrail.sharetext.persistence.ShareTextDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ShareTextDAOTest {

	@Inject
	private ShareTextDAO dao;
	
	
/*	@Test
	public void testCreate()throws Exception {
		System.out.println("test....");
		ShareTextVO shareText = new ShareTextVO();
		shareText.setSh_no(44);
		shareText.setC_id(3);
		shareText.setM_id("whwlgh");
		shareText.setSh_subject("[5일]");
		shareText.setSh_title("테스트타이틀");
		shareText.setSh_content("테스트 내용");
		shareText.setSh_pw("1234");
		//shareText.setSh_date();
		shareText.setSh_hit(5);
		shareText.setSh_shareok("Y");
		dao.create(shareText);
	}*/
	
	/*@Test
	public void testRead()throws Exception{
		System.out.println("목록 테스트...");
		System.out.println(dao.read(82).toString());
	}*/

/*@Test
	public void testupdate()throws Exception{
		ShareTextVO share = new ShareTextVO();
		share.setSh_no(41);
		share.setSh_title("수정해봄");
		share.setSh_content("수정된 내용");
		dao.update(share);
	}*/

/*@Test
	public void testDelete()throws Exception{
	dao.delete(41);
}*/
	
	@Test
	public void testListPage()throws Exception{
		int page = 1;
		
		List<ShareTextVO> list = dao.listPage(page);
		
		for(ShareTextVO share : list) {
			System.out.println(share.getSh_no()+":"+share.getSh_title());
		}
	}
	
/*@Test
	public void testListCriteria()throws Exception{
		
		ShareTextCriteria cri = new ShareTextCriteria();
		cri.setPage(1);
		cri.setPerPageNum(20);
		
		List<ShareTextVO> list = dao.listCriteria(cri);
		
		for(ShareTextVO sharetextVO : list){
			System.out.println(sharetextVO.getSh_no()+":"+sharetextVO.getSh_title());
		}
	}*/
}
