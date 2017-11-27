package starrail.partner.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import starrail.course.domain.CourseDetailVO;
import starrail.course.domain.CourseVO;
import starrail.main.domain.UserVO;
import starrail.partner.persistence.PartnerDAO;

@Service
public class PartnerServiceImpl implements PartnerService {

	@Inject
	private PartnerDAO dao;

	@Override
	public List<CourseVO> myCourse_List_service(UserVO userVO) {
		// System.out.println("서비스 : " + userVO);
		return dao.myCourse_List(userVO);
	}

	@Override
	public List<CourseDetailVO> mySchedule_List_service(HashMap<String, Object> map) {
		return dao.mySchedule_List(map);
	}

	@Override
	public List<CourseDetailVO> courseDetail_List_service(Map<String, String> list) {
		List<Integer> list_value = new ArrayList<Integer>();

		for (String mapkey : list.keySet()) {
			int cd_id = Integer.parseInt(list.get(mapkey));
			list_value.add(cd_id);
		}
		System.out.println("*****" + list_value);
		return dao.courseDetail_List(list_value);
	}

}
