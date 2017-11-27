package starrail.course.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/course/*")
public class CourseController {
	
	
	
	@RequestMapping(value="/makeCourse",method=RequestMethod.GET)
	public void courseGET(){
			
	}
	
	@RequestMapping(value="/startStation", method=RequestMethod.POST)
	public ResponseEntity<String> sStaionPOST(@RequestBody String startStation) throws Exception{

		ResponseEntity<String> entity = null;
		try {
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);  //200OK
							//ResponseEntity<String>는 실행결과, HttpStatus결과를 가져와 넣기 위한 것
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);  //400error
		}
		return entity;
	}
	
}
