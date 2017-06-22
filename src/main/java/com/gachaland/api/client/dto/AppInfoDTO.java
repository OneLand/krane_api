package com.gachaland.api.client.dto;

import com.gachaland.api.client.dao.model.Banner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chris.j3 on 2017. 6. 13..
 */
@Data
@NoArgsConstructor @AllArgsConstructor
public class AppInfoDTO implements Serializable {

    // 서비스 활성 여부
    private boolean available;

    // 업데이트 여부 (버전 체크 필요)
    private boolean checkUpdate;

    // 현재 알고있는 최신 버전
    private long recentVersion;

    // 지원하는 최소 버전
    private long requireMinVersion;

    private List<Banner> banners;

}
