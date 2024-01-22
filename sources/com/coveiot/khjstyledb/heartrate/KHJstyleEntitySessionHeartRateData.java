package com.coveiot.khjstyledb.heartrate;

import androidx.annotation.NonNull;
import androidx.room.Entity;
@Entity(primaryKeys = {"serialNo", "timeStamp"}, tableName = "history_session_hr")
/* loaded from: classes8.dex */
public class KHJstyleEntitySessionHeartRateData {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public long f7125a;
    public int b;
    @NonNull
    public String serialNo;

    public int getHeartRate() {
        return this.b;
    }

    @NonNull
    public String getSerialNo() {
        return this.serialNo;
    }

    public long getTimeStamp() {
        return this.f7125a;
    }

    public void setHeartRate(int i) {
        this.b = i;
    }

    public void setSerialNo(@NonNull String str) {
        this.serialNo = str;
    }

    public void setTimeStamp(long j) {
        this.f7125a = j;
    }
}
