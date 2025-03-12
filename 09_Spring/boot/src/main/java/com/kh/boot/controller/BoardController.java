package com.kh.boot.controller;

import com.kh.boot.domain.vo.Board;
import com.kh.boot.domain.vo.PageInfo;
import com.kh.boot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("list.bo")
    public String selectBoardList(@RequestParam(defaultValue = "1") int cpage, Model model) {
        int boardCount = boardService.selectBoardCount();

        PageInfo pi = new PageInfo(boardCount, cpage, 10, 5);
        ArrayList<Board> list = boardService.selectBoardList(pi);

        model.addAttribute("list", list);
        model.addAttribute("pi", pi);
        return "board/boardListView";
    }

    @GetMapping("enrollForm.bo")
    public String enrollForm() {return "board/boardEnrollForm";}

    @PostMapping("insert.bo")
    public String insertBoard(@ModelAttribute Board board, MultipartFile upfile) {
        System.out.println(board);
        System.out.println(upfile);

        if(!upfile.getOriginalFilename().equals("")){

        }
        return null;
    }
}
