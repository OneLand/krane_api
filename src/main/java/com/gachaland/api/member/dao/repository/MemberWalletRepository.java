package com.gachaland.api.member.dao.repository;

import com.gachaland.api.member.dao.model.MemberWallet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 */
public interface MemberWalletRepository extends JpaRepository<MemberWallet, Long> {
}
