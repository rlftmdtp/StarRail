package starrail.recommend.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import starrail.recommend.domain.HashSearchVO2;


@Repository
public class RecommenderDAOImpl implements RecommenderDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "starrail.recommend.mappers.partnerMapper";
	
	@Override
	public List<Map<String, Integer>> preferList() {
		return session.selectList(namespace+".prefer_Select");
	}

	@Override
	public List<HashSearchVO2> tagRecommend(List<Integer> list) {
		//System.out.println("dao : "+ session.selectList(namespace+".tagRecommend_Select", list));
		return session.selectList(namespace+".tagRecommend_Select", list);
	}
}
