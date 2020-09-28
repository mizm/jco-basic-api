package com.ildong.jco.sap.ui;

import com.ildong.jco.sap.domain.SapInputDTO;
import com.ildong.jco.sap.infra.SapRfcServiceInterface;
import com.ildong.jco.sap.usecase.SapRfcCallUseCase;
import com.sap.conn.jco.JCoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
public class BoardRestController {

    final private SapRfcCallUseCase sapRfcCallUseCase;

    @Autowired
    public BoardRestController(SapRfcCallUseCase sapRfcCallUseCase) {
        this.sapRfcCallUseCase = sapRfcCallUseCase;
    }

    //mapping에는 풀 경로를 다 적는게 좋음
    @PostMapping(value = "/api/v1/sap/rfc")
        ResponseEntity sapRfcCall(@RequestBody SapInputDTO sapInputDTO) throws JCoException {
//        log.info("######{}",sapInputDTO.getImportFields().get("test") instanceof ArrayList);
        return new ResponseEntity<>(sapRfcCallUseCase.sapRfcCall(sapInputDTO), HttpStatus.OK);
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity badRequestExceptionHandler(BadRequestException e)
    {
        ErrorResponse errorResponse = new ErrorResponse("잘못된 요청", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
