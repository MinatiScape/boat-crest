package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
/* loaded from: classes8.dex */
public class RRateTrainingRequest {
    @SerializedName("age")
    private String age;
    @SerializedName("deviceType")
    private String deviceType;
    @SerializedName("gender")
    private String gender;
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    private String height;
    @SerializedName("ppgType")
    private Integer ppgType;
    @SerializedName("rrValue")
    private String rrValue;
    @SerializedName("signalStr")
    private String signalStr;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    private String time;
    @SerializedName("weight")
    private String weight;

    public String getAge() {
        return this.age;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getGender() {
        return this.gender;
    }

    public String getHeight() {
        return this.height;
    }

    public Integer getPpgType() {
        return this.ppgType;
    }

    public String getRrValue() {
        return this.rrValue;
    }

    public String getSignalStr() {
        return this.signalStr;
    }

    public String getTime() {
        return this.time;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setAge(String str) {
        this.age = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public void setPpgType(Integer num) {
        this.ppgType = num;
    }

    public void setRrValue(String str) {
        this.rrValue = str;
    }

    public void setSignalStr(String str) {
        this.signalStr = str;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public void setWeight(String str) {
        this.weight = str;
    }
}
