package com.kh.boot.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Form {
    private int formNo;
    private String formTitle;
    private String formWriter;
    private String formResponseUrl;
    private String formDashBoardUrl;
    private String createDate;
    private String status;
}
