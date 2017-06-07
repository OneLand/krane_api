package com.gachaland.api.member.dao.repository;

import com.gachaland.api.member.dao.model.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chris.j3 on 2017. 6. 7..
 */
public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {
}
