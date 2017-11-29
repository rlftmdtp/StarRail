package starrail.reservation.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import starrail.reservation.domain.ReservationVO;
import starrail.reservation.service.ReservationService;

@Controller
@RequestMapping("/reservation/*")
public class ReservationController {

@Inject
private ReservationService service;
	
//���� ����
@RequestMapping(value="/Reservation_view", method=RequestMethod.GET)
public void registGET(ReservationVO reservation, Model model){
	System.out.println("Reservation registGET ����");
}
//���� ����
@RequestMapping(value="Reservation_view", method=RequestMethod.POST)
public String registPOST(ReservationVO reservation, RedirectAttributes rttr)throws Exception{
	System.out.println("Reservation registPOST ����");
	System.out.println(reservation.toString());
	
	service.regist(reservation);
	rttr.addFlashAttribute("msg", "success");
	
	return "redirect:/reservation/Reservation_result";
}
	

//���� ���� ��ȸ
@RequestMapping(value="Reservation_result", method=RequestMethod.GET)
public void read(@RequestParam("res_no") int res_no, Model model) throws Exception{
	System.out.println("reservation read...��");
	model.addAttribute(service.reservationRead(res_no));
}
}
