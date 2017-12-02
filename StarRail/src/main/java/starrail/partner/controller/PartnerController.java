package starrail.partner.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	// 泥섏쓬 �룞�뻾 �럹�씠吏� �겢由� �떆 �떎�뻾�릺�뒗 而⑦듃濡ㅻ윭
	@RequestMapping(value = "/partnerTest", method = RequestMethod.GET)
	public void partnerGET(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		System.out.println("ddddfdfdf");
		UserVO uservo = (UserVO) session.getAttribute("login");
		System.out.println(uservo);

		List<CourseVO> courseVO = service.myCourse_List_service(uservo);
		System.out.println(courseVO);
		model.addAttribute("courseVO", courseVO);
	}

	// �뜽�꽕�씪 �씠誘몄� �겢由� �떆 肄붿뒪 �븘�씠�뵒�쓣 �꽆寃⑤컺�븘 肄붿뒪 �뵒�뀒�씪�쓣 李얘퀬 踰꾪듉�쓣 �깮�꽦�븯湲� �쐞�빐 �젙蹂대�� �꽆寃⑥쨲	
	@RequestMapping(value = "/scheduleSearch", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<CourseDetailVO>> scheduleSearch_POST(HttpServletRequest request,@RequestParam Integer c_id) {
		HttpSession session = request.getSession();
		UserVO uservo = (UserVO) session.getAttribute("login");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("m_id", uservo.getM_id());
		map.put("c_id", c_id);
		
		System.out.println(map);
		System.out.println(c_id);
		
		// cd_id瑜� 戮묒븘�삩�떎
		//List<CourseDetailVO2> list = (List<CourseDetailVO2>)service.mySchedule_List_service(map);
		List<CourseDetailVO> list = service.mySchedule_List_service(map);
		System.out.println(list);
		
	    return new ResponseEntity<List<CourseDetailVO>>(list, HttpStatus.OK);
	}
	
	
/*	//�썝�븯�뒗 �씪�젙 �꽑�깮 �썑 �룞諛섏옄 李얘린 踰꾪듉 �겢由� �떆 cd_id媛� �꽆�뼱�샂
	//cd_id瑜� �넻�빐 �빐�떦�븯�뒗 �뙆�듃�꼫瑜� 李얠븘以�
	@RequestMapping(value = "/partnerSearch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> partnerSearch_POST(@RequestParam Map<String,String> list)throws Exception{
		List<CourseDetailVO> cd_list = service.courseDetail_List_service(list);			
		
		//System.out.println("*而⑦듃濡ㅻ윭2"+service.partnerSearch_List_service(cd_list));
		ResponseEntity<Map<String, Object>> entity = null;
		entity =   new ResponseEntity<Map<String,Object>>(service.partnerSearch_List_service(cd_list), HttpStatus.OK);

		return entity;
	}*/
	
	//�썝�븯�뒗 �씪�젙 �꽑�깮 �썑 �룞諛섏옄 李얘린 踰꾪듉 �겢由� �떆 cd_id媛� �꽆�뼱�샂
	//cd_id瑜� �넻�빐 �빐�떦�븯�뒗 �뙆�듃�꼫瑜� 李얠븘以�
	@RequestMapping(value = "/partnerSearch", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<UserVO>> partnerSearch_POST(@RequestParam Integer cd_id)throws Exception{
		CourseDetailVO courseDetailVO =  service.courseDetail_Search_List_service(cd_id);
		List<UserVO> user_list = service.partnerSearch_List_service(courseDetailVO);
		
		ResponseEntity<List<UserVO>> entity = null;		
		entity =   new ResponseEntity<List<UserVO>>(user_list, HttpStatus.OK);
		
		return entity;
	}
	
	
}
