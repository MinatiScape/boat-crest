package com.coveiot.covepreferences.data;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class AutoActivityDetectionData {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f7010a;
    public Boolean b;
    public Boolean c;
    public Boolean d;
    public Boolean e;
    public Boolean f;
    public Boolean g;
    public Boolean h;
    public Integer i;
    public Boolean j;
    public Boolean k;
    public List<Period> l;

    /* loaded from: classes8.dex */
    public static class Period {

        /* renamed from: a  reason: collision with root package name */
        public Integer f7011a;
        public Integer b;

        public Period(Integer num, Integer num2) {
            this.f7011a = 0;
            this.b = 0;
            this.f7011a = num;
            this.b = num2;
        }

        public Integer getEndTime() {
            return this.b;
        }

        public Integer getStartTime() {
            return this.f7011a;
        }
    }

    public AutoActivityDetectionData() {
        this.f7010a = new byte[32];
        Boolean bool = Boolean.TRUE;
        this.b = bool;
        this.c = bool;
        this.d = bool;
        this.e = bool;
        this.f = bool;
        this.g = bool;
        this.h = bool;
        this.i = 10;
        this.j = bool;
        this.k = Boolean.FALSE;
        this.l = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.l = arrayList;
        arrayList.add(new Period(Integer.valueOf((int) TypedValues.CycleType.TYPE_EASING), 720));
        this.l.add(new Period(960, 1260));
    }

    public byte[] getActivities() {
        return this.f7010a;
    }

    public Integer getCountDownValue() {
        return this.i;
    }

    public Boolean getFridayEnabled() {
        return this.g;
    }

    public Boolean getMondayEnabled() {
        return this.c;
    }

    public List<Period> getPeriods() {
        return this.l;
    }

    public Boolean getSaturdayEnabled() {
        return this.h;
    }

    public Boolean getSmartModeEnabled() {
        return this.k;
    }

    public Boolean getSundayEnabled() {
        return this.b;
    }

    public Boolean getThursdayEnabled() {
        return this.f;
    }

    public Boolean getTuesdayEnabled() {
        return this.d;
    }

    public Boolean getVibrationEnabled() {
        return this.j;
    }

    public Boolean getWednesdayEnabled() {
        return this.e;
    }

    public boolean isAllDaysEnabled() {
        return this.b.booleanValue() && this.c.booleanValue() && this.d.booleanValue() && this.e.booleanValue() && this.f.booleanValue() && this.g.booleanValue() && this.h.booleanValue();
    }

    public void setActivities(byte[] bArr) {
        this.f7010a = bArr;
    }

    public void setCountDownValue(Integer num) {
        this.i = num;
    }

    public void setFridayEnabled(Boolean bool) {
        this.g = bool;
    }

    public void setMondayEnabled(Boolean bool) {
        this.c = bool;
    }

    public void setPeriods(List<Period> list) {
        this.l = list;
    }

    public void setSaturdayEnabled(Boolean bool) {
        this.h = bool;
    }

    public void setSmartModeEnabled(Boolean bool) {
        this.k = bool;
    }

    public void setSundayEnabled(Boolean bool) {
        this.b = bool;
    }

    public void setThursdayEnabled(Boolean bool) {
        this.f = bool;
    }

    public void setTuesdayEnabled(Boolean bool) {
        this.d = bool;
    }

    public void setVibrationEnabled(Boolean bool) {
        this.j = bool;
    }

    public void setWednesdayEnabled(Boolean bool) {
        this.e = bool;
    }

    public AutoActivityDetectionData(byte[] bArr, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Integer num, Boolean bool8, Boolean bool9, List<Period> list) {
        this.f7010a = new byte[32];
        Boolean bool10 = Boolean.TRUE;
        this.b = bool10;
        this.c = bool10;
        this.d = bool10;
        this.e = bool10;
        this.f = bool10;
        this.g = bool10;
        this.h = bool10;
        this.i = 10;
        this.j = bool10;
        this.k = Boolean.FALSE;
        this.l = new ArrayList();
        this.f7010a = bArr;
        this.b = bool;
        this.c = bool2;
        this.d = bool3;
        this.e = bool4;
        this.f = bool5;
        this.g = bool6;
        this.h = bool7;
        this.i = num;
        this.j = bool8;
        this.k = bool9;
        this.l = list;
    }
}
