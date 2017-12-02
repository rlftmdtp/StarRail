package starrail.expenses.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import starrail.expenses.domain.ExpensesVO;
import starrail.expenses.domain.StatementVO;
import starrail.expenses.persistence.ExpensesDAO;

@Service
public class ExpensesServiceImpl implements ExpensesService {

	@Inject
	private ExpensesDAO dao;

	@Override // ������ �����ϱ�
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
		// �� �ܾ� - ���ݾ� ������ֱ�
		int total = dao.totalMoney(e_no) - ed_amount;
		
		System.out.println("���� ���� : dao.totalMoney(e_no) : " + dao.totalMoney(e_no));
		System.out.println("���� ���� : ed_amount : " + ed_amount);
		System.out.println("���� ���� : total : " + total);
		return total;
	}

	@Override	//���⳻�� ����
	public void amountRegist(StatementVO statementVO, Integer total) throws Exception {
		if (dao.selectEd_no() != null) {
			statementVO.setEd_no(dao.selectEd_no() + 1);
		} else {
			statementVO.setEd_no(1);
		}

		// total�ϰ� e_no���� �ѱ��
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("total", total);
		map.put("e_no", statementVO.getE_no());
		dao.updateExpenses(map); // ������ total�� ���ܾ׿� update���ְ�
		dao.amountInsert(statementVO); // ���⳻������ insert����
	}

	@Override	//���� ����� �� �ݾ�
	public int todayTotal(int e_no, String ed_date) throws Exception {
		System.out.println("���� : "+ e_no);
		System.out.println("���� : "+ ed_date);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("e_no", e_no);
		map.put("ed_date", ed_date);
		System.out.println("����� ���� ���� �� �ݾ� �פ��ϱ�  : " +dao.todayTotal(map));
		if(dao.todayTotal(map)==null){
			return 0;
		}else{
			return dao.todayTotal(map);
		}
		
	}

}
