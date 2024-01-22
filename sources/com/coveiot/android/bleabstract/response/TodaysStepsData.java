package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public class TodaysStepsData {

    /* renamed from: a  reason: collision with root package name */
    public int f3679a;
    public double b;
    public int c;

    public double getTotalCalories() {
        return this.b;
    }

    public int getTotalDistance() {
        return this.c;
    }

    public int getTotalSteps() {
        return this.f3679a;
    }

    public void setTotalCalories(double d) {
        this.b = d;
    }

    public void setTotalDistance(int i) {
        this.c = i;
    }

    public void setTotalSteps(int i) {
        this.f3679a = i;
    }

    public String toString() {
        return "TodaysStepsData{totalSteps=" + this.f3679a + ", totalCalories=" + this.b + ", totalDistance=" + this.c + '}';
    }
}
