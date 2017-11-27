package starrail.review.persistence;

import java.util.List;
import java.util.Map;

import starrail.review.domain.FileVO;
import starrail.review.domain.Hash_SearchVO;
import starrail.review.domain.ReviewVO;
import starrail.review.domain.ReviewCriteria;
import starrail.review.domain.ReviewSearchCriteria;


public interface ReviewDao {

	public void insertReview(ReviewVO review) throws Exception;
	
	public ReviewVO selectReview(Integer r_no) throws Exception;
	
	public void updateReview(ReviewVO review) throws Exception;
	
	public void deleteReview(Integer r_no) throws Exception;
	
	public List<ReviewVO> listReview() throws Exception;
	
	public List<ReviewVO> listPage(int Page) throws Exception;
	
	public List<ReviewVO> listCriteria(ReviewCriteria cri) throws Exception;
	
	public List<ReviewVO> listSearch(ReviewSearchCriteria cri) throws Exception;
	
	public int listSearchCount(ReviewSearchCriteria cri) throws Exception;
	
	public Integer selectR_no() throws Exception;
	
	public int countPaging(ReviewCriteria cri) throws Exception;

	public void addAttach(FileVO rf_fullname) throws Exception;
	int getR_no() throws Exception;
	
	public List<String> getAttach(Integer r_no) throws Exception;
	
	public void deleteAttach(Integer r_no) throws Exception;
	public void repalceAttach(String rf_fullName, Integer r_no) throws Exception;

	public void updateR_hit(Integer r_no) throws Exception;

	public void tagAdd(Integer h_no, Integer r_no, String r_hash) throws Exception; // 여기말한거야 잠만
	
	public Integer hash_no() throws Exception;
	
	public List<String> HashSearch() throws Exception;
	}
