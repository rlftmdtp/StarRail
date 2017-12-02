package starrail.expenses.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import starrail.expenses.domain.ExpensesVO;
import starrail.expenses.domain.StatementVO;
import starrail.expenses.service.ExpensesService;

@Controller
@RequestMapping(value = "/expenses/*")
public class ExpensesController {

	@Inject
	public ExpensesService service;

	//경비관리페이지 들어갈 때
	@RequestMapping(value = "/railro_expenses", method = RequestMethod.GET)
	public void railro_expensesGET(ExpensesVO expensesVO) throws Exception {
	
	}


	// 예상경비 설정하기
	@RequestMapping(value = "/railro_expenses", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> railro_expensesPOST(@RequestBody ExpensesVO expensesVO) throws Exception {
		
			
			expensesVO.setM_id("thf147");
			expensesVO.setC_id(3);
			
			service.expensesRegist(expensesVO);
			System.out.println("예상 경비 저장 완료 : " + expensesVO.getE_no());
		
		
		//가지고 갈 값을 넣어주면됨
		return new ResponseEntity<Integer>(expensesVO.getE_no(), HttpStatus.OK);
	}
	
	//지출내역 계산 및 저장
	@RequestMapping(value="/railro_amount", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> railro_amountPOST(@RequestBody StatementVO statementVO, ExpensesVO expensesVO) throws Exception{
		Map<String, Object> map = new HashMap<>();
		
		statementVO.setEd_plma("+");
		
		//총남은 금액 - 사용금액 계산한 것
		int totalMoney =service.totalMoney(statementVO.getE_no(), statementVO.getEd_amount());
	
		//계산하고 난 후 최종값 수정 및 지출내역 저장
		service.amountRegist(statementVO, service.totalMoney(statementVO.getE_no(), statementVO.getEd_amount())); //
		
		//오늘 쓴 총 금액
		int todayTotal = service.todayTotal(statementVO.getE_no(), statementVO.getEd_date());
		
		//map에 담아 jsp로 가져갈것
				map.put("ed_date", statementVO.getEd_date());
				map.put("ed_kategorie", statementVO.getEd_kategorie());
				map.put("ed_katename", statementVO.getEd_katename());
				map.put("ed_amount", statementVO.getEd_amount());
				map.put("e_no", statementVO.getE_no());
				map.put("e_total", totalMoney);
				map.put("todayTotal", todayTotal);
		
		return new ResponseEntity<Map<String, Object>>(map ,HttpStatus.OK);
	}
	


}
