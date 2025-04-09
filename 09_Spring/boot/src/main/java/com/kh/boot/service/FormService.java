package com.kh.boot.service;

import com.kh.boot.domain.vo.Form;
import java.util.ArrayList;
import org.springframework.stereotype.Service;


public interface FormService {
    ArrayList<Form> selectFormList();
    int insertForm(Form form);
}
