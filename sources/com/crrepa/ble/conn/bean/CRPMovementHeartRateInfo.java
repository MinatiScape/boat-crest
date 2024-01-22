package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPMovementHeartRateInfo {
    public static final byte BADMINTON_TYPE = 4;
    public static final byte BASEBALL_TYPE = 15;
    public static final byte BASKETBALL_TYPE = 5;
    public static final byte BOATING_TYPE = 19;
    public static final byte BOWLING_TYPE = 22;
    public static final byte CRICKET_TYPE = 28;
    public static final byte DANCE_TYPE = 14;
    public static final byte DUMBBELLS_TYPE = 23;
    public static final byte ELLIPTICAL_TYPE = 16;
    public static final byte FOOTBALL_TYPE = 6;
    public static final byte FREE_TRAINING_TYPE = 18;
    public static final byte GOLF_TYPE = 11;
    public static final byte INDOOR_CYCLING_TYPE = 17;
    public static final byte INDOOR_RUN_TYPE = 27;
    public static final byte INDOOR_WALK_TYPE = 26;
    public static final byte KABADDI_TYPE = 29;
    public static final byte MOUNTAINEERING_TYPE = 8;
    public static final byte ON_FOOT_TYPE = 25;
    public static final byte OUTDOOR_CYCLING_TYPE = 2;
    public static final byte ROPE_TYPE = 3;
    public static final byte RUGBY_TYPE = 10;
    public static final byte RUN_TYPE = 1;
    public static final byte SIT_UPS_TYPE = 24;
    public static final byte SKI_TYPE = 21;
    public static final byte SWIM_TYPE = 7;
    public static final byte TENNIS_TYPE = 9;
    public static final byte TRAIL_RUNNING_TYPE = 20;
    public static final byte WALK_TYPE = 0;
    public static final byte WORKOUT_TYPE = 13;
    public static final byte YOGA_TYPE = 12;

    /* renamed from: a  reason: collision with root package name */
    public int f7658a;
    public long b;
    public long c;
    public int d;
    public int e;
    public int f;
    public int g;

    public int getCalories() {
        return this.g;
    }

    public int getDistance() {
        return this.f;
    }

    public long getEndTime() {
        return this.c;
    }

    public long getStartTime() {
        return this.b;
    }

    public int getSteps() {
        return this.e;
    }

    public int getType() {
        return this.f7658a;
    }

    public int getValidTime() {
        return this.d;
    }

    public void setCalories(int i) {
        this.g = i;
    }

    public void setDistance(int i) {
        this.f = i;
    }

    public void setEndTime(long j) {
        this.c = j;
    }

    public void setStartTime(long j) {
        this.b = j;
    }

    public void setSteps(int i) {
        this.e = i;
    }

    public void setType(int i) {
        this.f7658a = i;
    }

    public void setValidTime(int i) {
        this.d = i;
    }
}
