package starrail.map.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import starrail.map.service.MapService;

@Controller
@RequestMapping("/map/*")
public class MapController {
	
	@Inject
	private MapService service;
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void viewGET() throws Exception{
		
	}
	

}
