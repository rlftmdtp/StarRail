package starrail.course.util;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import starrail.course.domain.StationVO;
import starrail.course.domain.TrainTimeVO;

@Component
public class StationParsingUtil {

	public List<String> getCityCode() throws Exception{	//역 목록 불러올 때 쓸 지역코드
		String xmlAddr ="http://openapi.tago.go.kr/openapi/service/TrainInfoService/getCtyCodeList?ServiceKey=TGcClofLdZE%2B0HSLqvPVVLrixz8HFOrgUW2yZUuIASicA0%2BIDXMxYLiT3MCirXZQ2xG%2Bfedyb38VIDSGlB3yzQ%3D%3D";
		
		URL url = new URL(xmlAddr);
		
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		xpp.setInput(bis, "utf-8");
		
		String tag = null;
		int eventType = xpp.getEventType();
		ArrayList<String> list = new ArrayList<String>();
		String str = null;
		
		while(eventType != XmlPullParser.END_DOCUMENT){
			if(eventType == XmlPullParser.START_TAG){
				tag = xpp.getName();
			} else if (eventType == XmlPullParser.TEXT){
				if(tag.equals("citycode")){
					str = xpp.getText();
				}
			} else if (eventType ==XmlPullParser.END_TAG){
				tag = xpp.getName();
				if(tag.equals("item")){
					list.add(str);
				}
			}
			
			eventType=xpp.next();
		}
		
		return list;
	}
	
	public List<StationVO> stationList(List<String> citycode) throws Exception{	//지역코드 이용해서 전체 역 목록 뽑기
		String xmlAddr = "http://openapi.tago.go.kr/openapi/service/TrainInfoService/getCtyAcctoTrainSttnList?serviceKey=TGcClofLdZE%2B0HSLqvPVVLrixz8HFOrgUW2yZUuIASicA0%2BIDXMxYLiT3MCirXZQ2xG%2Bfedyb38VIDSGlB3yzQ%3D%3D&cityCode=";
		List<StationVO> list = new ArrayList<StationVO>();
		
		
		
		for(int i=0; i<citycode.size(); i++){
			
			URL url = new URL(xmlAddr+citycode.get(i));
			
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			xpp.setInput(bis, "utf-8");
			
			String tag = null;
			int eventType = xpp.getEventType();
			String s_id=null;
			String s_name=null;
			
			
			while(eventType != XmlPullParser.END_DOCUMENT){
				StationVO station = new StationVO();
				if(eventType == XmlPullParser.START_TAG){
					tag = xpp.getName();
				} else if (eventType == XmlPullParser.TEXT){
					if(tag.equals("nodeid")){
						s_id=xpp.getText();
						
					} else if(tag.equals("nodename")){
						s_name=xpp.getText();
					}
				} else if (eventType ==XmlPullParser.END_TAG){
					tag = xpp.getName();
					if(tag.equals("item")){
						station.setId(s_id);
						station.setName(s_name);
						list.add(station);
						
					}
				}
				
				eventType=xpp.next();
			}
		}
	
		Collections.sort(list, new StationNameComparator());
		return list;
	}
	
	
	public List<StationVO> arrStationList(String depNode, String depDate, List<StationVO> allNodes) throws Exception{	//선택 가능한 도착역 불러오기
		
		String depPlaceId="&depPlaceId="+depNode;	//출발역ID
		String depPlandTime="&depPlandTime="+depDate.replaceAll("/","");	//출발일 (n일차 버튼 value)
		List<StationVO> arrPlaceIds= allNodes;	//도착역ID (위 리스트 몽땅 넣어줘야 함!)		
		
		String[] trainGradeCodes ={"&trainGradeCode=01","&trainGradeCode=02","&trainGradeCode=03"};	//열차 종류(새마을01 무궁화02 누리로03)
		
		String xmlOrg = "http://openapi.tago.go.kr/openapi/service/TrainInfoService/getStrtpntAlocFndTrainInfo?serviceKey=TGcClofLdZE%2B0HSLqvPVVLrixz8HFOrgUW2yZUuIASicA0%2BIDXMxYLiT3MCirXZQ2xG%2Bfedyb38VIDSGlB3yzQ%3D%3D&numOfRows=100"
							+ depPlaceId + depPlandTime + "&arrPlaceId=";
		
		List<String> arrNames = new ArrayList<String>();
		List<StationVO> list = new ArrayList<StationVO>();
		
		for(int i=0; i<arrPlaceIds.size(); i++){
			for(int j=0; j<trainGradeCodes.length; j++){
				
				
				String xmlAddr = xmlOrg + arrPlaceIds.get(i).getId() + trainGradeCodes[j];
				URL url = new URL(xmlAddr);
				
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				BufferedInputStream bis = new BufferedInputStream(url.openStream());
				xpp.setInput(bis, "utf-8");
				
				String tag = null;
				int eventType = xpp.getEventType();
				String arrName = null;
				
				while(eventType != XmlPullParser.END_DOCUMENT){
					StationVO station = new StationVO();
					if(eventType == XmlPullParser.START_TAG){
						tag = xpp.getName();
					} else if (eventType == XmlPullParser.TEXT){
						if(tag.equals("arrplacename")){
							arrName=xpp.getText();
						}
					} else if (eventType ==XmlPullParser.END_TAG){
						tag = xpp.getName();
						if(tag.equals("item")){
							if(!(arrNames.contains(arrName))){
								arrNames.add(arrName);
								station.setId(arrPlaceIds.get(i).getId());
								station.setName(arrName);
								list.add(station);
							}
						}
					}
					
					eventType=xpp.next();
				}
				
			}
			
		}
		
		Collections.sort(list, new StationNameComparator());
		return list;
	}
	
	
	public List<TrainTimeVO> getTimeTable(String depNode, String depDate, String arrNode, int selectedTime) throws Exception{
		
		String depPlaceId="&depPlaceId="+depNode;	//출발역ID
		String depPlandTime="&depPlandTime="+depDate.replaceAll("/","");	//출발일 (n일차 버튼 value)
		String arrPlaceId="&arrPlaceId="+arrNode;	//도착역ID
		
		String hopingTime = "";
		
		if(selectedTime <10){hopingTime = "0"+selectedTime+"0000";}
		else{hopingTime = selectedTime+"0000";}
		
		String[] trainGradeCodes ={"&trainGradeCode=01","&trainGradeCode=02","&trainGradeCode=03"};	//열차 종류(새마을01 무궁화02 누리로03)
		
		String xmlOrg = "http://openapi.tago.go.kr/openapi/service/TrainInfoService/getStrtpntAlocFndTrainInfo?serviceKey=TGcClofLdZE%2B0HSLqvPVVLrixz8HFOrgUW2yZUuIASicA0%2BIDXMxYLiT3MCirXZQ2xG%2Bfedyb38VIDSGlB3yzQ%3D%3D&numOfRows=100"
							+ depPlaceId + depPlandTime + arrPlaceId;
		
		List<TrainTimeVO> list = new ArrayList<TrainTimeVO>();
		
		for(int i=0; i<trainGradeCodes.length; i++){
			String xmlAddr = xmlOrg + trainGradeCodes[i];
			URL url = new URL(xmlAddr);
			
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			xpp.setInput(bis, "utf-8");
			
			String tag = null;
			int eventType = xpp.getEventType();
			String arrTime=null;
			String depTime=null;
			String trainType=null;
			
			while(eventType != XmlPullParser.END_DOCUMENT){
				TrainTimeVO vo = new TrainTimeVO();
				if(eventType == XmlPullParser.START_TAG){
					tag = xpp.getName();
				} else if (eventType == XmlPullParser.TEXT){
					if(tag.equals("arrplandtime")){
						arrTime=xpp.getText();
					} else if(tag.equals("depplandtime")){
						depTime=xpp.getText();
					} else if(tag.equals("traingradename")){
						trainType=xpp.getText();
					}
				} else if (eventType ==XmlPullParser.END_TAG){
					tag = xpp.getName();
					if(tag.equals("item")){
						if(Integer.parseInt(depDate+hopingTime)-20000 <= Integer.parseInt(arrTime)
								&& Integer.parseInt(arrTime)<=Integer.parseInt(depDate+hopingTime)+20000){
							vo.setArrTime(arrTime);
							vo.setDepTime(depTime);
							vo.setTrainType(trainType);
							
							list.add(vo);	
						}
					}
				}
				
				eventType=xpp.next();
			}
			
			
		}
		
		Collections.sort(list);
		
		return list;
	}
	
	
/*	public static void main(String[] args){
		StationParsingUtil a = new StationParsingUtil();
		try {
			List<StationVO> list = a.stationList(a.getCityCode());
			System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
