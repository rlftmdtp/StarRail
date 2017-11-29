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
		shareText.setSh_no(40);
		shareText.setC_id(15);
		shareText.setM_id("test");
		shareText.setSh_subject("[5일]");
		shareText.setSh_title("테스트타이틀");
		shareText.setSh_content("테스트 내용");
		shareText.setSh_pw("1234");
		//shareText.setSh_date();
		shareText.setSh_shareok("Y");
		dao.create(shareText);
	}*/
	
/*	@Test
	public void testRead()throws Exception{
		System.out.println(dao.read(4).toString());
	}*/

/*@Test
	public void testListPage()throws Exception{
		int page = 3;
		
		List<ShareTextVO> list = dao.listPage(page);
		
		for(ShareTextVO sharevo : list) {
			System.out.println(sharevo.getSh_no()+":"+sharevo.getSh_title());
		}
	}*/
	
	@Test
	public void testListCriteria()throws Exception{
		
		ShareTextCriteria scri = new ShareTextCriteria();
		scri.setPage(2);
		scri.setPerPageNum(20);
		
		List<ShareTextVO> list = dao.listCriteria(scri);
		
		for(ShareTextVO sharetextVO : list){
			System.out.println(sharetextVO.getSh_no()+":"+sharetextVO.getSh_title());
		}
	}
}
