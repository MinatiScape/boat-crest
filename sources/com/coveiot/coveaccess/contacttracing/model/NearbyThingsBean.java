package com.coveiot.coveaccess.contacttracing.model;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class NearbyThingsBean {
    @SerializedName("type")

    /* renamed from: a  reason: collision with root package name */
    private String f6447a;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String b;
    @SerializedName("mac")
    private String c;
    @SerializedName("timeSpan")
    private int d;
    @SerializedName("trackerId")
    private String e;
    @SerializedName("rssi")
    private int f;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    private String g;
    @SerializedName("coordinates")
    private List<Double> h;

    public List<Double> getCoordinates() {
        return this.h;
    }

    public String getMac() {
        return this.c;
    }

    public String getName() {
        return this.b;
    }

    public int getRssi() {
        return this.f;
    }

    public String getTime() {
        return this.g;
    }

    public int getTimeSpan() {
        return this.d;
    }

    public String getTrackerId() {
        return this.e;
    }

    public String getType() {
        return this.f6447a;
    }

    public void setCoordinates(List<Double> list) {
        this.h = list;
    }

    public void setMac(String str) {
        this.c = str;
    }

    public void setName(String str) {
        this.b = str;
    }

    public void setRssi(int i) {
        this.f = i;
    }

    public void setTime(String str) {
        this.g = str;
    }

    public void setTimeSpan(int i) {
        this.d = i;
    }

    public void setTrackerId(String str) {
        this.e = str;
    }

    public void setType(String str) {
        this.f6447a = str;
    }
}
