package starrail.course.service;

import java.util.List;

import starrail.course.domain.IssueInfoVO;

public interface CourseService {
	
	public List<IssueInfoVO> issueList(List<String> nodes) throws Exception;

}
