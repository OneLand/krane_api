package com.gachaland.api.member.dto.model;

import com.gachaland.api.common.Enumerations;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 */
@Data
public class MemberHistoryDTO implements Serializable {

    private long memberId;

    private List<History> history;

    @Data
    public static class History {
        private Enumerations.MemberHistoryStatus status ;
        private String issueDate;
    }
}
