package com.coveiot.covedb.bp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import com.coveiot.covedb.bp.model.BpCodedValueData;
import java.util.ArrayList;
@Entity(primaryKeys = {"date", "serial_no", "start_hour"}, tableName = "hourly_bp")
/* loaded from: classes8.dex */
public class EntityHourlyBp {
    public ArrayList<BpCodedValueData> codevalues;
    @NonNull
    public String date;
    public int diastolic_bp;
    @NonNull
    public String end_hour;
    @NonNull
    public String serial_no;
    @NonNull
    public String start_hour;
    public int systolic_bp;

    public ArrayList<BpCodedValueData> getCodevalues() {
        return this.codevalues;
    }

    @NonNull
    public String getDate() {
        return this.date;
    }

    public int getDiastolicBp() {
        return this.diastolic_bp;
    }

    @NonNull
    public String getEndHour() {
        return this.end_hour;
    }

    @NonNull
    public String getSerialNo() {
        return this.serial_no;
    }

    @NonNull
    public String getStartHour() {
        return this.start_hour;
    }

    public int getSystolicBp() {
        return this.systolic_bp;
    }

    public void setCodevalues(ArrayList<BpCodedValueData> arrayList) {
        this.codevalues = arrayList;
    }

    public void setDate(@NonNull String str) {
        this.date = str;
    }

    public void setDiastolicBp(int i) {
        this.diastolic_bp = i;
    }

    public void setEndHour(@NonNull String str) {
        this.end_hour = str;
    }

    public void setSerialNo(@NonNull String str) {
        this.serial_no = str;
    }

    public void setStartHour(@NonNull String str) {
        this.start_hour = str;
    }

    public void setSystolicBp(int i) {
        this.systolic_bp = i;
    }
}
