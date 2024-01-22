package com.coveiot.coveaccess.temperature;

import com.coveiot.android.watchfaceui.constants.WatchfaceConstants;
import com.coveiot.coveaccess.model.server.TemperatureDataBean;
import com.coveiot.coveaccess.temperature.model.TemperatureData;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class TemperatureUtils {
    public static TemperatureDataBean getServerTemperatureDataBeanfrom(TemperatureData temperatureData) {
        TemperatureDataBean temperatureDataBean = new TemperatureDataBean();
        temperatureDataBean.setBaseUnit(WatchfaceConstants.CELSIUS);
        temperatureDataBean.setType("BODY_TEMPERATURE");
        temperatureDataBean.setDate(temperatureData.getDate());
        temperatureDataBean.setMax(temperatureData.getMax());
        temperatureDataBean.setMin(temperatureData.getMin());
        temperatureDataBean.setAvg(temperatureData.getAvg());
        if (temperatureData.getTimeLog() != null && !CoveUtil.isEmpty(temperatureData.getTimeLog().getLogs())) {
            TemperatureDataBean.TimeLog timeLog = new TemperatureDataBean.TimeLog();
            ArrayList arrayList = new ArrayList();
            for (TemperatureData.TimeLog.Log log : temperatureData.getTimeLog().getLogs()) {
                TemperatureDataBean.TimeLog.Log log2 = new TemperatureDataBean.TimeLog.Log();
                log2.setStartTime(log.getStartTime());
                log2.setEndTime(log.getEndTime());
                log2.setMax(log.getMax());
                log2.setMin(log.getMin());
                log2.setAvg(log.getAvg());
                log2.setCodedValues(log.getCodedValues());
                log2.setSurfaceTempValues(log.getSurfaceTempValues());
                arrayList.add(log2);
            }
            timeLog.setLogs(arrayList);
            temperatureDataBean.setTimeLog(timeLog);
        }
        return temperatureDataBean;
    }

    public static TemperatureData getServerTemperatureDatafrom(TemperatureDataBean temperatureDataBean) {
        TemperatureData temperatureData = new TemperatureData();
        temperatureData.setBaseUnit(WatchfaceConstants.CELSIUS);
        temperatureData.setType("BODY_TEMPERATURE");
        temperatureData.setDate(temperatureDataBean.getDate());
        temperatureData.setMax(temperatureDataBean.getMax());
        temperatureData.setMin(temperatureDataBean.getMin());
        temperatureData.setAvg(temperatureDataBean.getAvg());
        if (temperatureDataBean.getTimeLog() != null && !CoveUtil.isEmpty(temperatureDataBean.getTimeLog().getLogs())) {
            TemperatureData.TimeLog timeLog = new TemperatureData.TimeLog();
            ArrayList arrayList = new ArrayList();
            for (TemperatureDataBean.TimeLog.Log log : temperatureDataBean.getTimeLog().getLogs()) {
                TemperatureData.TimeLog.Log log2 = new TemperatureData.TimeLog.Log();
                log2.setStartTime(log.getStartTime());
                log2.setEndTime(log.getEndTime());
                log2.setMax(log.getMax());
                log2.setMin(log.getMin());
                log2.setAvg(log.getAvg());
                log2.setCodedValues(log.getCodedValues());
                log2.setSurfaceTempValues(log.getSurfaceTempValues());
                arrayList.add(log2);
            }
            timeLog.setLogs(arrayList);
            temperatureData.setTimeLog(timeLog);
        }
        return temperatureData;
    }
}
