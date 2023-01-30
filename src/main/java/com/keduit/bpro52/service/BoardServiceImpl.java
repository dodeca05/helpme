package com.keduit.bpro52.service;

import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.keduit.bpro52.dto.BoardDTO;
import com.keduit.bpro52.dto.PageRequestDTO;
import com.keduit.bpro52.dto.PageResultDTO;
import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Member;
import com.keduit.bpro52.repository.BoardRepository;
import com.keduit.bpro52.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

	
	private final BoardRepository repository;
	private final ReplyRepository replyRepository;
	
	@Override
	public Long register(BoardDTO dto) {
		log.info("dto : " + dto);
		System.out.println("..............");
		System.out.println("..............");
		System.out.println("..............");
		System.out.println("..............");
		Board board = dtoToEntity(dto);
		repository.save(board);
		
		return board.getBno();
	}


	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pagerequestDTO) {
		
		log.info("PageRequestDTO : " + pagerequestDTO);
		Function<Object[], BoardDTO> fn = (entity -> entityToDTO(
				(Board)entity[0], 
				(Member)entity[1], 
				(Long)entity[2]));
		
		Page<Object[]> result = repository.getBoardWithReplyCount(pagerequestDTO.getPageable(Sort.by("bno").descending()));
		
		return new PageResultDTO<>(result, fn);
	}


	@Override
	public BoardDTO getLine(Long bno) {
		Object result = repository.getBoardByBno(bno);
		
		Object[] arr = (Object[]) result;
		
		return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
	}


	@Override
	@Transactional
	public void removeWithReplies(Long bno) {
		System.out.println("삭제직전 :" + bno);
		replyRepository.delteBybno(bno);
	}


	@Override
	@Transactional
	public void modify(BoardDTO boardDTO) {
		
		Board board = repository.getById(boardDTO.getBno());
		
		if (board != null) {
			board.change(boardDTO.getTitle(), boardDTO.getContent());
		}
		
		repository.save(board);
		
	}

}
