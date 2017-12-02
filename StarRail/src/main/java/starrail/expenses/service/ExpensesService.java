package starrail.expenses.service;


import starrail.expenses.domain.ExpensesVO;
import starrail.expenses.domain.StatementVO;

public interface ExpensesService {
	//���� ��� ����
	public void expensesRegist(ExpensesVO expensesVO) throws Exception;
	
	//�� �����ݾ� ���ؿ���
	public Integer totalMoney(int e_no, int ed_amount) throws Exception;
	
	//���� ���� ����
	public void amountRegist(StatementVO statementVO, Integer total) throws Exception;
	
	//����ڰ� ���� ����� �� �ݾ�
	public int todayTotal(int e_no, String ed_date)throws Exception;


	
}
