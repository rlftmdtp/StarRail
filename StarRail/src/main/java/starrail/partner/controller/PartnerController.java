package starrail.partner.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import starrail.course.domain.CourseDetailVO;
import starrail.course.domain.CourseVO;
import starrail.main.domain.UserVO;
import starrail.partner.service.PartnerService;

@Controller
@RequestMapping("/partner/*")
public class PartnerController {

	@Inject
	private PartnerService service;

	// 처음 동행 페이지 클릭 시 실행되는 컨트롤러
	@RequestMapping(value = "/partner", method = RequestMethod.GET)
	public void partnerGET(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();

		UserVO uservo = (UserVO) session.getAttribute("login");
		//System.out.println(uservo);

		List<CourseVO> courseVO = service.myCourse_List_service(uservo);
		//System.out.println(courseVO);
		model.addAttribute("courseVO", courseVO);
	}

	// 썸네일 이미지 클릭 시 코스 아이디을 넘겨받아 코스 디테일을 찾고 버튼을 생성하기 위해 정보를 넘겨줌	
	@RequestMapping(value = "/scheduleSearch", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<CourseDetailVO>> scheduleSearch_POST(HttpServletRequest request, Integer c_id) {
		HttpSession session = request.getSession();
		UserVO uservo = (UserVO) session.getAttribute("login");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("m_id", uservo.getM_id());
		map.put("c_id", c_id);
		
		// cd_id를 뽑아온다
		List<CourseDetailVO> list = (List<CourseDetailVO>)service.mySchedule_List_service(map);
		//System.out.println(list);
		
	    return new ResponseEntity<List<CourseDetailVO>>(list, HttpStatus.OK);
	}
	
	
	//원하는 일정 선택 후 동반자 찾기 버튼 클릭 시 cd_id가 넘어옴
	//cd_id를 통해 해당하는 파트너를 찾아줌
	@RequestMapping(value = "/partnerSearch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> partnerSearch_POST(@RequestParam Map<String,String> list) {
		System.out.println("파트너 검색 시 컨트롤러");
		
		
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(service.courseDetail_List_service(list));
		System.out.println("--------------------------------------------------------------------------");
		
		List<CourseDetailVO> a = service.courseDetail_List_service(list);		
		System.out.println(a.get(0));
		//return new ResponseEntity<List<String>> (list, HttpStatus.OK);
		return null;
	}
	
	
	@RequestMapping(value = "/partnerSearch", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<String>> partnerSearch_GET() {
		System.out.println("파트너 검색 시 컨트롤러");

		return null;
	}

}
