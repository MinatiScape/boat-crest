package com.coveiot.coveaccess.heartrate;

import com.coveiot.coveaccess.heartrate.HeartRateData;
import com.coveiot.coveaccess.model.server.HeartRateDataBean;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class HeartRateUtils {
    public static HeartRateDataBean getServerHeartDataBeanfrom(HeartRateData heartRateData) {
        HeartRateDataBean heartRateDataBean = new HeartRateDataBean();
        heartRateDataBean.setBaseUnit("BPM");
        heartRateDataBean.setType(EcgStyleReportUtil.UserInfoKey.HR);
        heartRateDataBean.setDate(heartRateData.getDate());
        heartRateDataBean.setMax(heartRateData.getMax());
        heartRateDataBean.setMin(heartRateData.getMin());
        heartRateDataBean.setRest(heartRateData.getRest());
        if (heartRateData.getTimeLog() != null && !CoveUtil.isEmpty(heartRateData.getTimeLog().getLogs())) {
            HeartRateDataBean.TimeLogBean timeLogBean = new HeartRateDataBean.TimeLogBean();
            ArrayList arrayList = new ArrayList();
            for (HeartRateData.TimeLogBean.LogsBean logsBean : heartRateData.getTimeLog().getLogs()) {
                HeartRateDataBean.TimeLogBean.LogsBean logsBean2 = new HeartRateDataBean.TimeLogBean.LogsBean();
                logsBean2.setStartTime(logsBean.getStartTime());
                logsBean2.setEndTime(logsBean.getEndTime());
                logsBean2.setMax(logsBean.getMax());
                logsBean2.setMin(logsBean.getMin());
                logsBean2.setCodedValues(logsBean.getCodedValues());
                arrayList.add(logsBean2);
            }
            timeLogBean.setLogs(arrayList);
            heartRateDataBean.setTimeLog(timeLogBean);
        }
        return heartRateDataBean;
    }

    public static HeartRateData getServerHeartDataBeanfrom(HeartRateDataBean heartRateDataBean) {
        HeartRateData heartRateData = new HeartRateData();
        heartRateData.setBaseUnit("BPM");
        heartRateData.setType(EcgStyleReportUtil.UserInfoKey.HR);
        heartRateData.setDate(heartRateDataBean.getDate());
        heartRateData.setMax(heartRateDataBean.getMax());
        heartRateData.setMin(heartRateDataBean.getMin());
        heartRateData.setRest(heartRateDataBean.getRest());
        if (heartRateDataBean.getTimeLog() != null && !CoveUtil.isEmpty(heartRateDataBean.getTimeLog().getLogs())) {
            HeartRateData.TimeLogBean timeLogBean = new HeartRateData.TimeLogBean();
            ArrayList arrayList = new ArrayList();
            for (HeartRateDataBean.TimeLogBean.LogsBean logsBean : heartRateDataBean.getTimeLog().getLogs()) {
                HeartRateData.TimeLogBean.LogsBean logsBean2 = new HeartRateData.TimeLogBean.LogsBean();
                logsBean2.setStartTime(logsBean.getStartTime());
                logsBean2.setEndTime(logsBean.getEndTime());
                logsBean2.setMax(logsBean.getMax());
                logsBean2.setMin(logsBean.getMin());
                logsBean2.setCodedValues(logsBean.getCodedValues());
                arrayList.add(logsBean2);
            }
            timeLogBean.setLogs(arrayList);
            heartRateData.setTimeLog(timeLogBean);
        }
        return heartRateData;
    }
}
