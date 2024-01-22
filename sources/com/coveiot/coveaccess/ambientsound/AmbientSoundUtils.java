package com.coveiot.coveaccess.ambientsound;

import com.coveiot.coveaccess.ambientsound.AmbientSoundData;
import com.coveiot.coveaccess.model.server.AmbientSoundDataBean;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class AmbientSoundUtils {
    public static AmbientSoundDataBean getServerAmbientSoundBeanfrom(AmbientSoundData ambientSoundData) {
        AmbientSoundDataBean ambientSoundDataBean = new AmbientSoundDataBean();
        ambientSoundDataBean.setBaseUnit("DECIBEL");
        ambientSoundDataBean.setType("AMBIENT_SOUND");
        ambientSoundDataBean.setDate(ambientSoundData.getDate());
        ambientSoundDataBean.setMax(ambientSoundData.getMax());
        ambientSoundDataBean.setMin(ambientSoundData.getMin());
        ambientSoundDataBean.setAvg(ambientSoundData.getAvg());
        ambientSoundDataBean.setTotalDuration(ambientSoundData.getTotalDuration());
        ambientSoundDataBean.setTzOffset(ambientSoundData.getTzOffset());
        if (ambientSoundData.getTimeLog() != null && !CoveUtil.isEmpty(ambientSoundData.getTimeLog().getLogs())) {
            AmbientSoundDataBean.TimeLogBean timeLogBean = new AmbientSoundDataBean.TimeLogBean();
            ArrayList arrayList = new ArrayList();
            for (AmbientSoundData.TimeLogBean.LogsBean logsBean : ambientSoundData.getTimeLog().getLogs()) {
                AmbientSoundDataBean.TimeLogBean.LogsBean logsBean2 = new AmbientSoundDataBean.TimeLogBean.LogsBean();
                logsBean2.setStartTime(logsBean.getStartTime());
                logsBean2.setEndTime(logsBean.getEndTime());
                logsBean2.setMax(logsBean.getMax());
                logsBean2.setMin(logsBean.getMin());
                logsBean2.setAvg(logsBean.getAvg());
                logsBean2.setCodedValues(logsBean.getCodedValues());
                arrayList.add(logsBean2);
            }
            timeLogBean.setLogs(arrayList);
            ambientSoundDataBean.setTimeLog(timeLogBean);
        }
        if (ambientSoundData.getBaseUnits() != null) {
            AmbientSoundDataBean.BaseUnits baseUnits = new AmbientSoundDataBean.BaseUnits();
            baseUnits.setTotalDuration("MINUTES");
            ambientSoundDataBean.setBaseUnits(baseUnits);
        }
        return ambientSoundDataBean;
    }

    public static AmbientSoundData getServerAmbientSoundBeanfrom(AmbientSoundDataBean ambientSoundDataBean) {
        AmbientSoundData ambientSoundData = new AmbientSoundData();
        ambientSoundData.setBaseUnit("DECIBEL");
        ambientSoundData.setType("AMBIENT_SOUND");
        ambientSoundData.setDate(ambientSoundDataBean.getDate());
        ambientSoundData.setMax(ambientSoundDataBean.getMax());
        ambientSoundData.setMin(ambientSoundDataBean.getMin());
        ambientSoundData.setAvg(ambientSoundDataBean.getAvg());
        ambientSoundData.setTotalDuration(ambientSoundDataBean.getTotalDuration());
        ambientSoundData.setTzOffset(ambientSoundDataBean.getTzOffset());
        if (ambientSoundDataBean.getTimeLog() != null && !CoveUtil.isEmpty(ambientSoundDataBean.getTimeLog().getLogs())) {
            AmbientSoundData.TimeLogBean timeLogBean = new AmbientSoundData.TimeLogBean();
            ArrayList arrayList = new ArrayList();
            for (AmbientSoundDataBean.TimeLogBean.LogsBean logsBean : ambientSoundDataBean.getTimeLog().getLogs()) {
                AmbientSoundData.TimeLogBean.LogsBean logsBean2 = new AmbientSoundData.TimeLogBean.LogsBean();
                logsBean2.setStartTime(logsBean.getStartTime());
                logsBean2.setEndTime(logsBean.getEndTime());
                logsBean2.setMax(logsBean.getMax());
                logsBean2.setMin(logsBean.getMin());
                logsBean2.setAvg(logsBean.getAvg());
                logsBean2.setCodedValues(logsBean.getCodedValues());
                arrayList.add(logsBean2);
            }
            timeLogBean.setLogs(arrayList);
            ambientSoundData.setTimeLog(timeLogBean);
        }
        if (ambientSoundDataBean.getBaseUnits() != null) {
            AmbientSoundData.BaseUnits baseUnits = new AmbientSoundData.BaseUnits();
            baseUnits.setTotalDuration("MINUTES");
            ambientSoundData.setBaseUnits(baseUnits);
        }
        return ambientSoundData;
    }
}
