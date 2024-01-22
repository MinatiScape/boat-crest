package com.coveiot.coveaccess.fitness.model;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class FitnessRecordData {
    @SerializedName("baseUnit")
    @Expose
    public String baseUnit;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    @Expose
    public String time;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("tzOffset")
    @Expose
    public String tzOffset;

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public String getDate() {
        return this.date;
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

    public void setBaseUnit(String str) {
        this.baseUnit = str;
    }

    public void setDate(String str) {
        this.date = str;
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
}
