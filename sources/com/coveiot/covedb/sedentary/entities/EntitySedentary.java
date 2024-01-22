package com.coveiot.covedb.sedentary.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(primaryKeys = {"serial_number", "timestamp"}, tableName = "sedentary_data")
/* loaded from: classes8.dex */
public class EntitySedentary {
    @NonNull
    @ColumnInfo(name = "timestamp")

    /* renamed from: a  reason: collision with root package name */
    public long f6967a;
    @NonNull
    @ColumnInfo(name = "serial_number")
    public String b;
    @ColumnInfo(name = "recordNumber")
    public int c;
    @ColumnInfo(name = "date")
    public String d;
    @ColumnInfo(name = "is_saved_server")
    public boolean e;

    public String getDate() {
        return this.d;
    }

    public int getRecordNumber() {
        return this.c;
    }

    @NonNull
    public String getSerial_number() {
        return this.b;
    }

    public long getTimestamp() {
        return this.f6967a;
    }

    public boolean isSavedServer() {
        return this.e;
    }

    public void setDate(String str) {
        this.d = str;
    }

    public void setRecordNumber(int i) {
        this.c = i;
    }

    public void setSavedServer(boolean z) {
        this.e = z;
    }

    public void setSerial_number(@NonNull String str) {
        this.b = str;
    }

    public void setTimestamp(long j) {
        this.f6967a = j;
    }

    public String toString() {
        return "EntitySedentary{timestamp=" + this.f6967a + ", serial_number='" + this.b + "', recordNumber=" + this.c + ", date='" + this.d + "', isSavedServer=" + this.e + '}';
    }
}
