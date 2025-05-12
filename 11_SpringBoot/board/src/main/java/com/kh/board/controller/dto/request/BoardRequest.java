package com.kh.board.controller.dto.request;

import com.kh.board.entity.Board;
import lombok.Getter;
import lombok.Setter;

public class BoardRequest {

    @Getter
    @Setter
    public static class CreateDTO {

        private String user_id;
        private String title;
        private String contents;
        private String file_name;

        public Board toEntity() {
            return Board.builder()
                        .memberEmail(user_id)
                        .title(title)
                        .contents(contents)
                        .fileName(file_name)
                        .build();
        }
    }
}
