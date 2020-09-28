package com.ildong.jco.sap.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubwayInputDTO {
    int resultCnt;
    double x;
    double y;

    /* 여기에 살짝 범위를 추가해줌 0.005
    2km 범위를 탐색하는 것이 목적이라면, 좌표값으로 대략 범위 +- 0.022 lng x
    위에는 lat,lng 에서 적용 되는 방식(wgs84)
    강남역과 선릉역을 기준으로 가로 2km정도 거리
    강남 x : 202477.1229 y : 444291.4383
    선릉 x : 204329.4182 y : 445018.7002
    x 대략 1500 y 대략 800 이므로
    삼성역 대모산입구역 세로 2km정도 거리
        205577.1523 445501.402
        206436.8025 443563.9025
    x 대략 1000 y 대략 2000
    총 대략적으로 +- 2000정도의 거리로 범위로 리스트 만들어 온 후에 다시 거리계산 할 예정.
    -- 변경 // UTM-K 좌표계는 1이 M단위
    그냥 정리하면 끝인부분이였습니다.

    20200915
    검색반경 - 1 km 로 변경
     */

    public double getMinX(){
        return this.x - 1000;
    }
    public double getMaxX(){
        return this.x + 1000;
    }
    public double getMinY(){
        return this.y - 1000;
    }
    public double getMaxY(){
        return this.y + 1000;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
}
