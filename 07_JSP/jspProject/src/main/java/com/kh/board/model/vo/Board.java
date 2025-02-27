package com.kh.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int boardNo;
	private int boardType;
	private String categoryNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private int count;
	private String createDate;
	private String status;
}
