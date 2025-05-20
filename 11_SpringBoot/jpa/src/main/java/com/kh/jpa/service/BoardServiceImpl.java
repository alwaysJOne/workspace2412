package com.kh.jpa.service;


import com.kh.jpa.entity.Board;
import com.kh.jpa.enums.CommonEnums;
import com.kh.jpa.repository.BoardRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRespository boardRespository;

    @Override
    public void getBoardList(Pageable pageable) {
        /*
            getContent() 실제 데이터 리스트 반환
            getTotalPages() 전체 페이지 개수
            getTotalelements() 전체 데이터 수
            getSize() 페이지당 데이터 수
            ...
         */
        Page<Board> page = boardRespository.findByStatus(CommonEnums.Status.Y, pageable);
       

    }
}
