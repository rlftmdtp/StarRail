package starrail.course.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import starrail.course.domain.IssueInfoVO;

@Repository
public class CourseDAOImpl implements CourseDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "starrail.course.mappers.courseMapper";

	@Override
	public IssueInfoVO issueSelect(String i_name) throws Exception {
		return session.selectOne(namespace+".issueSelect", i_name);
	}

}
