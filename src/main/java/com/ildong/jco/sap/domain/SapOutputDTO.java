package com.ildong.jco.sap.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter
//@XmlRootElement(name = "row")
//@XmlAccessorType(XmlAccessType.FIELD)
public class SapOutputDTO {
    private Map<String,Object> ExportFields;
    private Map<String,Object> ExportTables;
    private Map<String,Object> ExportChangings;
//    private String statnNm;
//    private String subwayId;
//    private String subwayNm;
//    private double x;
//    private double y;
//    private int ord;

}
