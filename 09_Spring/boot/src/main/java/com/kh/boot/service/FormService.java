package com.kh.boot.service;

import com.kh.boot.domain.vo.Form;
import java.util.ArrayList;

public interface FormService {
    int insertForm(Form form);
    ArrayList<Form> selectFormList();
}
