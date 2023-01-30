package com.keduit.bpro52.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro52.dto.BoardDTO;
import com.keduit.bpro52.dto.PageRequestDTO;
import com.keduit.bpro52.dto.PageResultDTO;
import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.service.BoardService;

@SpringBootTest
public class BoardServiceTests {
	
	@Test
	public void test() {
		System.out.println("Ttt");
	}
	
	@Autowired
	private BoardService boardService;
	
	@Test
	public void testRegister() {

		BoardDTO boardDTO = BoardDTO.builder().title("ttt").content("cccc").writerEmail("user1@abc.com").build();
		System.out.println("boardDTO" + boardDTO);
		
		Long bno = boardService.register(boardDTO);
		
		System.out.println("dto : " + boardDTO);
		
	}
	
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);
		
		for (BoardDTO boardDTO : result.getDtoList()) {
			System.out.println(boardDTO);
		}
		
	}
	
	
	@Test
	public void testGetLine() {
		Long bno = 95L;
		BoardDTO  boardDTO = boardService.getLine(bno); 
	}
	
	@Test
	public void testDeleteLine() {
		Long bno = 75L;
		boardService.removeWithReplies(bno);
	}
	
	@Test
	public void testModify() {
		
		BoardDTO boardDTO = BoardDTO.builder()
				.bno(2L)
				.title("t")
				.content("t")
				.build();
		
	}
}
