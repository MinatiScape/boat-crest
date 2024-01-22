package com.coveiot.android.femalewellness.wellnesscalendar.model;
/* loaded from: classes4.dex */
public class WomenWellnessDetails {
    public static WomenWellnessDetails f;

    /* renamed from: a  reason: collision with root package name */
    public String f4411a;
    public String b;
    public String c;
    public int d;
    public int e;

    public static WomenWellnessDetails getInstance() {
        if (f == null) {
            f = new WomenWellnessDetails();
        }
        return f;
    }

    public int getmMenstruationCycleLength() {
        return this.e;
    }

    public int getmMenstruationPeriodLength() {
        return this.d;
    }

    public String getmUserSetPeriodDay() {
        return this.f4411a;
    }

    public String getmUserSetPeriodMonth() {
        return this.b;
    }

    public String getmUserSetPeriodYear() {
        return this.c;
    }

    public void setmMenstruationCycleLength(int i) {
        this.e = i;
    }

    public void setmMenstruationPeriodLength(int i) {
        this.d = i;
    }

    public void setmUserSetPeriodDay(String str) {
        this.f4411a = str;
    }

    public void setmUserSetPeriodMonth(String str) {
        this.b = str;
    }

    public void setmUserSetPeriodYear(String str) {
        this.c = str;
    }
}
