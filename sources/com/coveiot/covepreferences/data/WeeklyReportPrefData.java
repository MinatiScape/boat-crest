package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class WeeklyReportPrefData {
    public static WeeklyReportPrefData d;

    /* renamed from: a  reason: collision with root package name */
    public boolean f7051a = false;
    public boolean b = false;
    public long c = 0;

    public static WeeklyReportPrefData getInstance() {
        if (d == null) {
            d = new WeeklyReportPrefData();
        }
        return d;
    }

    public long getLastShownTimestamp() {
        return this.c;
    }

    public boolean isEmailVerified() {
        return this.f7051a;
    }

    public boolean isSubscribed() {
        return this.b;
    }

    public void setEmailVerified(boolean z) {
        this.f7051a = z;
    }

    public void setLastShownTimestamp(long j) {
        this.c = j;
    }

    public void setSubscribed(boolean z) {
        this.b = z;
    }
}
