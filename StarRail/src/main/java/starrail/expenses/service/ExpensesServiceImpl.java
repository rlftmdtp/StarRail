package starrail.expenses.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import starrail.expenses.domain.ExpensesVO;
import starrail.expenses.persistence.ExpensesDAO;

@Service
public class ExpensesServiceImpl implements ExpensesService {

	@Inject
	private ExpensesDAO dao;

	@Override
	public void expensesRegist(ExpensesVO expensesVO) throws Exception {
		
		if(dao.selectE_no() != null){
			expensesVO.setE_no(dao.selectE_no()+1);
		}else{
			expensesVO.setE_no(1);
		}
		dao.expensesInsert(expensesVO);
	}

	
}
