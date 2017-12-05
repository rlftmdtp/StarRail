package starrail.expenses.persistence;

import java.util.List;
import java.util.Map;

import starrail.expenses.domain.ExpenseCourseVO;
import starrail.expenses.domain.ExpensesVO;
import starrail.expenses.domain.StatementVO;

public interface ExpensesDAO {

	//예상경비 설정
	public void expensesInsert(ExpensesVO expensesVO) throws Exception;
	public Integer selectE_no() throws Exception;

	//예상경비 설정 코스있음
	public void expenseCourseInsert(ExpenseCourseVO exCourseVO) throws Exception;
	public Integer selectCourseE_no() throws Exception;
	
	//총 남은금액 구해오기
	public Integer totalMoney(int e_no) throws Exception;
	
	//지출내역 저장
	public void amountInsert(StatementVO statementVO) throws Exception;
	public Integer selectEd_no() throws Exception;
	
	//총 남은 금액-사용금액 된 금액으로 총 남은금액 수정
	public Integer updateExpenses(Map<String, Integer> map) throws Exception;
	
	//사용자가 오늘 사용한 총 금액 가져오기
	public Integer todayTotal(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> course(String id) throws Exception;
	
	public List<StatementVO> recall(String m_id) throws Exception;
	//m_id에 해당하는 경비가 몇개있는지
	public Integer expenseCount(String m_id) throws Exception;
	
}
