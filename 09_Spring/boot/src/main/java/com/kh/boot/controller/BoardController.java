package com.kh.boot.controller;

import com.kh.boot.domain.vo.Board;
import com.kh.boot.domain.vo.PageInfo;
import com.kh.boot.service.BoardService;
import com.kh.boot.utils.Template;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    public String insertBoard(@ModelAttribute Board board, MultipartFile upfile, HttpSession session, Model model) {
        System.out.println(board);
        System.out.println(upfile);

        if(!upfile.getOriginalFilename().equals("")){
            String changeName = Template.saveFile(upfile, session, "/resources/uploadfile/");

            board.setChangeName("/resources/uploadfile/" + changeName);
            board.setOriginName(upfile.getOriginalFilename());
        }

        int result = boardService.insertBoard(board);

        if(result > 0){
            session.setAttribute("alertMsg", "게시글 작성 성공");
            return "redirect:/list.bo";
        } else {
            model.addAttribute("errorMsg", "게시글 작성 실패");
            return "common/errorPage";
        }
    }

    @GetMapping("detail.bo")
    public String selectBoardDetail(int bno, Model model) {
        int result = boardService.increaseCount(bno);

        if(result > 0){
            Board b = boardService.selectBoard(bno);
            model.addAttribute("b", b);

            return "board/boardDetailView";
        } else {
            model.addAttribute("errorMsg", "게시글 조회 실패");
            return "common/errorPage";
        }

    }

    @GetMapping("updateForm.bo")
    public String updateBoard(@RequestParam(value = "bno") int boardNo, Model model) {

        model.addAttribute("b", boardService.selectBoard(boardNo));
        return "board/boardUpdateForm";
    }

    @PostMapping("update.bo")
    public String updateBoard(@ModelAttribute Board b, MultipartFile reupfile, HttpSession session, Model model) {
        //새로운 첨부파일 있다면 저장 후 b객체에 파일명 수정
        //b객체 전달받은 값으로 수정

        //새로운 첨부 파일이 있는가?
        if(!reupfile.getOriginalFilename().equals("")){
            //기존첨파일 삭제
            if(b.getChangeName() != null && !b.getChangeName().equals("")){
                new File(session.getServletContext().getRealPath(b.getChangeName())).delete();
            }

            String changeName = Template.saveFile(reupfile, session, "/resources/uploadfile/");
            b.setChangeName("/resources/uploadfile/" + changeName);
            b.setOriginName(reupfile.getOriginalFilename());
        }

        int result = boardService.updateBoard(b);
        if(result > 0){
            session.setAttribute("alertMsg", "게시글 수정 성공");
            return "redirect:/detail.bo?bno=" + b.getBoardNo();
        } else {
            model.addAttribute("errorMsg", "게시글 수정 실패");
            return "common/errorPage";
        }
    }
}