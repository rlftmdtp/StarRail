package starrail.sharetext.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import starrail.main.domain.UserVO;
import starrail.sharetext.domain.ShareTextCriteria;
import starrail.sharetext.domain.ShareTextVO;
import starrail.sharetext.domain.SharetextPageMaker;
import starrail.sharetext.service.ShareTextService;

@Controller
@RequestMapping("/sharetext/*")
public class ShareTextController {
	
	@Inject
	private ShareTextService service;
	
	
	//회원정보 가지고 오기
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET(ShareTextVO share, Model model, HttpServletRequest request) throws Exception{
		
		//회원정보가 담겨있는 session
		HttpSession session = request.getSession();
		
		UserVO uservo = (UserVO)session.getAttribute("login");
		
		String m_id= uservo.getM_name();
		
		System.out.println("register get...");
	}
	
	//글 등록
	@RequestMapping(value="/sharetext_insert", method=RequestMethod.GET)
	public void registerGET(ShareTextVO share, Model model)throws Exception{
		System.out.println("register get...");
	}
	//글 등록
	@RequestMapping(value="/sharetext_insert", method=RequestMethod.POST)
	public String registPOST(ShareTextVO share, RedirectAttributes rttr) throws Exception{
		System.out.println("regist post...");
		System.out.println(share.toString());
		
		service.regist(share);
		
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/sharetext/listAll";
		
	}
		//목록보기
		@RequestMapping(value="/listAll", method=RequestMethod.GET)
		public String listAll(Model model) throws Exception{
			
			System.out.println("show all list...");
			model.addAttribute("list", service.listAll());
			return "/sharetext/sharetext_listAll";
		}
	
		//디테일
		@RequestMapping(value="/sharetext_detail",method=RequestMethod.GET)
		public void read(@RequestParam("sh_no") int sh_no, Model model)throws Exception{
			System.out.println("read text...");
			model.addAttribute(service.read(sh_no));
		}
		
/*		//삭제
		@RequestMapping(value="/remove", method=RequestMethod.POST)
		public String remove(@RequestParam("sh_no") int sh_no, RedirectAttributes rttr)throws Exception{
			service.remove(sh_no);
			rttr.addFlashAttribute("msg", "SUCCESS");
			
			return "redirect:/sharetext/listAll";
		}*/
		
		//삭제
		@RequestMapping("remove")
		public String remove(@RequestParam("sh_no") Integer sh_no, RedirectAttributes rttr)throws Exception{
			service.remove(sh_no);
			rttr.addFlashAttribute("msg", "SUCCESS");
			
			return "redirect:/sharetext/listAll";
		}
		
		//수정
		@RequestMapping(value="/sharetext_update", method=RequestMethod.GET)
		public void modifyGET(@RequestParam("sh_no")int sh_no, Model model)throws Exception{
			System.out.println("update text...");
			model.addAttribute(service.read(sh_no));
		}
		
		@RequestMapping(value="/sharetext_update", method=RequestMethod.POST)
		public String modifyPOST(ShareTextVO share, RedirectAttributes rttr)throws Exception{
			System.out.println("mod post...");
			
			service.modify(share);
			rttr.addFlashAttribute("msg", "SUCCESS");
			
			return "redirect:/sharetext/listAll";
		}
		
		@RequestMapping(value="/sharetext_listCri", method=RequestMethod.GET)
		public void listAll(ShareTextCriteria scri, Model model) throws Exception{
			
			System.out.println("show list page with criteria...");
			
			model.addAttribute("list",service.listCriteria(scri));
		}
		
		@RequestMapping(value="/sharetext_listPage", method=RequestMethod.GET)
		public void listPage(ShareTextCriteria scri, Model model)throws Exception{
			
			System.out.println(scri.toString());
			
			model.addAttribute("list", service.listCriteria(scri));
			SharetextPageMaker pageMaker = new SharetextPageMaker();
			pageMaker.setCri(scri);
			pageMaker.setTotalCount(131);  //임의값 131
			
			model.addAttribute("pageMaker",pageMaker);
		}
}

