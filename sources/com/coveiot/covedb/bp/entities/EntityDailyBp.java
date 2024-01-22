package com.coveiot.covedb.bp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
@Entity(primaryKeys = {"date", "serial_no"}, tableName = "daily_bp")
/* loaded from: classes8.dex */
public class EntityDailyBp {
    @NonNull
    public String date;
    public int diastolic_bp;
    public boolean is_sync_server;
    @NonNull
    public String serial_no;
    public int systolic_bp;

    @NonNull
    public String getDate() {
        return this.date;
    }

    public int getDiastolicBp() {
        return this.diastolic_bp;
    }

    @NonNull
    public String getSerialNo() {
        return this.serial_no;
    }

    public int getSystolicBp() {
        return this.systolic_bp;
    }

    public boolean isIsSyncServer() {
        return this.is_sync_server;
    }

    public void setDate(@NonNull String str) {
        this.date = str;
    }

    public void setDiastolicBp(int i) {
        this.diastolic_bp = i;
    }

    public void setIsSyncServer(boolean z) {
        this.is_sync_server = z;
    }

    public void setSerialNo(@NonNull String str) {
        this.serial_no = str;
    }

    public void setSystolicBp(int i) {
        this.systolic_bp = i;
    }
}
