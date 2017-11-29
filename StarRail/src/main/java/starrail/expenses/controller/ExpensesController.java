package starrail.expenses.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import starrail.expenses.domain.ExpensesVO;
import starrail.expenses.service.ExpensesService;

@Controller
@RequestMapping(value = "/expenses/*")
public class ExpensesController {

	@Inject
	public ExpensesService service;

	@RequestMapping(value = "/railro_expenses", method = RequestMethod.GET)
	public void railro_expensesGET(ExpensesVO expensesVO) throws Exception {
	
		System.out.println("?");
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainGET(ExpensesVO expensesVO) throws Exception {
		System.out.println("?");
	}

	// 여행 초기비용 설정
	@RequestMapping(value = "/railro_expenses", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> railro_expensesPOST(@RequestBody ExpensesVO expensesVO) throws Exception {
		
			System.out.println(expensesVO.getE_title());
			
			expensesVO.setM_id("thf147");
			expensesVO.setC_id(5);
			
			System.out.println("서비스로 DB에 등록됫음");
			service.expensesRegist(expensesVO);
			System.out.println("e_no" + expensesVO.getE_no());
		
		
		//나중에 불러오기 하면 return 값에 넣어주면 될듯 지금은 x
		return new ResponseEntity<Integer>(expensesVO.getE_no(), HttpStatus.OK);
	}


}
