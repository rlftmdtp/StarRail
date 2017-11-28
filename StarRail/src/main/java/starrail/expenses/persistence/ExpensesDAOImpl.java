package starrail.expenses.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import starrail.expenses.domain.ExpensesVO;

@Repository
public class ExpensesDAOImpl implements ExpensesDAO {
	
	@Inject
	public SqlSession session;
	
	private static String namespace = "railro.review.mapper.ExpensesMapper";

	@Override
	public void expensesInsert(ExpensesVO expensesVO) throws Exception {
		session.insert(namespace+".expensesInsert", expensesVO);
	}

	@Override
	public Integer selectE_no() throws Exception {
		return session.selectOne(namespace+".selectE_no");
	}


}
