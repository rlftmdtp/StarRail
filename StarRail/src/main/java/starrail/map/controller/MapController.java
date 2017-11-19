package starrail.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/map/*")
public class MapController {
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void viewGET() throws Exception{
		
	}

}
