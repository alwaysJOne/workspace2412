package com.kh.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
