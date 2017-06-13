package com.gachaland.api.client.service;

import com.gachaland.api.client.dto.AppInfo;
import com.gachaland.api.client.dao.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chris.j3 on 2017. 6. 13..
 */
@Service
public class AppInfoService {

    @Autowired
    private BannerRepository bannerRepository;

    public AppInfo getCurrentAppInfo() {
        AppInfo appInfo = new AppInfo();
        appInfo.setAvailable(true);
        appInfo.setCheckUpdate(false);
        appInfo.setRecentVersion(1);
        appInfo.setRequireMinVersion(1);

//        appInfo.setBanners(bannerRepository.findByStartDtAfterAndEndDtBefore(new Date()));
//        appInfo.setBanners(bannerRepository.findByStartDtAfterAndEndDtBefore(LocalDateTime.now(), LocalDateTime.now()));

        return appInfo;
    }


}
