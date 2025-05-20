package com.kh.jpa.controller;

import com.kh.jpa.entity.Board;
import com.kh.jpa.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    /*
    page 보고자하는 페이지 번호
    size 몇개씩 가지고 올것인지
    sort 정렬 기준 : 속성, 방향 (boardTitle,desc)
     */
    @GetMapping
    public List<Board> getBoards(
            @PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
        boardService.getBoardList(pageable);
        return null;
    }
}
