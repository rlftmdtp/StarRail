package starrail.map.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import starrail.map.service.MapService;

@RestController
@RequestMapping("/maprest/*")
public class MapRestController {
	
	@Inject
	private MapService service;
	
	public static final String clientId = "FeSf9NchU5GMk0kap7Kn";//애플리케이션 클라이언트 아이디값";
	public static final String clientSecret = "PFQhfHmyH1";//애플리케이션 클라이언트 시크릿값";
	
	@RequestMapping(value="/tourlist/{station}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> tourList(@PathVariable("station") String station){
		System.out.println("누른곳의 역은 " +station);
	
		useNaverAPI(station + "역 맛집");
		
		
		return null;
	}
	
	public void useNaverAPI(String station){
		 try {
	            String text = URLEncoder.encode(station, "UTF-8");
	            String apiURL = "https://openapi.naver.com/v1/search/local?query="+ text; // json 결과
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            System.out.println(response.toString());
	        } catch (Exception e) {
	            System.out.println(e);
	        } 
	}
}
