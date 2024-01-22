package com.coveiot.repository.spo2.datasources.server;

import com.coveiot.coveaccess.fitness.model.SPO2FitnessRecordData;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordType;
import com.coveiot.coveaccess.model.server.SSaveFitnessRecordReq;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/* loaded from: classes9.dex */
public class SPO2Formator {
    public static SSaveFitnessRecordReq getSPO2ServerModels(List<EntityManualData> list) {
        SSaveFitnessRecordReq sSaveFitnessRecordReq = new SSaveFitnessRecordReq();
        ArrayList arrayList = new ArrayList();
        if (!AppUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                EntityManualData entityManualData = list.get(i);
                SPO2FitnessRecordData sPO2FitnessRecordData = new SPO2FitnessRecordData();
                sPO2FitnessRecordData.setBaseUnit(entityManualData.isLevelInterpretation() ? "ORDINAL" : "PERCENTAGE");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(entityManualData.getTimeStamp());
                sPO2FitnessRecordData.setDate(AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"));
                sPO2FitnessRecordData.setTime(AppUtils.formatDate(calendar.getTime(), "HH:mm:ss"));
                double spo2 = entityManualData.getSpo2();
                sPO2FitnessRecordData.setValue(String.valueOf(Double.parseDouble(entityManualData.isLevelInterpretation() ? String.valueOf((int) spo2) : String.valueOf(spo2))));
                sPO2FitnessRecordData.setType(FitnessRecordType.SPO2.name());
                arrayList.add(sPO2FitnessRecordData);
            }
            sSaveFitnessRecordReq.setItems(arrayList);
        }
        return sSaveFitnessRecordReq;
    }
}
