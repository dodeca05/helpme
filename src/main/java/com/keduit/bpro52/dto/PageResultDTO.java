package com.keduit.bpro52.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResultDTO<DTO, Entity> {

	private List<DTO> dtoList;

	private int totalPage;
	private int page;
	private int size;

	private int start;
	private int end;

	private boolean prev;
	private boolean next;

	private List<Integer> pageList;

	public PageResultDTO(Page<Entity> result, Function<Entity, DTO> fn) {
		dtoList = result.stream().map(fn).collect(Collectors.toList());

		totalPage = result.getTotalPages();
		makePageList(result.getPageable());

	}

	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageSize();
		int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

		this.start = tempEnd - 9;
		this.end = totalPage > tempEnd ? tempEnd + 1 : totalPage + 1;

		this.prev = start > 1;
		this.next = totalPage > tempEnd;

		this.pageList = IntStream.rangeClosed(start, end)
				.boxed()
				.collect(Collectors.toList());

	}

}
