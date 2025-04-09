package com.kh.boot.mappers;

import com.kh.boot.domain.vo.Form;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FormMapper {
    ArrayList<Form> selectFormList();
    int insertForm(Form form);
}
