package com.gachaland.api.member.dao.repository;

import com.gachaland.api.member.dao.model.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 */
public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
    List<MemberHistory> findByMemberId(long memberId);
}
