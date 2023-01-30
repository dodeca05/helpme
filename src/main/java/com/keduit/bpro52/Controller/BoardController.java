package com.keduit.bpro52.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.bpro52.dto.BoardDTO;
import com.keduit.bpro52.dto.PageRequestDTO;
import com.keduit.bpro52.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;

	@GetMapping("/qweqweasd")
	public void qweqweasd(){

	}
	
	@GetMapping("/list")
	public void getList(PageRequestDTO pageRequestDTO, Model model) {
		
		log.info("getList");
		log.info("PageRequestDTO : " + pageRequestDTO);
		System.out.println( boardService.getList(pageRequestDTO));
		System.out.println(pageRequestDTO);
		model.addAttribute("result", boardService.getList(pageRequestDTO));
	}
	
	@GetMapping("/register")
	public void getRegister() {
		log.info("getReigster");
		
	}
	
	@PostMapping("/register")
	public String postRegister(BoardDTO dto, RedirectAttributes redirectAttributes) {
		log.info("postRegister");
		Long bno = boardService.register(dto);
		log.info("BNO : :"+ bno);
		redirectAttributes.addFlashAttribute("msg" , bno);
		return "redirect:/board/list";
		
	}
	
	@GetMapping({"/read","/modify"})
	public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model) {
		
		log.info("bno : " + bno);
		
		BoardDTO boardDTO = boardService.getLine(bno);
		
		log.info("boardDTO : " + boardDTO);		
		model.addAttribute("dto", boardDTO);
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes redirectAttributes) {
		System.out.println("bno 삭제대기");
		
		boardService.removeWithReplies(bno);
		redirectAttributes.addFlashAttribute("msg", bno);
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO RequestDTO,
			RedirectAttributes redirectAttributes) {
		System.out.println("modify 호출");
		System.out.println();
		boardService.modify(dto);
		
		redirectAttributes.addAttribute("bno", dto.getBno());
		
		return "redirect:/board/read";
	}
}

