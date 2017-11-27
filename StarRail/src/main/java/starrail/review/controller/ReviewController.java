package starrail.review.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import starrail.review.domain.ReviewVO;
import starrail.main.domain.UserVO;
import starrail.review.domain.Hash_SearchVO;
import starrail.review.domain.ReviewPageMaker;
import starrail.review.domain.ReviewSearchCriteria;
import starrail.review.persistence.ReviewDao;
import starrail.review.service.ReviewService;

@Controller
@RequestMapping("/review/*")
public class ReviewController {

	@Inject
	public ReviewService service;
	public ReviewDao dao;

	@RequestMapping(value = "/review_insert", method = RequestMethod.GET)
	public void insertReviewGET(ReviewVO review, Hash_SearchVO hash, String hashSearch,Model model) throws Exception {
		List<String> list = new ArrayList<String>();
		list = service.hashSearch();
		for(int i=0;i<list.size();i++) {
			list.set(i,  "\"" + list.get(i) + "\"");
		}
		System.out.println("붙어오니? : "+ list);
		model.addAttribute("list", list);
	}

	@RequestMapping(value = "/review_insert", method = RequestMethod.POST)
	public String insertReviewPOST(@ModelAttribute("review") ReviewVO review, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("login");

		review.setM_id(userVO.getM_id());
		System.out.println(review.toString());

		service.register(review);
		service.hashtagInsert(review);

		
		
		return "redirect:/review/review_list";
	}

	@RequestMapping(value = "/review_list", method = RequestMethod.GET)
	public void listReviewGET(@ModelAttribute("cri") ReviewSearchCriteria cri, Model model) throws Exception {

		model.addAttribute("list", service.listSearchCriteria(cri));
		ReviewPageMaker pageMaker = new ReviewPageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.listSearchCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value = "/review_detail", method = RequestMethod.GET)
	public void DetailReviewGET(@RequestParam("r_no") int r_no, @ModelAttribute("cri") ReviewSearchCriteria cri,
			Model model) throws Exception {
		model.addAttribute(service.read(r_no));
	}

	@RequestMapping(value = "/review_remove", method = RequestMethod.GET)
	public String RemoveReviewGET(@RequestParam("r_no") int r_no, ReviewSearchCriteria cri, RedirectAttributes rttr)
			throws Exception {

		service.remove(r_no);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/review/review_list";
	}

	@RequestMapping(value = "/review_modify", method = RequestMethod.GET)
	public void ModifyReviewGET(@RequestParam("r_no") int r_no, @ModelAttribute("cri") ReviewSearchCriteria cri,
			Model model) throws Exception {
		model.addAttribute(service.read(r_no));
	}

	@RequestMapping(value = "/review_modify", method = RequestMethod.POST)
	public String ModifyReviewPOST(ReviewVO review, ReviewSearchCriteria cri, RedirectAttributes rttr)
			throws Exception {
		service.modify(review);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/review/review_list";
	}

	@RequestMapping("/getAttach/{r_no}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("r_no") Integer r_no) throws Exception {
		return service.getAttach(r_no);
	}
	

/*	@RequestMapping(value = "/tagCheck", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Hash_SearchVO>> TagCheckPOST()
			throws Exception {
		
		ResponseEntity<List<Hash_SearchVO>> entity = null;
		ResponseEntity<String> entity2 = null;

		List<Hash_SearchVO> list = new ArrayList<Hash_SearchVO>();
		list = service.hashSearch();
		System.out.println("list : "+list);
		
		try {
			entity = new ResponseEntity<List<Hash_SearchVO>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity2 = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}*/
/*	@RequestMapping(value = "/tagCheck", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, String>> TagCheckPOST(String hs_search)
			throws Exception {
		System.out.println("입력: " + hs_search);
		ResponseEntity<String> entity2 = null;
		ResponseEntity<Map<String, String>> entity = null;
		
		List<Hash_SearchVO> list = new ArrayList<Hash_SearchVO>();
		list = service.hashSearch();
		HashMap<String, String> map = new HashMap<String, String>();
		
		for (int i = 0; i < list.size(); i++) {
			map.put("s" + Integer.toString(list.get(i).getHs_no()), list.get(i).getHs_search());
			map.get(list.get(i).getHs_search());
			System.out.println(map.get("s"+list.get(i).getHs_no()));
		}
		
		try {
			entity = new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity2 = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
*/
	/*
	 * @RequestMapping(value="/tagCheck", method=RequestMethod.POST)
	 * public @ResponseBody ResponseEntity<List> TagCheckPOST(@RequestBody
	 * String hs_search, Model model) throws Exception{ ResponseEntity<String>
	 * entity2 =null; ResponseEntity<List> entity =null;
	 * 
	 * List<Hash_SearchVO> list = new ArrayList<Hash_SearchVO>(); list =
	 * service.hashSearch(); // List<HashMap<String, String>> hashmap = new
	 * LinkedList<HashMap<String, String>>();
	 * 
	 * for(int i=0;i<list.size();i++) { HashMap<String, String> map = new
	 * HashMap<String, String>(); map.put("hs_search",
	 * list.get(i).getHs_search()); // map.put("hs_count",
	 * Integer.toString(list.get(i).getHs_count()));
	 * 
	 * // hashmap.add(map); System.out.println(map.get("hs_search"));
	 * model.addAttribute(map.get("hs_search"));
	 * 
	 * }
	 * 
	 * 
	 * try{ entity = new ResponseEntity<List>(list, HttpStatus.OK);
	 * 
	 * }catch (Exception e) { e.printStackTrace(); entity2 = new
	 * ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); } return
	 * entity; }
	 */

	/*
	 * @RequestMapping(value="/auto", method=RequestMethod.POST) public void
	 * autoGET(String term, HttpServletResponse response, Model model)throws
	 * Exception{ List<Hash_SearchVO> list = dao.getAddList(term);
	 * System.out.println(list); model.addAttribute("list", list); }
	 */
}
