package com.kh.boot.controller;

import com.kh.boot.domain.vo.Form;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    @GetMapping("/list.fo")
    public String selectFormList() {
        return "form/formListView";
    }

    @GetMapping("/enrollForm.fo")
    public String selectEnrollForm() {

        return "form/formEnrollForm";
    }

    @PostMapping("/insert.fo")
    public String insertForm(Form form, String formId) {
        form.setFormResponseUrl("https://docs.google.com/forms/d/"+formId+"/viewform");
        form.setFormDashBoardUrl("https://docs.google.com/forms/d/"+formId+"/edit#responses");
    }
}
