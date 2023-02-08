package com.coding404.myweb.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.TripVO;
import com.coding404.myweb.trip.service.TripService;

@Controller
@RequestMapping("/trip")
public class NoticeController {
	
	@Autowired
	@Qualifier("tripService")
	private TripService tripService;
	
	@RequestMapping("/notice_list")
	public String notice_list(Model model) {
		
		/*
		 * service, mapper������ getList �Լ��� �����ϰ�
		 * ��Ϲ�ȣ �������� �����͸� ��ȸ�ؼ� ������ ���´�
		 * model�� ��Ƽ� 
		 * ȭ�鿡���� �ݺ������� ó�� 
		 */
		ArrayList<TripVO> Triplist = tripService.getTripList();
		model.addAttribute("Triplist", Triplist);
		
		
		return "trip/notice_list";
	}
	
	//���ȭ��
	@RequestMapping("/notice_write")
	public String notice_write() {
		
		return "trip/notice_write";
	}
	
	@RequestMapping("/notice_view" )
	public String notice_view(@RequestParam("tno") int tno, Model model,
							 HttpServletResponse response,
							 HttpServletRequest request) {
		
		// Ŭ���� �� ��ȣ�� ���� ������ ��ȸ
		TripVO vo = tripService.getContent(tno);
		model.addAttribute("vo", vo);
		
		//��ȸ�� - ��Ű �Ǵ� ���� �̿��ؼ� ��ȸ�� �ߺ� ����
		tripService.upHit(tno);
		
		Cookie cookie = new Cookie("key", "1");
		cookie.setMaxAge(30);
		response.addCookie(cookie);
		
		//������ ������
		ArrayList<TripVO> Tlist = tripService.getPrevNext(tno);
		model.addAttribute("Tlist", Tlist);
		
		return "trip/notice_view";
	}

	
	//����ȭ��
	@RequestMapping("/notice_modify")
	public String notice_modify(@RequestParam("tno") int tno,
								Model model) {
		
		
		// Ŭ���� �� ��ȣ�� ���� ������ ��ȸ
		TripVO vo = tripService.getContent(tno);
		model.addAttribute("vo", vo);
		
		return "trip/notice_modify";
	}
	
	//�� ����
	@RequestMapping(value="/modifyForm", method = RequestMethod.POST)
	public String modifyForm (TripVO vo, RedirectAttributes re) {
		
		//������Ʈ �۾� - ȭ�鿡���� tno�� �ʿ������� �������� �ʾƾ� �ϱ� ������ hidden�±� �̿��ؼ� �Ѱ��ֱ�!
//		System.out.println(vo.toString());
		
		int result = tripService.noticeModify(vo);
		String msg = result == 1 ? "���ǻ����� �����Ǿ����ϴ�" : "������ �����߽��ϴ�";
		re.addFlashAttribute("msg", msg);
		
		return "redirect:/trip/notice_list";
//		return "redirect:/trip/notice_view?tno=" + vo.getTno();
		
	}
	
	
	
	//�� ����
	@RequestMapping(value="/deleteForm", method=RequestMethod.POST)
	public String deleteForm(@RequestParam("tno") int tno,
							RedirectAttributes re) {
		
		int result1 = tripService.noticeDelete(tno);
		String msg = result1 == 1 ? "���������� ���� �����Ǿ����ϴ�" : "������ �����߽��ϴ�";
		re.addFlashAttribute("msg", msg);
			
		return "redirect:/trip/notice_list";
	}
	
	
	
	
	
//	//����, �� ȭ���� ������ �����ϴٸ�
//	//void�� ������ �� ������ ���!! view�� ������ view�� ������ modify�� ������ modify�� ������
//	@RequestMapping({"/notice_view", "/notice_modify"})
//	public void notice_view(@RequestParam("tno") int tno, 
//							Model model) {
//		TripVO vo = tripService.getContent(tno);
//		model.addAttribute("vo", vo);
//		
//	}
//	
	
	
	
	//�۵��
	@RequestMapping(value="/registForm", method = RequestMethod.POST)
	public String registForm(TripVO vo, RedirectAttributes ra) {
		System.out.println(vo.toString());
		
		int result = tripService.noticeRegist(vo);
		String msg = result == 1 ? "���ǻ����� ���� ��ϵǾ����ϴ�" : "���ǻ��� ��Ͽ� �����Ͽ����ϴ�";
		ra.addFlashAttribute("msg", msg);

		return "redirect:/trip/notice_list";
	}
	
}
