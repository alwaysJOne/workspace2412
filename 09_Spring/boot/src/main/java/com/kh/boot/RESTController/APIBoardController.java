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

    //produces : 해당 타입으로 응답을 반환해 줘.
    @GetMapping(value = "/topn", produces = "application/json; charset=UTF-8")
    public ArrayList<Board> getBoardTopN(String order, int limit) {
        return boardService.getBoardTopN(order, limit);
    }
}
