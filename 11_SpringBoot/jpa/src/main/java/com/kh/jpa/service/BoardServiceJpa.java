package com.kh.jpa.service;

import com.kh.jpa.dto.BoardDto;
import com.kh.jpa.entity.Board;
import com.kh.jpa.entity.BoardTag;
import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Tag;
import com.kh.jpa.enums.CommonEnums;
import com.kh.jpa.repository.BoardJpaRepository;
import com.kh.jpa.repository.MemberJpaRepository;
import com.kh.jpa.repository.TagJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시글 관련 비즈니스 로직을 처리하는 서비스 클래스 - Spring Data JPA 버전
 * BoardJpaRepository(JpaRepository 상속)를 사용
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceJpa {

    private final BoardJpaRepository boardJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final TagJpaRepository tagJpaRepository;
    private final String UPLOAD_PATH = "C:\\dev_tool";

    /**
     * 게시글 목록 조회 (페이징)
     */
    public Page<BoardDto.Response> getBoardList(Pageable pageable) {
        // JpaRepository의 Method Query 사용 - 한 줄로 끝!
        Page<Board> page = boardJpaRepository.findByStatus(CommonEnums.Status.Y, pageable);

        return page.map(board -> BoardDto.Response.ofSimple(
                board.getBoardNo(),
                board.getBoardTitle(),
                board.getOriginName(),
                board.getCount(),
                board.getCreateDate(),
                board.getMember().getUserId()
        ));
    }

    /**
     * 게시글 상세 조회
     */
    public BoardDto.Response getBoardDetail(Long boardNo) {
        // JpaRepository.findById() 사용
        Board board = boardJpaRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        Member writer = board.getMember();
        List<String> tagNames = board.getBoardTags()
                .stream()
                .map(boardTag -> boardTag.getTag().getTagName())
                .toList();

        return BoardDto.Response.of(
                board.getBoardNo(),
                board.getBoardTitle(),
                board.getBoardContent(),
                board.getOriginName(),
                board.getChangeName(),
                board.getCount(),
                board.getCreateDate(),
                writer.getUserId(),
                writer.getUserName(),
                tagNames
        );
    }

    /**
     * 게시글 생성
     */
    @Transactional
    public Long createBoard(BoardDto.Create createBoard) throws IOException {
        // 작성자 찾기 - JpaRepository.findById()
        Member member = memberJpaRepository.findById(createBoard.getUser_id())
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        String changeName = null;
        String originName = null;

        // 파일 업로드 처리
        if (createBoard.getFile() != null && !createBoard.getFile().isEmpty()) {
            originName = createBoard.getFile().getOriginalFilename();
            changeName = UUID.randomUUID().toString() + "_" + originName;

            File uploadDir = new File(UPLOAD_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            createBoard.getFile().transferTo(new File(UPLOAD_PATH + changeName));
        }

        // Board 엔티티 생성
        Board board = createBoard.toEntity();
        board.changeMember(member);
        board.changeFile(originName, changeName);

        // 태그 처리
        if (createBoard.getTags() != null && !createBoard.getTags().isEmpty()) {
            for (String tagName : createBoard.getTags()) {
                // 태그 조회 또는 생성 - JpaRepository 사용
                Tag tag = tagJpaRepository.findByTagName(tagName)
                        .orElseGet(() -> tagJpaRepository.save(Tag.builder()
                                .tagName(tagName)
                                .build()));

                BoardTag boardTag = BoardTag.builder()
                        .tag(tag)
                        .build();

                boardTag.changeBoard(board);
            }
        }

        // 게시글 저장 - JpaRepository.save()
        return boardJpaRepository.save(board).getBoardNo();
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deleteBoard(Long boardNo) {
        // JpaRepository.findById()
        Board board = boardJpaRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        // 파일 삭제
        if (board.getChangeName() != null) {
            new File(UPLOAD_PATH + board.getChangeName()).delete();
        }

        // 게시글 삭제 - JpaRepository.delete()
        boardJpaRepository.delete(board);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public BoardDto.Response updateBoard(Long boardNo, BoardDto.Update boardUpdate) throws IOException {
        // JpaRepository.findById()
        Board board = boardJpaRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        String changeName = board.getChangeName();
        String originName = board.getOriginName();

        // 파일 업로드 처리
        if (boardUpdate.getFile() != null && !boardUpdate.getFile().isEmpty()) {
            originName = boardUpdate.getFile().getOriginalFilename();
            changeName = UUID.randomUUID().toString() + "_" + originName;

            File uploadDir = new File(UPLOAD_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            boardUpdate.getFile().transferTo(new File(UPLOAD_PATH + changeName));
        }

        // 게시글 내용 수정
        board.changeContent(boardUpdate.getBoard_content());
        board.changeTitle(boardUpdate.getBoard_title());
        board.changeFile(originName, changeName);

        // 태그 처리
        if (boardUpdate.getTags() != null && !boardUpdate.getTags().isEmpty()) {
            // 기존 태그 제거
            board.getBoardTags().clear();

            // 새로운 태그 추가
            for (String tagName : boardUpdate.getTags()) {
                // 태그 조회 또는 생성 - JpaRepository 사용
                Tag tag = tagJpaRepository.findByTagName(tagName)
                        .orElseGet(() -> tagJpaRepository.save(Tag.builder()
                                .tagName(tagName)
                                .build()));

                BoardTag boardTag = BoardTag.builder()
                        .tag(tag)
                        .build();

                boardTag.changeBoard(board);
            }
        }

        // Response DTO 생성
        Member writer = board.getMember();
        List<String> tagNames = board.getBoardTags()
                .stream()
                .map(boardTag -> boardTag.getTag().getTagName())
                .toList();

        return BoardDto.Response.of(
                board.getBoardNo(),
                board.getBoardTitle(),
                board.getBoardContent(),
                board.getOriginName(),
                board.getChangeName(),
                board.getCount(),
                board.getCreateDate(),
                writer.getUserId(),
                writer.getUserName(),
                tagNames
        );
    }
}
