package starrail.review.service;

import java.util.List;
import java.util.Map;

import starrail.review.domain.ReviewVO;
import starrail.review.domain.Hash_SearchVO;
import starrail.review.domain.ReviewCriteria;
import starrail.review.domain.ReviewSearchCriteria;


public interface ReviewService {

	public void register(ReviewVO review) throws Exception;
	
	public ReviewVO read(Integer r_no) throws Exception;
	
	public void modify(ReviewVO review) throws Exception;
	
	public void remove(Integer r_no) throws Exception;
	
	public List<ReviewVO> list() throws Exception;
	
	public List<ReviewVO> listCriteria(ReviewCriteria cri) throws Exception;
	
	public int listCountCriteria(ReviewCriteria cri) throws Exception;
	
	public List<ReviewVO> listSearchCriteria(ReviewSearchCriteria cri) throws Exception;
	
	public int listSearchCount(ReviewSearchCriteria cri) throws Exception;

	public List<String> getAttach(Integer r_no) throws Exception;
	
	public int hash_no()throws Exception;
	
	public void tagAdd(Integer h_no, Integer r_no, String r_hash) throws Exception;

	public int selectR_no() throws Exception;
	
	public void hashtagInsert(ReviewVO review) throws Exception;
	
	public String specialCharacter_replace(String str) throws Exception;

	public List<String> hashSearch() throws Exception;
}
