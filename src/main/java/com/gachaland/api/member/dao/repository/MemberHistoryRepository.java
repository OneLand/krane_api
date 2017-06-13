package com.gachaland.api.member.dao.repository;

import com.gachaland.api.member.dao.model.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chris.j3 on 2017. 6. 13..
 */
public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {

}
