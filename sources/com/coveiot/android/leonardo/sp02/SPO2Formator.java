package com.coveiot.android.leonardo.sp02;

import com.coveiot.android.leonardo.sp02.database.entities.SPO2Record;
import com.coveiot.coveaccess.fitness.model.SPO2FitnessRecordData;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordType;
import com.coveiot.coveaccess.model.server.SSaveFitnessRecordReq;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/* loaded from: classes5.dex */
public class SPO2Formator {
    public static SSaveFitnessRecordReq getSPO2ServerModels(List<SPO2Record> list) {
        SSaveFitnessRecordReq sSaveFitnessRecordReq = new SSaveFitnessRecordReq();
        ArrayList arrayList = new ArrayList();
        if (!AppUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                SPO2Record sPO2Record = list.get(i);
                SPO2FitnessRecordData sPO2FitnessRecordData = new SPO2FitnessRecordData();
                sPO2FitnessRecordData.setBaseUnit(sPO2Record.isLevelIntepreTation ? "ORDINAL" : "PERCENTAGE");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(sPO2Record.getTimeStamp());
                sPO2FitnessRecordData.setDate(AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"));
                sPO2FitnessRecordData.setTime(AppUtils.formatDate(calendar.getTime(), "HH:mm:ss"));
                double value = sPO2Record.getValue();
                sPO2FitnessRecordData.setValue(sPO2Record.isLevelIntepreTation ? String.valueOf((int) value) : String.valueOf(value));
                sPO2FitnessRecordData.setTzOffset(sPO2Record.getTimeZoneOffSet());
                sPO2FitnessRecordData.setType(FitnessRecordType.SPO2.name());
                arrayList.add(sPO2FitnessRecordData);
            }
            sSaveFitnessRecordReq.setItems(arrayList);
        }
        return sSaveFitnessRecordReq;
    }
}
