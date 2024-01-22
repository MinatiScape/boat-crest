package com.coveiot.android.leonardo.sp02.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "spo2_records")
/* loaded from: classes5.dex */
public class SPO2Record {
    public String date;
    public boolean isLevelIntepreTation;
    public boolean isSyncedToServer;
    @PrimaryKey
    public long timeStamp;
    public String timeZoneOffSet;
    public double value;

    public String getDate() {
        return this.date;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public String getTimeZoneOffSet() {
        return this.timeZoneOffSet;
    }

    public double getValue() {
        return this.value;
    }

    public boolean isSyncedToServer() {
        return this.isSyncedToServer;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setSyncedToServer(boolean z) {
        this.isSyncedToServer = z;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public void setTimeZoneOffSet(String str) {
        this.timeZoneOffSet = str;
    }

    public void setValue(double d) {
        this.value = d;
    }
}
