package com.ildong.jco.sap.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
//@XmlRootElement(name = "row")
//@XmlAccessorType(XmlAccessType.FIELD)
public class SapInputDTO {
    private AccessInfo accessInfo;
    private String functionName;
    private Map<String,Object> importFields;
    private Map<String,Object> importChangings;
    private Map<String,Object> importTables;
//    private String statnNm;
//    private String subwayId;
//    private String subwayNm;
//    private double x;
//    private double y;
//    private int ord;

}

/*
호출 스택
{
    "accessInfo" : {
        "host" : "172.16.17.16",
        "systemNumber" : "00",
        "clientNumber" : "400",
        "userId" : "ZILD_SD6",
        "userPw" : "dbswlgh!12",
        "language" : "KO",
        "type" : "3"
    },
    "functionName" : "ZSD_SND_DELIVERY_DOC",
    "importFields" : {
         "I_WADAT" : "20200824",
         "I_BEROT" : "01",
         "I_LPRIO" : "24"
    }
}


{
    "accessInfo" : {
        "host" : "172.16.17.16",
        "systemNumber" : "00",
        "clientNumber" : "400",
        "userId" : "ZILD_SD6",
        "userPw" : "dbswlgh!12",
        "language" : "KO",
        "type" : "3"
    },
    "functionName" : "ZSD_RCV_DELIVERY_STATUS",
    "importTables" : {
        "IT_ITEM" : [
            {
                "VBELN" : "0080000228",
                "POSNR" : "000010",
                "STATUS" : "C"
            },
            {
                "VBELN" : "0080000229",
                "POSNR" : "000010",
                "STATUS" : "R"
            }
        ]
    }
}

 */