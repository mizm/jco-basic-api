package com.ildong.jco.log.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
public class LogDTO {
    private String rfcName;
    private String userId;
    private Date startDtm;
    private String successYn;

    public LogDTO(String rfcName, String userId) {
        this.rfcName = rfcName;
        this.userId = userId;
        this.startDtm = new Date();
        this.successYn = "Y";
    }
}
