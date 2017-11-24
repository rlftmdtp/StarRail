package starrail.course.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import starrail.course.domain.StationVO;
import starrail.course.util.StationParsingUtil;

@Controller
@RequestMapping("/course/*")
public class CourseController {
	
	@Inject
	private StationParsingUtil stationParser;
	
	@RequestMapping(value="/makeCourse",method=RequestMethod.GET)
	public void courseGET(){
			
	}
	
	@RequestMapping(value="/depList", method=RequestMethod.POST)
	public ResponseEntity<List<StationVO>> startListPOST() throws Exception{

		ResponseEntity<List<StationVO>> entity = null;
		
		
		try {
			List<String> cityCodes = stationParser.getCityCode();
			List<StationVO> s_stations = stationParser.stationList(cityCodes);
			System.out.println(cityCodes);
			System.out.println(s_stations);
			entity = new ResponseEntity<List<StationVO>>(s_stations, HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@RequestMapping(value="/selectDep", method=RequestMethod.POST)
	public ResponseEntity<String> sStaionPOST(@RequestBody String selectedDep) throws Exception{

		ResponseEntity<String> entity = null;
		try {
			
			
			entity = new ResponseEntity<String>(selectedDep, HttpStatus.OK);  //200OK
							//ResponseEntity<String>는 실행결과, HttpStatus결과를 가져와 넣기 위한 것
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);  //400error
		}
		return entity;
	}
	
	

}
