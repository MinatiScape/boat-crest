package com.coveiot.coveaccess.spo2;

import com.coveiot.coveaccess.model.server.Spo2DataBean;
import com.coveiot.coveaccess.spo2.Spo2Data;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class Spo2Utils {
    public static Spo2DataBean getServerSpo2DataBeanfrom(Spo2Data spo2Data) {
        Spo2DataBean spo2DataBean = new Spo2DataBean();
        spo2DataBean.setBaseUnit("PERCENTAGE");
        spo2DataBean.setType("SPO2");
        spo2DataBean.setDate(spo2Data.getDate());
        spo2DataBean.setMax(Integer.valueOf(spo2Data.getMax()));
        spo2DataBean.setMin(Integer.valueOf(spo2Data.getMin()));
        spo2DataBean.setAvg(spo2Data.getAvg());
        if (spo2Data.getTimeLog() != null && !CoveUtil.isEmpty(spo2Data.getTimeLog().getLogs())) {
            Spo2DataBean.TimeLog timeLog = new Spo2DataBean.TimeLog();
            ArrayList arrayList = new ArrayList();
            for (Spo2Data.TimeLog.Log log : spo2Data.getTimeLog().getLogs()) {
                Spo2DataBean.TimeLog.Log log2 = new Spo2DataBean.TimeLog.Log();
                log2.setStartTime(log.getStartTime());
                log2.setEndTime(log.getEndTime());
                log2.setMax(Integer.valueOf(log.getMax()));
                log2.setMin(Integer.valueOf(log.getMin()));
                log2.setAvg(log.getAvg());
                log2.setCodedValues(log.getCodedValues());
                arrayList.add(log2);
            }
            timeLog.setLogs(arrayList);
            spo2DataBean.setTimeLog(timeLog);
        }
        return spo2DataBean;
    }

    public static Spo2Data getServerSpo2Datafrom(Spo2DataBean spo2DataBean) {
        Spo2Data spo2Data = new Spo2Data();
        spo2Data.setBaseUnit("PERCENTAGE");
        spo2Data.setType("SPO2");
        spo2Data.setDate(spo2DataBean.getDate());
        spo2Data.setMax(spo2DataBean.getMax().intValue());
        spo2Data.setMin(spo2DataBean.getMin().intValue());
        spo2Data.setAvg(spo2DataBean.getAvg());
        if (spo2DataBean.getTimeLog() != null && !CoveUtil.isEmpty(spo2DataBean.getTimeLog().getLogs())) {
            Spo2Data.TimeLog timeLog = new Spo2Data.TimeLog();
            ArrayList arrayList = new ArrayList();
            for (Spo2DataBean.TimeLog.Log log : spo2DataBean.getTimeLog().getLogs()) {
                Spo2Data.TimeLog.Log log2 = new Spo2Data.TimeLog.Log();
                log2.setStartTime(log.getStartTime());
                log2.setEndTime(log.getEndTime());
                log2.setMax(log.getMax().intValue());
                log2.setMin(log.getMin().intValue());
                log2.setAvg(log.getAvg());
                log2.setCodedValues(log.getCodedValues());
                arrayList.add(log2);
            }
            timeLog.setLogs(arrayList);
            spo2Data.setTimeLog(timeLog);
        }
        return spo2Data;
    }
}
