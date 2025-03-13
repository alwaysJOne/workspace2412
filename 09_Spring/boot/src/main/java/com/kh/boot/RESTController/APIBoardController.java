package com.kh.boot.RESTController;

import com.kh.boot.domain.vo.Board;
import com.kh.boot.domain.vo.Reply;
import com.kh.boot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class APIBoardController {

    private final BoardService boardService;

    @PostMapping("/reply")
    public String insertReply(Reply r) {
        return boardService.insertReply(r) > 0 ? "success" : "fail";
    }

    @GetMapping("/reply")
    public ArrayList<Reply> getReplyList(int boardNo) {
        return boardService.selectReplyList(boardNo);
    }
}
