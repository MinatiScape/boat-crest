package com.coveiot.coveaccess.model.server;

import com.coveiot.coveaccess.manualdata.model.BaseUnitsManual;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessSessionDataBean {
    @SerializedName("baseUnit")
    private String baseUnit;
    @SerializedName("baseUnits")
    private BaseUnitsManual baseUnitsManual;
    @SerializedName("bpValues")
    private List<List<Integer>> bpValues;
    @SerializedName("clientRefId")
    private String clientRefId;
    @SerializedName("ecgGraph")
    private EcgGraphBean ecgGraph;
    @SerializedName("hr")
    private int hr;
    @SerializedName("hrValues")
    private List<Integer> hrValues;
    @SerializedName(DeviceKey.HRV)
    private int hrv;
    @SerializedName("rriValues")
    private List<Integer> rriValues;
    @SerializedName("sessionEndDate")
    private String sessionEndDate;
    @SerializedName("sessionStartDate")
    private String sessionStartDate;
    @SerializedName("spo2")
    private Integer spo2;
    @SerializedName("stressLevel")
    private int stressLevel;
    @SerializedName("timeLog")
    private TimeLogBean timeLog;
    @SerializedName("totalSampleCount")
    private int totalSampleCount;
    @SerializedName("type")
    private String type;
    @SerializedName("tzOffset")
    private String tzOffset;
    @SerializedName("value")
    private List<Integer> value;
    @SerializedName("vascAging")
    private Integer vascAging;

    /* loaded from: classes8.dex */
    public static class EcgGraphBean {
        @SerializedName("mediaClass")
        private String mediaClass;
        @SerializedName("mediaId")
        private String mediaId;

        public String getMediaClass() {
            return this.mediaClass;
        }

        public String getMediaId() {
            return this.mediaId;
        }

        public void setMediaClass(String str) {
            this.mediaClass = str;
        }

        public void setMediaId(String str) {
            this.mediaId = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class TimeLogBean {
        @SerializedName("logs")
        private List<LogsBean> logs;

        /* loaded from: classes8.dex */
        public static class LogsBean {
            @SerializedName("codedValues")
            private List<Integer> codedValues;
            @SerializedName("seqNumber")
            private String seqNumber;

            public List<Integer> getCodedValues() {
                return this.codedValues;
            }

            public String getSeqNumber() {
                return this.seqNumber;
            }

            public void setCodedValues(List<Integer> list) {
                this.codedValues = list;
            }

            public void setSeqNumber(String str) {
                this.seqNumber = str;
            }
        }

        public List<LogsBean> getLogs() {
            return this.logs;
        }

        public void setLogs(List<LogsBean> list) {
            this.logs = list;
        }
    }

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public BaseUnitsManual getBaseUnitsManual() {
        return this.baseUnitsManual;
    }

    public List<List<Integer>> getBpValues() {
        return this.bpValues;
    }

    public String getClientRefId() {
        return this.clientRefId;
    }

    public EcgGraphBean getEcgGraph() {
        return this.ecgGraph;
    }

    public int getHr() {
        return this.hr;
    }

    public List<Integer> getHrValues() {
        return this.hrValues;
    }

    public int getHrv() {
        return this.hrv;
    }

    public List<Integer> getRriValues() {
        return this.rriValues;
    }

    public String getSessionEndDate() {
        return this.sessionEndDate;
    }

    public String getSessionStartDate() {
        return this.sessionStartDate;
    }

    public Integer getSpo2() {
        return this.spo2;
    }

    public int getStressLevel() {
        return this.stressLevel;
    }

    public TimeLogBean getTimeLog() {
        return this.timeLog;
    }

    public int getTotalSampleCount() {
        return this.totalSampleCount;
    }

    public String getType() {
        return this.type;
    }

    public String getTzOffset() {
        return this.tzOffset;
    }

    public List<Integer> getValue() {
        return this.value;
    }

    public Integer getVascAging() {
        return this.vascAging;
    }

    public void setBaseUnit(String str) {
        this.baseUnit = str;
    }

    public void setBaseUnitsManual(BaseUnitsManual baseUnitsManual) {
        this.baseUnitsManual = baseUnitsManual;
    }

    public void setBpValues(List<List<Integer>> list) {
        this.bpValues = list;
    }

    public void setClientRefId(String str) {
        this.clientRefId = str;
    }

    public void setEcgGraph(EcgGraphBean ecgGraphBean) {
        this.ecgGraph = ecgGraphBean;
    }

    public void setHr(int i) {
        this.hr = i;
    }

    public void setHrValues(List<Integer> list) {
        this.hrValues = list;
    }

    public void setHrv(int i) {
        this.hrv = i;
    }

    public void setRriValues(List<Integer> list) {
        this.rriValues = list;
    }

    public void setSessionEndDate(String str) {
        this.sessionEndDate = str;
    }

    public void setSessionStartDate(String str) {
        this.sessionStartDate = str;
    }

    public void setSpo2(Integer num) {
        this.spo2 = num;
    }

    public void setStressLevel(int i) {
        this.stressLevel = i;
    }

    public void setTimeLog(TimeLogBean timeLogBean) {
        this.timeLog = timeLogBean;
    }

    public void setTotalSampleCount(int i) {
        this.totalSampleCount = i;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setTzOffset(String str) {
        this.tzOffset = str;
    }

    public void setValue(List<Integer> list) {
        this.value = list;
    }

    public void setVascAging(Integer num) {
        this.vascAging = num;
    }
}
