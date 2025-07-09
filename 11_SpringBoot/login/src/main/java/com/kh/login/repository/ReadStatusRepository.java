package com.kh.login.repository;

import com.kh.login.domain.ReadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadStatusRepository extends JpaRepository<ReadStatus, Long> {
}
