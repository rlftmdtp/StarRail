package starrail.recommend.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import starrail.recommend.domain.HashSearchVO2;


public interface RecommenderDAO {
	public  List<Map<String, Integer>> preferList();
	public List<HashSearchVO2> tagRecommend(List<Integer> list);
}
