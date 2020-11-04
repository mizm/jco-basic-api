package com.ildong.jco.log.ui;

import com.ildong.jco.log.domain.LogDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Slf4j
public class LogClient {
    //final private LogRepository logRepository;

    public LogClient() {
        //LogRepository logRepository
        //this.logRepository = logRepository;
    }
    private void insertLog(LogDTO logDTO) {
        log.debug("##{}##", logDTO);
    }
    public void insertLog(String rfcName, String userId) {
        LogDTO logDTO = new LogDTO(rfcName, userId);
        insertLog(logDTO);
    }

}
