package com.gachaland.api.client.dao.repository;

import com.gachaland.api.client.dao.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
//    List<Banner> findByStartDtAfterAndEndDtBefore(Date nowDate);
//    List<Banner> findByStartDtAfterAndEndDtBefore(LocalDateTime nowDate, LocalDateTime nowDate2);
}
