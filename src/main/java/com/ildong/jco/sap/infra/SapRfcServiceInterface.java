package com.ildong.jco.sap.infra;

import com.ildong.jco.sap.domain.SapInputDTO;
import com.ildong.jco.sap.domain.SapOutputDTO;
import com.sap.conn.jco.JCoException;

import java.util.List;
import java.util.Map;

public interface SapRfcServiceInterface {

    SapOutputDTO sapRfcCall(SapInputDTO input) throws JCoException;

}
