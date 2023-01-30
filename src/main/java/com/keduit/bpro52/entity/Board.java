package com.keduit.bpro52.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="board")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Board extends BaseEntity{
		//PK
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long bno;
		
		@Column(length=200, 
				nullable=false)
		private String title;
		
		@Column(length=2000, 
				nullable=false)
		private String content;
		
		//FK
		//Many To One EAGER 은 쓰레기같이 많이함 그래서 필요할떄만 써야함.
		@ManyToOne(fetch = FetchType.LAZY)
		private Member writer;
		
		public void change(String title, String content) {
			this.title = title;
			this.content = content;
		}
}
