package com.ildong.jco.sap.usecase;

import com.ildong.jco.sap.domain.*;
import com.ildong.jco.sap.infra.SapRfcServiceInterface;
import com.sap.conn.jco.JCoException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class SapRfcCallUseCase {

    final private SapRfcServiceInterface sapRfcServiceInterface;
    @Autowired
    public SapRfcCallUseCase(SapRfcServiceInterface sapRfcServiceInterface) {
        this.sapRfcServiceInterface = sapRfcServiceInterface;
    }
    public SapOutputDTO sapRfcCall(SapInputDTO sapInputDTO) throws JCoException {
        return sapRfcServiceInterface.sapRfcCall(sapInputDTO);
    }
}
