package com.coveiot.coveaccess.hrv;

import com.coveiot.coveaccess.hrv.model.HrvData;
import com.coveiot.coveaccess.model.server.HrvDataBean;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class HrvConvertionAdapter {
    public static HrvDataBean getHrvDataBeanFrom(HrvData hrvData) {
        HrvDataBean hrvDataBean = new HrvDataBean();
        hrvDataBean.setType("HRV");
        hrvDataBean.setBaseUnit("VARIANCE");
        hrvDataBean.setDate(hrvData.getDate());
        hrvDataBean.setAvgHrv(hrvData.getAvgHrv());
        if (hrvData.getTimeLog() != null) {
            HrvDataBean.TimeLogBean timeLogBean = new HrvDataBean.TimeLogBean();
            if (!CoveUtil.isEmpty(hrvData.getTimeLog().getLogs())) {
                ArrayList arrayList = new ArrayList();
                for (HrvData.TimeLogBean.LogsBean logsBean : hrvData.getTimeLog().getLogs()) {
                    HrvDataBean.TimeLogBean.LogsBean logsBean2 = new HrvDataBean.TimeLogBean.LogsBean();
                    logsBean2.setEndTime(logsBean.getEndTime());
                    logsBean2.setStartTime(logsBean.getStartTime());
                    logsBean2.setValue(logsBean.getValue());
                    logsBean2.setFatigueValues(logsBean.getFatigueValues());
                    logsBean2.setVascOcclValues(logsBean.getVascOcclValues());
                    logsBean2.setHrValues(logsBean.getHrValues());
                    logsBean2.setAvgHr(logsBean.getAvgHr());
                    logsBean2.setCoordinateValues(logsBean.getCoordinateValues());
                    logsBean2.setHrvMethod(logsBean.getHrvMethod().toString());
                    logsBean2.setRriValues(logsBean.getRriValues());
                    arrayList.add(logsBean2);
                }
                timeLogBean.setLogs(arrayList);
            }
            hrvDataBean.setTimeLog(timeLogBean);
        }
        return hrvDataBean;
    }

    public static HrvData getHrvDataFrom(HrvDataBean hrvDataBean) {
        HrvData hrvData = new HrvData();
        hrvData.setDate(hrvDataBean.getDate());
        hrvData.setAvgHrv(hrvDataBean.getAvgHrv());
        if (hrvDataBean.getTimeLog() != null) {
            HrvData.TimeLogBean timeLogBean = new HrvData.TimeLogBean();
            if (!CoveUtil.isEmpty(hrvDataBean.getTimeLog().getLogs())) {
                ArrayList arrayList = new ArrayList();
                for (HrvDataBean.TimeLogBean.LogsBean logsBean : hrvDataBean.getTimeLog().getLogs()) {
                    HrvData.TimeLogBean.LogsBean logsBean2 = new HrvData.TimeLogBean.LogsBean();
                    logsBean2.setEndTime(logsBean.getEndTime());
                    logsBean2.setStartTime(logsBean.getStartTime());
                    logsBean2.setValue(logsBean.getValue());
                    logsBean2.setFatigueValues(logsBean.getFatigueValues());
                    logsBean2.setVascOcclValues(logsBean.getVascOcclValues());
                    logsBean2.setHrValues(logsBean.getHrValues());
                    logsBean2.setAvgHr(logsBean.getAvgHr());
                    logsBean2.setCoordinateValues(logsBean.getCoordinateValues());
                    logsBean2.setHrvMethod(HrvData.TimeLogBean.LogsBean.HRVMethod.valueOf(logsBean.getHrvMethod()));
                    logsBean2.setRriValues(logsBean.getRriValues());
                    arrayList.add(logsBean2);
                }
                timeLogBean.setLogs(arrayList);
            }
            hrvData.setTimeLog(timeLogBean);
        }
        return hrvData;
    }
}
