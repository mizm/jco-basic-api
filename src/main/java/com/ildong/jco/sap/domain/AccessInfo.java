package com.ildong.jco.sap.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter
//@XmlRootElement(name = "row")
//@XmlAccessorType(XmlAccessType.FIELD)
public class AccessInfo {
    private String host;
    private String systemNumber;
    private String clientNumber;
    private String userId;
    private String userPw;
    private String language;
    private String type;

//    private String statnNm;
//    private String subwayId;
//    private String subwayNm;
//    private double x;
//    private double y;
//    private int ord;

}
