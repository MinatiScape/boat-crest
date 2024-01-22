package com.coveiot.khjstyledb.gps;

import androidx.annotation.NonNull;
import androidx.room.Entity;
@Entity(primaryKeys = {"serialNo", "timeStamp"}, tableName = "history_session_gps")
/* loaded from: classes8.dex */
public class KHJstyleSessionGPSData {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public long f7123a;
    public double b;
    public double c;
    @NonNull
    public String serialNo;

    public double getLatitude() {
        return this.b;
    }

    public double getLongitude() {
        return this.c;
    }

    @NonNull
    public String getSerialNo() {
        return this.serialNo;
    }

    public long getTimeStamp() {
        return this.f7123a;
    }

    public void setLatitude(double d) {
        this.b = d;
    }

    public void setLongitude(double d) {
        this.c = d;
    }

    public void setSerialNo(@NonNull String str) {
        this.serialNo = str;
    }

    public void setTimeStamp(long j) {
        this.f7123a = j;
    }
}
