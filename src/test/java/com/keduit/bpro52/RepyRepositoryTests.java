package com.keduit.bpro52;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Reply;
import com.keduit.bpro52.repository.ReplyRepository;

@SpringBootTest
public class RepyRepositoryTests {
	
	@Autowired
	private ReplyRepository replyRepository;
	
	
	@Test
	public void insertReply() {
		IntStream.rangeClosed(1,100).forEach(i -> {
			long bno = (long)(Math.random() * 100) + 1 ;
			Board board = Board.builder().bno(bno).build();
			
			Reply reply = Reply.builder().text("댓글......" + i)
					.replyer("내이름......" + i).board(board).build();
			replyRepository.save(reply);
			
		});
	}
	
	@Test
	public void readReply() {
		Optional<Reply> result = replyRepository.findById(1L);
		Reply reply = result.get();
		System.out.println(reply);
		
	}
	
}
