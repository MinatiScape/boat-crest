package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public final class GetCalorieDistanceGoalResponse {

    /* renamed from: a  reason: collision with root package name */
    public int f3604a;
    public int b;
    public int c;
    public int d;
    public int e;

    public final int getCalorieGoal() {
        return this.f3604a;
    }

    public final int getDistanceGoal() {
        return this.b;
    }

    public final int getExerciseTimeGoal() {
        return this.c;
    }

    public final int getSleepGoal() {
        return this.e;
    }

    public final int getWalkHourGoal() {
        return this.d;
    }

    public final void setCalorieGoal(int i) {
        this.f3604a = i;
    }

    public final void setDistanceGoal(int i) {
        this.b = i;
    }

    public final void setExerciseTimeGoal(int i) {
        this.c = i;
    }

    public final void setSleepGoal(int i) {
        this.e = i;
    }

    public final void setWalkHourGoal(int i) {
        this.d = i;
    }
}
