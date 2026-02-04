package com.kh.jpa.service;

import com.kh.jpa.dto.MemberDto;
import com.kh.jpa.entity.Member;
import com.kh.jpa.repository.MemberJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 관련 비즈니스 로직을 처리하는 서비스 클래스 - Spring Data JPA 버전
 * MemberJpaRepository(JpaRepository 상속)를 사용
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceJpa {

    private final MemberJpaRepository memberJpaRepository;

    /**
     * 회원 등록
     * @param createDto 회원 생성 정보
     * @return 생성된 회원 ID
     */
    public String createMember(MemberDto.Create createDto) {
        Member member = createDto.toEntity();
        memberJpaRepository.save(member); // JpaRepository.save()
        return member.getUserId();
    }

    /**
     * 회원 단건 조회
     * @param userId 조회할 회원 ID
     * @return 회원 정보 DTO
     */
    @Transactional(readOnly = true)
    public MemberDto.Response findMember(String userId) {
        return memberJpaRepository.findById(userId) // JpaRepository.findById()
                .map(member -> MemberDto.Response.of(
                        member.getUserId(),
                        member.getUserName(),
                        member.getEmail(),
                        member.getGender(),
                        member.getPhone(),
                        member.getAddress(),
                        member.getAge(),
                        member.getCreateDate(),
                        member.getModifyDate(),
                        member.getStatus()
                ))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    /**
     * 회원 정보 수정
     * @param userId 수정할 회원 ID
     * @param updateDto 수정할 회원 정보
     * @return 수정된 회원 정보 DTO
     */
    public MemberDto.Response updateMember(String userId, MemberDto.Update updateDto) {
        Member member = memberJpaRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.updateMemberInfo(
                updateDto.getUser_name(),
                updateDto.getEmail(),
                updateDto.getGender(),
                updateDto.getPhone(),
                updateDto.getAddress(),
                updateDto.getAge()
        );

        return MemberDto.Response.of(
                member.getUserId(),
                member.getUserName(),
                member.getEmail(),
                member.getGender(),
                member.getPhone(),
                member.getAddress(),
                member.getAge(),
                member.getCreateDate(),
                member.getModifyDate(),
                member.getStatus()
        );
    }

    /**
     * 회원 삭제
     * @param userId 삭제할 회원 ID
     */
    public void deleteMember(String userId) {
        Member member = memberJpaRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        memberJpaRepository.delete(member); // JpaRepository.delete()
    }

    /**
     * 전체 회원 조회
     * @return 전체 회원 리스트
     */
    @Transactional(readOnly = true)
    public List<MemberDto.Response> findAllMember() {
        return memberJpaRepository.findAll() // JpaRepository.findAll()
                .stream()
                .map(member -> MemberDto.Response.of(
                        member.getUserId(),
                        member.getUserName(),
                        member.getEmail(),
                        member.getGender(),
                        member.getPhone(),
                        member.getAddress(),
                        member.getAge(),
                        member.getCreateDate(),
                        member.getModifyDate(),
                        member.getStatus()
                ))
                .toList();
    }

    /**
     * 이름으로 회원 검색
     * @param name 검색할 이름
     * @return 검색된 회원 리스트
     */
    @Transactional(readOnly = true)
    public List<MemberDto.Response> findByName(String name) {
        return memberJpaRepository.findByUserNameContaining(name) // Method Query 사용
                .stream()
                .map(member -> MemberDto.Response.of(
                        member.getUserId(),
                        member.getUserName(),
                        member.getEmail(),
                        member.getGender(),
                        member.getPhone(),
                        member.getAddress(),
                        member.getAge(),
                        member.getCreateDate(),
                        member.getModifyDate(),
                        member.getStatus()
                ))
                .toList();
    }
}
