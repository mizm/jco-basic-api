package com.ildong.jco.sap.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "API_SUBWAY_POINT")
public class Subway{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String statnId;
    private String statnNm;
    private String subwayId;
    private String subwayNm;
    private double x;
    private double y;
    public double getDistance(double x1, double y1) {
        return Math.sqrt(Math.pow((x1-this.x),2) + Math.pow((y1-this.y),2));
    }

}
