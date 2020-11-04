package com.ildong.jco.sap.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class SapInputDTO {
    private AccessInfo accessInfo;
    private String functionName;
    private Map<String,Object> importFields;
    private Map<String,Object> importChangings;
    private Map<String,Object> importTables;
}

/*
호출 스택
{
    "accessInfo" : {
        "host" : "172.16.17.16",
        "systemNumber" : "00",
        "clientNumber" : "400",
        "userId" : "",
        "userPw" : "",
        "language" : "KO",
        "type" : "3"
    },
    "functionName" : "functionName",
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
        "userId" : "",
        "userPw" : "",
        "language" : "KO",
        "type" : "3"
    },
    "functionName" : "functionName",
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
