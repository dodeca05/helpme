package com.keduit.bpro52.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.keduit.bpro52.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	@Query("SELECT b, w FROM Board b LEFT JOIN b.writer w WHERE b.bno =:bno")
	Object[] getBoardwithWriter(@Param("bno") Long bno);
	
	@Query("select b, r from Board b left join Reply r on r.board = b where b.bno =:bno")
	List<Object[]> getBoardWithReply(@Param("bno") Long bno);
	
	
	@Query(value="SELECT b, w, COUNT(r) "
			+ "FROM Board b LEFT JOIN b.writer w "
			+ "LEFT JOIN Reply r "
			+ "ON r.board = b GROUP BY b")
	Page<Object[]> getBoardWithReplyCount(Pageable pageable);
	
	@Query("SELECT b, w, COUNT(r) FROM"
			+ " Board b LEFT JOIN b.writer"
			+ " w LEFT OUTER JOIN Reply r"
			+ " ON r.board = b WHERE b.bno =:bno")
	Object getBoardByBno(@Param("bno") Long bno);
}
