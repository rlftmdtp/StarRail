package starrail.expenses.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import starrail.expenses.domain.ExpensesVO;
import starrail.expenses.service.ExpensesService;

@Controller
@RequestMapping(value = "/expenses/*")
public class ExpensesController {

	@Inject
	public ExpensesService service;
	

	@RequestMapping(value="/railro_expenses", method=RequestMethod.GET)
	public void railro_expensesGET(ExpensesVO expensesVO)throws Exception{
		System.out.println("?");
	}
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public void mainGET(ExpensesVO expensesVO)throws Exception{
		System.out.println("?");
	}
	
	@RequestMapping(value="/railro_expenses", method=RequestMethod.POST)
	public void railro_expensesPOST(ExpensesVO expensesVO)throws Exception{
		System.out.println(expensesVO);
		System.out.println("гоюл?");
		expensesVO.setC_id(2);
		expensesVO.setM_id("dlwotmd");
		service.expensesRegist(expensesVO);
	}
}
