package com.keduit.bpro52;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.keduit.bpro52.entity.Board;
import com.keduit.bpro52.entity.Member;
import com.keduit.bpro52.repository.BoardRepository;

@SpringBootTest
public class BoardRepositoryTests {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void insertBoard() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = Member.builder().email("user" + i + "@abc.com").build();
			Board board = Board.builder().title("title....." + i).content("content....." + i).writer(member).build();
			
			boardRepository.save(board);
			
		});
	}
	
	@Test
	@Transactional
	public void testRead(){
		Optional<Board> result = boardRepository.findById(75L);
		
		Board board = result.get();
		System.out.println(board);
	}
	
	@Test
	public void testReadWithWriter() {
		Object[] resultArr = boardRepository.getBoardwithWriter(75L);
		System.out.println(Arrays.toString(resultArr));
	}
	
	@Test
	@Transactional
    public void testReadWithReply() {
        List<Object[]> result = boardRepository.getBoardWithReply(75L);

        System.out.println("---------------------------------------");
        for(Object[] arr : result){
            System.out.println(Arrays.deepToString(arr));
        }
    }
	@Test
	public void testWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		
		Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
		
		result.get().forEach(row -> {
			Object[] arr = row;
			
			System.out.println(Arrays.toString(arr));
		});
	}
	
	@Test
	public void testReadOne() {
		Object result = boardRepository.getBoardByBno(75L);
		Object[] arr = (Object[])result;
		
		System.out.println(Arrays.toString(arr));
		
		
	}
	
	
}
