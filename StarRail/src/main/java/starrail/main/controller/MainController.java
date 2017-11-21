package starrail.main.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import starrail.main.domain.UserVO;
import starrail.main.dto.LoginDTO;
import starrail.main.service.UserService;

@Controller
@RequestMapping("/main/*")
public class MainController {
	
	@Inject
	private UserService service;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public void viewGET() throws Exception{
		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto){
		
	}
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception{
		
		System.out.println(dto.getM_id());
		System.out.println(dto.getM_pw());
		
		UserVO vo = service.login(dto);
		
		// 로그인 실패
		if(vo == null){
			System.out.println("아이디나 비밀번호가 틀렸습니다");
			
			return;
		}
		
		// 로그인 성공
		model.addAttribute("userVO", vo);
	}
}
