package starrail.expenses.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import starrail.expenses.domain.ExCourseVO;
import starrail.expenses.domain.ExpensesVO;
import starrail.expenses.domain.StatementVO;
import starrail.expenses.persistence.ExpensesDAO;

@Service
public class ExpensesServiceImpl implements ExpensesService {

	@Inject
	private ExpensesDAO dao;

	@Override // 예상경비 저장하기
	public void expensesRegist(ExpensesVO expensesVO) throws Exception {

		if (dao.selectE_no() != null) {
			expensesVO.setE_no(dao.selectE_no() + 1);
		} else {
			expensesVO.setE_no(1);
		}
		dao.expensesInsert(expensesVO);
	}

	@Override
	public Integer totalMoney(int e_no, int ed_amount) throws Exception {
		// 총 잔액 - 사용금액 계산해주기
		int total = dao.totalMoney(e_no) - ed_amount;
		
		System.out.println("서비스 계산기 : dao.totalMoney(e_no) : " + dao.totalMoney(e_no));
		System.out.println("서비스 계산기 : ed_amount : " + ed_amount);
		System.out.println("서비스 계산기 : total : " + total);
		return total;
	}

	@Override	//지출내역 저장
	public void amountRegist(StatementVO statementVO, Integer total) throws Exception {
		if (dao.selectEd_no() != null) {
			statementVO.setEd_no(dao.selectEd_no() + 1);
		} else {
			statementVO.setEd_no(1);
		}

		// total하고 e_no같이 넘기기
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("total", total);
		map.put("e_no", statementVO.getE_no());
		dao.updateExpenses(map); // 가져온 total을 총잔액에 update해주고
		dao.amountInsert(statementVO); // 지출내역에는 insert해줌
	}

	@Override	//오늘 사용한 총 금액
	public int todayTotal(int e_no, String ed_date) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("e_no", e_no);
		map.put("ed_date", ed_date);

		//잔액이 없으면 0
		if(dao.todayTotal(map)==null){
			return 0;
		}else{
			return dao.todayTotal(map);
		}
		
	}

	@Override
	public List<Map<String, Object>> course(String id) throws Exception {
		System.out.println("service m_id는 : " +id);
		List<Map<String, Object>> list = new ArrayList<>();	
		
		list = dao.course(id);
		
		for(int i=0; i<list.size(); i++){
			System.out.println((i+1)+"c_id : "+list.get(i).get("c_name"));
		}
		
		System.out.println("service list : " +list);
		return list;
	}

	@Override
	public List<StatementVO> recall(String m_id) throws Exception {
		//만약 m_id에 대한 경비내역이 여러개라면..! 여기서 처리해줘야겠지?

		if(dao.expenseCount(m_id)>1){
			
		}
		return dao.recall(m_id);
	}

}
