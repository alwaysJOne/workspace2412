package com.kh.jpa.dto;

import com.kh.jpa.entity.Board;
import com.kh.jpa.entity.BoardTag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class BoardDto {

    @Getter
    @AllArgsConstructor
    public static class Create{
        private String board_title;
        private String board_content;
        private String user_id;
        private MultipartFile file;
        private List<String> tags;

        public Board toEntity() {
            return Board.builder()
                    .boardTitle(this.board_title)
                    .boardContent(this.board_content)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Update{
        private String board_title;
        private String board_content;
        private MultipartFile file;
        private List<String> tags;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {

        private Long board_no;
        private String board_title;
        private String board_content;
        private String origin_name;
        private String change_name;
        private LocalDateTime create_date;
        private Integer count;
        private String user_id;
        private String user_name;
        private List<String> tags;

        public static Response ofSimple(Long boardNo, String boardTitle, String originName,
                                         Integer count, LocalDateTime createDate, String userId) {
            return Response.builder()
                    .board_no(boardNo)
                    .board_title(boardTitle)
                    .origin_name(originName)
                    .count(count)
                    .create_date(createDate)
                    .user_id(userId)
                    .build();
        }

        public static Response of(Long boardNo, String boardTitle, String boardContent,
                                   String originName, String changeName, Integer count,
                                   LocalDateTime createDate, String userId, String userName,
                                   List<String> tags) {
            return Response.builder()
                    .board_no(boardNo)
                    .board_title(boardTitle)
                    .board_content(boardContent)
                    .origin_name(originName)
                    .change_name(changeName)
                    .count(count)
                    .create_date(createDate)
                    .user_id(userId)
                    .user_name(userName)
                    .tags(tags)
                    .build();
        }
    }
}
