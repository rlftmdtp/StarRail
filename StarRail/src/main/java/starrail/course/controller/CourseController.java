package starrail.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/course/*")
public class CourseController {
	
	@RequestMapping(value="/makeCourse",method=RequestMethod.GET)
	public void courseGET(){
		
		
	}
	
}
