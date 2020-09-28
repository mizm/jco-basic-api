package com.ildong.jco.sap.infra;

import com.ildong.jco.sap.domain.AccessInfo;
import com.ildong.jco.sap.domain.SapInputDTO;
import com.ildong.jco.sap.domain.SapOutputDTO;
import com.ildong.jco.sap.infra.util.InMemoryDestinationDataProvider;
import com.sap.conn.jco.*;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service("SapRfcServiceInterface")
public class SapRfcServiceInterfaceImpl implements SapRfcServiceInterface {

    private InMemoryDestinationDataProvider memoryProvider = new InMemoryDestinationDataProvider();

    @Override
    public SapOutputDTO sapRfcCall(SapInputDTO input) throws JCoException {
        JCoDestination dest = getSapConnection(input.getAccessInfo());

        if (dest == null)
            throw new RuntimeException("JCoDestination construct fail.");
        JCoFunctionTemplate ftemplate = dest.getRepository().getFunctionTemplate(input.getFunctionName());
        JCoFunction jcoFunction = ftemplate.getFunction();

        if (jcoFunction == null)
            throw new RuntimeException("ZSD_MAKE_SO_IF not found in SAP.");


        // import parameter 넣기
        if(input.getImportFields() != null) {
            JCoParameterList importParameterList = jcoFunction.getImportParameterList();
            mapToJco(importParameterList, input.getImportFields());
        }
        // import Chaninging
        if(input.getImportChangings() != null) {
            JCoParameterList importChangingList = jcoFunction.getChangingParameterList();
            mapToJco(importChangingList, input.getImportChangings());
        }
        // import table
        if(input.getImportTables() != null) {
            log.info("#################################################################");
            JCoParameterList importTableList = jcoFunction.getTableParameterList();
            mapToJco(importTableList, input.getImportTables());
        }
        // excute
        jcoFunction.execute(dest);

        SapOutputDTO res = new SapOutputDTO();
        res.setExportTables(mapToMap(jcoFunction.getTableParameterList()));
        res.setExportFields(mapToMap(jcoFunction.getExportParameterList()));
        res.setExportChangings(mapToMap(jcoFunction.getChangingParameterList()));
        return res;
    }

    private void mapToJco(final JCoRecord record, Map<String,Object> items) {
        for(String fieldName : items.keySet()) {
            final Object value = items.get(fieldName);
            // hash - structure
            if(value instanceof HashMap ){
                final JCoStructure structure = record.getStructure(fieldName);
                mapToJco(structure, (Map<String, Object>) value);
            } else if(value instanceof ArrayList) {
                log.info("###{}",value);
                //list - table
                final JCoTable table = record.getTable(fieldName);
                table.clear();
                for (Map<String, Object> structureMap : (List<Map<String, Object>>) value) {
                    table.appendRow();
                    mapToJco(table, structureMap);
                }
            }
            else {
                //string - field
                record.setValue(fieldName, value);
            }
        }
    }
    private Map<String, Object> mapToMap(final JCoRecord record) {
        final Map<String, Object> map = new HashMap<>();
        if (record == null) {
            return map;
        }

        final JCoFieldIterator iter = record.getFieldIterator();

        while (iter.hasNextField()) {
            final JCoField jcoField = iter.nextField();

            final String sapFieldName = jcoField.getName();

            if (jcoField.isStructure()) {
                map.put(sapFieldName, mapToMap(jcoField.getStructure()));
            } else if (jcoField.isTable()) {
                final List<Map<String, Object>> list = new ArrayList<>();
                final JCoTable table = jcoField.getTable();
                for (int j = 0; j < table.getNumRows(); j++) {
                    table.setRow(j);
                    list.add(mapToMap(table));
                }
                map.put(sapFieldName, list);
            } else {
                final Object value = jcoField.getValue();
                map.put(sapFieldName, value);
            }
        }
        return map;
    }

    private JCoDestination getSapConnection(AccessInfo accessInfo) {

        try {
            if(!Environment.isDestinationDataProviderRegistered())
                Environment.registerDestinationDataProvider(memoryProvider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JCoDestination res = null;
        Properties prop = new Properties();
        prop.setProperty(DestinationDataProvider.JCO_ASHOST, accessInfo.getHost());
        prop.setProperty(DestinationDataProvider.JCO_SYSNR, accessInfo.getSystemNumber());
        prop.setProperty(DestinationDataProvider.JCO_CLIENT, accessInfo.getClientNumber());
        prop.setProperty(DestinationDataProvider.JCO_USER, accessInfo.getUserId());
        prop.setProperty(DestinationDataProvider.JCO_PASSWD, accessInfo.getUserPw());
        prop.setProperty(DestinationDataProvider.JCO_LANG, accessInfo.getLanguage());
        prop.setProperty(DestinationDataProvider.JCO_TYPE, accessInfo.getType());
        memoryProvider.changeProperties(accessInfo.getUserId(), prop);
        try {
            res =  JCoDestinationManager.getDestination(accessInfo.getUserId());
            log.info("Destination {} works", accessInfo.getUserId());
        } catch (JCoException e) {
            e.printStackTrace();
        }
        return res;
    }

}
