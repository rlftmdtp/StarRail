package starrail.course.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import starrail.course.domain.IssueInfoVO;
import starrail.course.persistence.CourseDAO;

@Service
public class CourseServiceImpl implements CourseService {

	@Inject
	private CourseDAO dao;
	
	@Override
	public List<IssueInfoVO> issueList(List<String> nodes) throws Exception {
		List<IssueInfoVO> list = new ArrayList<IssueInfoVO>();
		
		for(int i=0; i<nodes.size(); i++){
			list.add(dao.issueSelect(nodes.get(i)));
		}
		
		return list;
	}

	

}
