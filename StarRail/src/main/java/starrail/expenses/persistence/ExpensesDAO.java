package starrail.expenses.persistence;

import starrail.expenses.domain.ExpensesVO;

public interface ExpensesDAO {

	public void expensesInsert(ExpensesVO expensesVO) throws Exception;
	
	public Integer selectE_no() throws Exception;
	
}
