package com.gachaland.api.client.dao.repository;

import com.gachaland.api.client.dao.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chris.j3 on 2017. 6. 13..
 */
public interface BannerRepository extends JpaRepository<Banner, Long> {
//    List<Banner> findByStartDtAfterAndEndDtBefore(Date nowDate);
//    List<Banner> findByStartDtAfterAndEndDtBefore(LocalDateTime nowDate, LocalDateTime nowDate2);
}
