package com.gachaland.api.member.dao.repository;


import com.gachaland.api.member.dao.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jhpark1220 on 2017. 6. 7..
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByPhoneNumber(String phoneNumber);
}
