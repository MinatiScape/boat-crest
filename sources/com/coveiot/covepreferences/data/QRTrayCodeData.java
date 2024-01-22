package com.coveiot.covepreferences.data;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class QRTrayCodeData implements Serializable {
    private Integer maxAllowed;
    private Integer maxCharLimit;
    private String resolution;

    public QRTrayCodeData(Integer num, Integer num2, String str) {
        this.maxAllowed = num;
        this.maxCharLimit = num2;
        this.resolution = str;
    }

    public Integer getMaxAllowed() {
        return this.maxAllowed;
    }

    public Integer getMaxCharLimit() {
        return this.maxCharLimit;
    }

    public String getResolution() {
        return this.resolution;
    }

    public void setMaxAllowed(Integer num) {
        this.maxAllowed = num;
    }

    public void setMaxCharLimit(Integer num) {
        this.maxCharLimit = num;
    }

    public void setResolution(String str) {
        this.resolution = str;
    }
}
