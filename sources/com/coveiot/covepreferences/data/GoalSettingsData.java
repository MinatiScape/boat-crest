package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class GoalSettingsData {
    public static GoalSettingsData c;

    /* renamed from: a  reason: collision with root package name */
    public int f7027a = 10000;
    public int b = 8;

    public static void clearInstance() {
        c = null;
    }

    public static GoalSettingsData getInstance() {
        if (c == null) {
            c = new GoalSettingsData();
        }
        return c;
    }

    public int getSleep_hours() {
        return this.b;
    }

    public int getSteps() {
        return this.f7027a;
    }

    public GoalSettingsData setSleep_hours(int i) {
        this.b = i;
        return this;
    }

    public GoalSettingsData setSteps(int i) {
        this.f7027a = i;
        return this;
    }
}
