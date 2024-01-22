package com.coveiot.coveaccess.diagnostics.model;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes8.dex */
public class TestSummary {
    @SerializedName("charger")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6478a;
    @SerializedName(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION)
    @Expose
    private String b;
    @SerializedName("vibration")
    @Expose
    private String c;
    @SerializedName("button")
    @Expose
    private String d;
    @SerializedName("touchscreen")
    @Expose
    private String e;
    @SerializedName("accelerometer")
    @Expose
    private String f;
    @SerializedName(DeviceKey.TempData)
    @Expose
    private String g;
    @SerializedName("ppg")
    @Expose
    private String h;

    public String getAccelerometer() {
        return this.f;
    }

    public String getButton() {
        return this.d;
    }

    public String getCharger() {
        return this.f6478a;
    }

    public String getDisplay() {
        return this.b;
    }

    public String getPpg() {
        return this.h;
    }

    public String getTemperature() {
        return this.g;
    }

    public String getTouchscreen() {
        return this.e;
    }

    public String getVibration() {
        return this.c;
    }

    public void setAccelerometer(String str) {
        this.f = str;
    }

    public void setButton(String str) {
        this.d = str;
    }

    public void setCharger(String str) {
        this.f6478a = str;
    }

    public void setDisplay(String str) {
        this.b = str;
    }

    public void setPpg(String str) {
        this.h = str;
    }

    public void setTemperature(String str) {
        this.g = str;
    }

    public void setTouchscreen(String str) {
        this.e = str;
    }

    public void setVibration(String str) {
        this.c = str;
    }
}
