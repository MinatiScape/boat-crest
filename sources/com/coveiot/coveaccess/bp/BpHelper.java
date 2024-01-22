package com.coveiot.coveaccess.bp;

import com.coveiot.coveaccess.bp.model.BpData;
import com.coveiot.coveaccess.model.server.BpDataBean;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class BpHelper {
    public static BpDataBean getBpDataBeanFrom(BpData bpData) {
        BpDataBean bpDataBean = new BpDataBean();
        bpDataBean.setType(EcgStyleReportUtil.UserInfoKey.BP);
        bpDataBean.setBaseUnit("MMHG");
        bpDataBean.setDate(bpData.getDate());
        bpDataBean.setAvgDiastolic(bpData.getAvgDiastolic());
        bpDataBean.setAvgSystolic(bpData.getAvgSystolic());
        ArrayList arrayList = new ArrayList();
        if (bpData.getTimeLog() != null) {
            for (BpData.TimeLogBean.LogsBean logsBean : bpData.getTimeLog().getLogs()) {
                BpDataBean.TimeLogBean.LogsBean logsBean2 = new BpDataBean.TimeLogBean.LogsBean();
                logsBean2.setStartTime(logsBean.getStartTime());
                logsBean2.setEndTime(logsBean.getEndTime());
                logsBean2.setCodedValues(logsBean.getCodedValues());
                if (!CoveUtil.isEmpty(logsBean.getCoordinateValues())) {
                    logsBean2.setCoordinateValues(logsBean.getCoordinateValues());
                }
                if (!CoveUtil.isEmpty(logsBean.getHrValues())) {
                    logsBean2.setHrValues(logsBean.getHrValues());
                }
                arrayList.add(logsBean2);
            }
        }
        BpDataBean.TimeLogBean timeLogBean = new BpDataBean.TimeLogBean();
        timeLogBean.setLogs(arrayList);
        bpDataBean.setTimeLog(timeLogBean);
        return bpDataBean;
    }

    public static BpData getBpDataFrom(BpDataBean bpDataBean) {
        BpData bpData = new BpData();
        bpData.setDate(bpDataBean.getDate());
        bpData.setAvgDiastolic(bpDataBean.getAvgDiastolic());
        bpData.setAvgSystolic(bpDataBean.getAvgSystolic());
        ArrayList arrayList = new ArrayList();
        if (bpDataBean.getTimeLog() != null) {
            for (BpDataBean.TimeLogBean.LogsBean logsBean : bpDataBean.getTimeLog().getLogs()) {
                BpData.TimeLogBean.LogsBean logsBean2 = new BpData.TimeLogBean.LogsBean();
                logsBean2.setStartTime(logsBean.getStartTime());
                logsBean2.setEndTime(logsBean.getEndTime());
                logsBean2.setCodedValues(logsBean.getCodedValues());
                if (!CoveUtil.isEmpty(logsBean.getCoordinateValues())) {
                    logsBean2.setCoordinateValues(logsBean.getCoordinateValues());
                }
                if (!CoveUtil.isEmpty(logsBean.getHrValues())) {
                    logsBean2.setHrValues(logsBean.getHrValues());
                }
                arrayList.add(logsBean2);
            }
        }
        BpData.TimeLogBean timeLogBean = new BpData.TimeLogBean();
        timeLogBean.setLogs(arrayList);
        bpData.setTimeLog(timeLogBean);
        return bpData;
    }
}
