package com.gachaland.api.client.service;

import com.gachaland.api.client.dto.AppInfoDTO;
import com.gachaland.api.client.dao.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppInfoService {

    @Autowired
    private BannerRepository bannerRepository;

    public AppInfoDTO getCurrentAppInfo() {
        AppInfoDTO appInfoDTO = new AppInfoDTO();
        appInfoDTO.setAvailable(true);
        appInfoDTO.setCheckUpdate(false);
        appInfoDTO.setRecentVersion(1);
        appInfoDTO.setRequireMinVersion(1);

//        appInfoDTO.setBanners(bannerRepository.findByStartDtAfterAndEndDtBefore(LocalDateTime.now()));
//        appInfoDTO.setBanners(bannerRepository.findByStartDtAfterAndEndDtBefore(LocalDateTime.now(), LocalDateTime.now()));

        return appInfoDTO;
    }

}
