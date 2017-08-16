package com.gachaland.api.client.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "LandBanner")
public class Banner { // implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    // 배너 URL
    private String bannerUrl;

    // 설명
    private String description;

    // 배너 노출 시작 시간
    private Date startDt;

    // 배너 노출 종료 시간
    private Date endDt;

}
