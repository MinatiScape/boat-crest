package com.coveiot.coveaccess.fitness.model;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class AllFitnessRecordBean {
    @SerializedName("baseUnit")
    @Expose
    private String baseUnit;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("device")
    @Expose
    private BPDeviceBeans device;
    @SerializedName("flow")
    @Expose
    private String flow;
    @SerializedName("hr")
    @Expose
    private Integer hr;
    @SerializedName("meanArterialPressure")
    @Expose
    private Integer meanArterialPressure;
    @SerializedName("mood")
    @Expose
    private String mood;
    @SerializedName("pain")
    @Expose
    private String pain;
    @SerializedName("settings")
    @Expose
    private MensSettings settings;
    @SerializedName("source")
    @Expose
    private BPSourceBeans source;
    @SerializedName("symptoms")
    @Expose
    private List<String> symptoms = null;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    @Expose
    private String time;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("tzOffset")
    @Expose
    private String tzOffset;
    @SerializedName("value")
    @Expose
    private Object value;

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public String getDate() {
        return this.date;
    }

    public BPDeviceBeans getDevice() {
        return this.device;
    }

    public String getFlow() {
        return this.flow;
    }

    public Integer getHr() {
        return this.hr;
    }

    public Integer getMeanArterialPressure() {
        return this.meanArterialPressure;
    }

    public String getMood() {
        return this.mood;
    }

    public String getPain() {
        return this.pain;
    }

    public MensSettings getSettings() {
        return this.settings;
    }

    public BPSourceBeans getSource() {
        return this.source;
    }

    public List<String> getSymptoms() {
        return this.symptoms;
    }

    public String getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    public String getTzOffset() {
        return this.tzOffset;
    }

    public Object getValue() {
        return this.value;
    }

    public void setBaseUnit(String str) {
        this.baseUnit = str;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setDevice(BPDeviceBeans bPDeviceBeans) {
        this.device = bPDeviceBeans;
    }

    public void setFlow(String str) {
        this.flow = str;
    }

    public void setHr(Integer num) {
        this.hr = num;
    }

    public void setMeanArterialPressure(Integer num) {
        this.meanArterialPressure = num;
    }

    public void setMood(String str) {
        this.mood = str;
    }

    public void setPain(String str) {
        this.pain = str;
    }

    public void setSettings(MensSettings mensSettings) {
        this.settings = mensSettings;
    }

    public void setSource(BPSourceBeans bPSourceBeans) {
        this.source = bPSourceBeans;
    }

    public void setSymptoms(List<String> list) {
        this.symptoms = list;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setTzOffset(String str) {
        this.tzOffset = str;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }
}
