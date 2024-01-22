package com.coveiot.coveaccess.userappsetting;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class BpCalibrationBean implements Serializable {
    @SerializedName("diastolic")
    private Integer diastolic;
    @SerializedName("recordedDate")
    private String recordedDate;
    @SerializedName("systolic")
    private Integer systolic;

    public Integer getDiastolic() {
        return this.diastolic;
    }

    public String getRecordedDate() {
        return this.recordedDate;
    }

    public Integer getSystolic() {
        return this.systolic;
    }

    public void setDiastolic(Integer num) {
        this.diastolic = num;
    }

    public void setRecordedDate(String str) {
        this.recordedDate = str;
    }

    public void setSystolic(Integer num) {
        this.systolic = num;
    }
}
