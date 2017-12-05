package starrail.recommend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.INTERNAL;

import starrail.recommend.domain.HashSearchVO2;


public interface RecommenderService {
	public List<Integer> preferList_service(int m_no) throws Exception;
	public List<Integer> recommender_service(int m_no);
	public List<HashSearchVO2> tagRecommend_service(List<Integer> list);
}
