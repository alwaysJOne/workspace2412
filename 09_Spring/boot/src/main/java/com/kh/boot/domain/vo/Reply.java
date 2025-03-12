package com.kh.boot.domain.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reply {
    private int replyNo;
    private String replyContent;
    private int refBno;
    private String replyWriter;
    private String createDate;
    private String status;
}
