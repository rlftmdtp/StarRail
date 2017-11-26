package starrail.course.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import starrail.course.domain.StationVO;
import starrail.course.domain.TrainTimeVO;
import starrail.course.util.StationParsingUtil;

@Controller
@RequestMapping("/course/*")
public class CourseController {
	
	@Inject
	private StationParsingUtil stationParser;
	
	@RequestMapping(value="/makeCourse",method=RequestMethod.GET)	//코스짜기 페이지 열기
	public void courseGET(){
			
	}
	
	@RequestMapping(value="/depList", method=RequestMethod.POST)	//n일차 선택 후 출발역 리스트 불러오기
	public ResponseEntity<List<StationVO>> depListPOST() throws Exception{

		ResponseEntity<List<StationVO>> entity = null;
		
		
		try {
			List<String> cityCodes = stationParser.getCityCode();
			List<StationVO> s_stations = stationParser.stationList(cityCodes);
			
			entity = new ResponseEntity<List<StationVO>>(s_stations, HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@RequestMapping(value="/arrList", method=RequestMethod.POST)	//출발역 선택 후 가능한 도착역 리스트 불러오기
	@ResponseBody
	public ResponseEntity<List<StationVO>> arrListPOST(@RequestParam(value="depNode", required=false) String depNode,
														@RequestParam(value="depDate", required=false) String depDate) throws Exception{
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		
		ResponseEntity<List<StationVO>> entity = null;
		try {
			List<String> cityCodes = stationParser.getCityCode();
			List<StationVO> allNodes = stationParser.stationList(cityCodes);
			List<StationVO> arrStations = stationParser.arrStationList(depNode, depDate, allNodes);
			
			entity = new ResponseEntity<List<StationVO>>(arrStations, responseHeaders, HttpStatus.OK);  //200OK
							//ResponseEntity<String>는 실행결과, HttpStatus결과를 가져와 넣기 위한 것
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return entity;
	}
	
	@RequestMapping(value="/trainTime", method=RequestMethod.POST)	//출발 희망시간 선택 후 시간표 출력
	@ResponseBody
	public ResponseEntity<List<TrainTimeVO>> trainTimPOST(@RequestParam(value="depNode", required=false) String depNode,
															@RequestParam(value="depDate", required=false) String depDate,
															@RequestParam(value="arrNode", required=false) String arrNode,
															@RequestParam(value="selectedTime", required=false)int selectedTime) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		ResponseEntity<List<TrainTimeVO>> entity = null;
		try {
			List<TrainTimeVO> traintimes = stationParser.getTimeTable(depNode, depDate, arrNode, selectedTime);
			entity = new ResponseEntity<List<TrainTimeVO>>(traintimes, responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	

	
	
	

}
