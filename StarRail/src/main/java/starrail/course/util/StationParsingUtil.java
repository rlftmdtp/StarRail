package starrail.course.util;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import starrail.course.domain.StationVO;

@Component
public class StationParsingUtil {

	public List<String> getCityCode() throws Exception{
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
	
	public List<StationVO> stationList(List<String> citycode) throws Exception{
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
