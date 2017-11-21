package starrail.main.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import starrail.main.domain.UserVO;
import starrail.main.dto.LoginDTO;
import starrail.main.service.UserService;

@Controller
public class MainController {
	
	@Inject
	private UserService service;
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public void viewGET() throws Exception{
		
	}
	
	@RequestMapping(value="/main/login", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception{
		UserVO vo = service.login(dto);
		
		// 회원 정보가 없거나 아이디.비밀번호가 틀렸다면
		if(vo == null){
			return;
		}
		
		model.addAttribute("userVO", vo);
		
	}
}
