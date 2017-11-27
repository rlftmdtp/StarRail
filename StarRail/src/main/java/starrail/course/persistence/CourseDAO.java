package starrail.course.persistence;

import java.util.List;

import starrail.course.domain.IssueInfoVO;

public interface CourseDAO {
	
	public IssueInfoVO issueSelect(String i_name) throws Exception;

}
