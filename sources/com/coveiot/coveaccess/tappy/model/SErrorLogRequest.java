package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SErrorLogRequest implements Serializable {
    @SerializedName("LogSeverity")
    private String logSeverity = null;
    @SerializedName("DateUTC")
    private String dateUTC = null;
    @SerializedName("Module")
    private String module = null;
    @SerializedName("ErrorGUID")
    private String errorGUID = null;
    @SerializedName("Details")
    private String details = null;
    @SerializedName("ExtraInfo")
    private ExtraInfo extraInfo = null;

    /* loaded from: classes8.dex */
    public static class ExtraInfo implements Serializable {
        @SerializedName("Extra")
        private String extra = null;

        public String getExtra() {
            return this.extra;
        }

        public void setExtra(String str) {
            this.extra = str;
        }
    }

    public String getDateUTC() {
        return this.dateUTC;
    }

    public String getDetails() {
        return this.details;
    }

    public String getErrorGUID() {
        return this.errorGUID;
    }

    public ExtraInfo getExtraInfo() {
        return this.extraInfo;
    }

    public String getLogSeverity() {
        return this.logSeverity;
    }

    public String getModule() {
        return this.module;
    }

    public void setDateUTC(String str) {
        this.dateUTC = str;
    }

    public void setDetails(String str) {
        this.details = str;
    }

    public void setErrorGUID(String str) {
        this.errorGUID = str;
    }

    public void setExtraInfo(ExtraInfo extraInfo) {
        this.extraInfo = extraInfo;
    }

    public void setLogSeverity(String str) {
        this.logSeverity = str;
    }

    public void setModule(String str) {
        this.module = str;
    }
}
