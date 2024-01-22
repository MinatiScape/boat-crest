package com.coveiot.covedb.sensai.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import java.util.ArrayList;
@Entity(primaryKeys = {WorkoutConstants.SESSION_ID, "serial_no"}, tableName = "sensai_activity_summary_details")
/* loaded from: classes8.dex */
public class SensAIActivitySummaryDetails {
    @NonNull
    @ColumnInfo(name = WorkoutConstants.SESSION_ID)

    /* renamed from: a  reason: collision with root package name */
    public String f6970a;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String b;
    @NonNull
    @ColumnInfo(name = "activity_type")
    public int c;
    @ColumnInfo(name = "details_data_num")
    public int d;
    @ColumnInfo(name = "unix_time_stamp")
    public Long e;
    @ColumnInfo(name = "hand_speed")
    public ArrayList<Integer> f;
    @ColumnInfo(name = "hr")
    public ArrayList<Integer> g;
    @ColumnInfo(name = "steps")
    public ArrayList<Integer> h;
    @ColumnInfo(name = "distance")
    public ArrayList<Integer> i;
    @ColumnInfo(name = "calories")
    public ArrayList<Integer> j;
    @ColumnInfo(name = "hit_miss")
    public ArrayList<Integer> k;
    @ColumnInfo(name = "is_feedback_saved")
    public boolean l;

    public int getActivityType() {
        return this.c;
    }

    public ArrayList<Integer> getCalories() {
        return this.j;
    }

    public int getDetailsDataNum() {
        return this.d;
    }

    public ArrayList<Integer> getDistance() {
        return this.i;
    }

    public ArrayList<Integer> getHandSpeed() {
        return this.f;
    }

    public ArrayList<Integer> getHitOrMiss() {
        return this.k;
    }

    public ArrayList<Integer> getHr() {
        return this.g;
    }

    @NonNull
    public String getMacAddress() {
        return this.b;
    }

    @NonNull
    public String getSessionId() {
        return this.f6970a;
    }

    public ArrayList<Integer> getSteps() {
        return this.h;
    }

    public Long getUnixTimeStamp() {
        return this.e;
    }

    public boolean isFeedbackSaved() {
        return this.l;
    }

    public void setActivityType(int i) {
        this.c = i;
    }

    public void setCalories(ArrayList<Integer> arrayList) {
        this.j = arrayList;
    }

    public void setDetailsDataNum(int i) {
        this.d = i;
    }

    public void setDistance(ArrayList<Integer> arrayList) {
        this.i = arrayList;
    }

    public void setFeedbackSaved(boolean z) {
        this.l = z;
    }

    public void setHandSpeed(ArrayList<Integer> arrayList) {
        this.f = arrayList;
    }

    public void setHitOrMiss(ArrayList<Integer> arrayList) {
        this.k = arrayList;
    }

    public void setHr(ArrayList<Integer> arrayList) {
        this.g = arrayList;
    }

    public void setMacAddress(@NonNull String str) {
        this.b = str;
    }

    public void setSessionId(@NonNull String str) {
        this.f6970a = str;
    }

    public void setSteps(ArrayList<Integer> arrayList) {
        this.h = arrayList;
    }

    public void setUnixTimeStamp(Long l) {
        this.e = l;
    }
}
