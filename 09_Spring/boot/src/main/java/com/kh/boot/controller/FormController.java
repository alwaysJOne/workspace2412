package com.kh.boot.controller;

import com.kh.boot.domain.vo.Form;

import com.kh.boot.service.FormService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class FormController {

    private final FormService formService;

    @GetMapping("/list.fo")
    public String selectFormList(Model model) {
        ArrayList<Form> list = formService.selectFormList();

        model.addAttribute("list", list);
        return "form/formListView";
    }

    @GetMapping("/enrollForm.fo")
    public String selectEnrollForm(HttpSession session) {
        if(session.getAttribute("googleAccessToken") == null) {
            session.setAttribute("alertMsg", "google로그인 계정만 작성 가능합니다.");
            return "redirect:/list.fo";
        }
        return "form/formEnrollView";
    }

    @PostMapping("insert.fo")
    public String insertForm(Form form, HttpSession session, Model model) {
        int result = formService.insertForm(form);
        if(result > 0){
            session.setAttribute("alertMsg", "설문지 등록 성공");
            return "redirect:/list.fo";
        } else {
            model.addAttribute("errorMsg", "설문지등록 실패");
            return "common/errorPage";
        }
    }

}
