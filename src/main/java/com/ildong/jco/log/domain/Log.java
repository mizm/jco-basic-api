package com.ildong.jco.log.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

//@Entity
@Data
//@Table(name = "SAP_RFC_LOG")
public class Log {
    private int id;
    private String rfcName;
    private String userId;
    private Date startDtm;
    private String successYn;

}
