package com.ildong.jco.sap.ui;

import com.ildong.jco.log.ui.LogClient;
import com.ildong.jco.sap.domain.SapInputDTO;
import com.ildong.jco.sap.usecase.SapRfcCallUseCase;
import com.sap.conn.jco.JCoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class SapRestController {

    final private SapRfcCallUseCase sapRfcCallUseCase;
//    final private LogClient logClient;

    @Autowired
    public SapRestController(SapRfcCallUseCase sapRfcCallUseCase) {
        this.sapRfcCallUseCase = sapRfcCallUseCase;
//        this.logClient = logClient;
    }

    //mapping에는 풀 경로를 다 적는게 좋음
    @PostMapping(value = "/api/v1/sap/rfc")
    ResponseEntity sapRfcCall(@RequestBody SapInputDTO sapInputDTO) throws JCoException {
//        log.info("######{}",sapInputDTO.getImportFields().get("test") instanceof ArrayList);
        ResponseEntity res = new ResponseEntity<>(sapRfcCallUseCase.sapRfcCall(sapInputDTO), HttpStatus.OK);
        // 구현방식 공부해볼것.
//        logClient.insertLog(sapInputDTO.getFunctionName(), sapInputDTO.getAccessInfo().getUserId());
        return res;
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity badRequestExceptionHandler(BadRequestException e)
    {
        ErrorResponse errorResponse = new ErrorResponse("잘못된 요청", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
