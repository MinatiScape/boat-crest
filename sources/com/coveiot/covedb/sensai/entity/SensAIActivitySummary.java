package com.coveiot.covedb.sensai.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
@Entity(primaryKeys = {WorkoutConstants.SESSION_ID, "serial_no"}, tableName = "sensai_activity_summary")
/* loaded from: classes8.dex */
public class SensAIActivitySummary {
    @ColumnInfo(name = "is_data_aggregate_saved")
    public boolean A;
    @NonNull
    @ColumnInfo(name = WorkoutConstants.SESSION_ID)

    /* renamed from: a  reason: collision with root package name */
    public String f6969a;
    @ColumnInfo(name = "client_ref_id")
    public String b;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String c;
    @NonNull
    @ColumnInfo(name = "timestamp")
    public Long d;
    @NonNull
    @ColumnInfo(name = "activity_type")
    public int e;
    @ColumnInfo(name = "duration_sec")
    public long f;
    @ColumnInfo(name = "total_steps")
    public int g;
    @ColumnInfo(name = "total_calories")
    public double h;
    @ColumnInfo(name = "hand")
    public int i;
    @ColumnInfo(name = "goal_type")
    public int j;
    @ColumnInfo(name = "target_goal_value")
    public int k;
    @ColumnInfo(name = "goal_completion_pct")
    public int l;
    @ColumnInfo(name = "max_hr")
    public int m;
    @ColumnInfo(name = "avg_hr")
    public int n;
    @ColumnInfo(name = "total_swings")
    public int o;
    @ColumnInfo(name = "played")
    public int p;
    @ColumnInfo(name = "hit_pct")
    public int q;
    @ColumnInfo(name = "max_hand_speed")
    public int r;
    @ColumnInfo(name = "avg_hand_speed")
    public int s;
    @ColumnInfo(name = "bowling_type")
    public int t;
    @ColumnInfo(name = "total_balls_bowled")
    public int u;
    @ColumnInfo(name = "max_arm_speed")
    public int v;
    @ColumnInfo(name = "min_arm_speed")
    public int w;
    @ColumnInfo(name = "avg_arm_speed")
    public int x;
    @ColumnInfo(name = "is_saved_server")
    public boolean y;
    @ColumnInfo(name = "is_add_to_compare")
    public boolean z;

    public int getActivityType() {
        return this.e;
    }

    public int getAvgArmSpeed() {
        return this.x;
    }

    public int getAvgHR() {
        return this.n;
    }

    public int getAvgHandSpeed() {
        return this.s;
    }

    public int getBowlingType() {
        return this.t;
    }

    public String getClientRefID() {
        return this.b;
    }

    public long getDurationSec() {
        return this.f;
    }

    public int getGoalCompletionPct() {
        return this.l;
    }

    public int getGoalType() {
        return this.j;
    }

    public int getHand() {
        return this.i;
    }

    public int getHitPct() {
        return this.q;
    }

    @NonNull
    public String getMacAddress() {
        return this.c;
    }

    public int getMaxArmSpeed() {
        return this.v;
    }

    public int getMaxHR() {
        return this.m;
    }

    public int getMaxHandSpeed() {
        return this.r;
    }

    public int getMinArmSpeed() {
        return this.w;
    }

    public int getPlayed() {
        return this.p;
    }

    @NonNull
    public String getSessionId() {
        return this.f6969a;
    }

    public int getTargetGoalValue() {
        return this.k;
    }

    @NonNull
    public Long getTimestamp() {
        return this.d;
    }

    public int getTotalBallsBowled() {
        return this.u;
    }

    public double getTotalCalories() {
        return this.h;
    }

    public int getTotalSteps() {
        return this.g;
    }

    public int getTotalSwings() {
        return this.o;
    }

    public boolean isAddToCompare() {
        return this.z;
    }

    public boolean isDataAggregateSaved() {
        return this.A;
    }

    public boolean isSavedServer() {
        return this.y;
    }

    public void setActivityType(int i) {
        this.e = i;
    }

    public void setAddToCompare(boolean z) {
        this.z = z;
    }

    public void setAvgArmSpeed(int i) {
        this.x = i;
    }

    public void setAvgHR(int i) {
        this.n = i;
    }

    public void setAvgHandSpeed(int i) {
        this.s = i;
    }

    public void setBowlingType(int i) {
        this.t = i;
    }

    public void setClientRefID(String str) {
        this.b = str;
    }

    public void setDataAggregateSaved(boolean z) {
        this.A = z;
    }

    public void setDurationSec(long j) {
        this.f = j;
    }

    public void setGoalCompletionPct(int i) {
        this.l = i;
    }

    public void setGoalType(int i) {
        this.j = i;
    }

    public void setHand(int i) {
        this.i = i;
    }

    public void setHitPct(int i) {
        this.q = i;
    }

    public void setMacAddress(@NonNull String str) {
        this.c = str;
    }

    public void setMaxArmSpeed(int i) {
        this.v = i;
    }

    public void setMaxHR(int i) {
        this.m = i;
    }

    public void setMaxHandSpeed(int i) {
        this.r = i;
    }

    public void setMinArmSpeed(int i) {
        this.w = i;
    }

    public void setPlayed(int i) {
        this.p = i;
    }

    public void setSavedServer(boolean z) {
        this.y = z;
    }

    public void setSessionId(@NonNull String str) {
        this.f6969a = str;
    }

    public void setTargetGoalValue(int i) {
        this.k = i;
    }

    public void setTimestamp(@NonNull Long l) {
        this.d = l;
    }

    public void setTotalBallsBowled(int i) {
        this.u = i;
    }

    public void setTotalCalories(double d) {
        this.h = d;
    }

    public void setTotalSteps(int i) {
        this.g = i;
    }

    public void setTotalSwings(int i) {
        this.o = i;
    }
}
