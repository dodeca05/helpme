package com.keduit.bpro52.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.keduit.bpro52.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
	
	@Modifying
	@Query("DELETE FROM Reply r WHERE r.board.bno = :bno")
	void delteBybno(Long bno);
}
