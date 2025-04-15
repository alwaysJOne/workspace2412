package com.kh.boot.service;

import com.kh.boot.domain.vo.Form;
import com.kh.boot.mappers.FormMapper;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormServiceImpl implements FormService {

    private final FormMapper formMapper;

    @Override
    public int insertForm(Form form) {
        return formMapper.insertForm(form);
    }

    @Override
    public ArrayList<Form> selectFormList() {
        return formMapper.selectFormList();
    }
}
