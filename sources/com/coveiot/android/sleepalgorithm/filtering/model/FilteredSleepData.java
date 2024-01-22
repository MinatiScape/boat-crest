package com.coveiot.android.sleepalgorithm.filtering.model;

import com.coveiot.android.sleepalgorithm.filtering.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
/* loaded from: classes6.dex */
public class FilteredSleepData {
    public static final int HOURS_IN_A_DAY = 24;
    public static final int MAX_UNITS_IN_SLEEP_HOUR = 60;

    /* renamed from: a  reason: collision with root package name */
    public byte[] f5687a;
    public ArrayList<Integer> b;
    public ArrayList<Integer> c;
    public ArrayList<Integer> d;
    public ArrayList<Integer> e;
    public Type f;
    public HashMap<Integer, FilteredSleepHour> g = new HashMap<>();
    public ArrayList<Integer> h = new ArrayList<>();

    public FilteredSleepData(byte[] bArr, Type type, int i, int i2) {
        this.f = type;
        byte[] copyOf = Arrays.copyOf(bArr, 1440);
        this.f5687a = copyOf;
        int length = bArr.length;
        if (length < 1440) {
            Arrays.fill(copyOf, length, copyOf.length, (byte) 0);
        }
        this.h.clear();
        a();
    }

    public final void a() {
        for (int i = 0; i < 24; i++) {
            try {
                int i2 = (i + 12) % 24;
                this.h.add(Integer.valueOf(i2));
                if (this.f == Type.KaHa) {
                    int i3 = i * 60;
                    this.g.put(Integer.valueOf(i2), new FilteredSleepHourKaHa(Arrays.copyOfRange(this.f5687a, i3, i3 + 60)));
                }
                Type type = this.f;
                if (type == Type.KaHaWithREM) {
                    int i4 = i * 60;
                    this.g.put(Integer.valueOf(i2), new FilteredSleepHourKaHaREM(Arrays.copyOfRange(this.f5687a, i4, i4 + 60)));
                } else if (type == Type.JStyle) {
                    int i5 = i * 60;
                    this.g.put(Integer.valueOf(i2), new FilteredSleepHourJStyle(Arrays.copyOfRange(this.f5687a, i5, i5 + 60)));
                } else if (type == Type.JStyleWithREM) {
                    int i6 = i * 60;
                    this.g.put(Integer.valueOf(i2), new FilteredSleepHourJStyleWithREM(Arrays.copyOfRange(this.f5687a, i6, i6 + 60)));
                } else if (type == Type.JStyleWithREM2301) {
                    int i7 = i * 60;
                    this.g.put(Integer.valueOf(i2), new FilteredSleepHourJStyleWithREM2301(Arrays.copyOfRange(this.f5687a, i7, i7 + 60)));
                } else if (type == Type.SMA) {
                    int i8 = i * 60;
                    this.g.put(Integer.valueOf(i2), new FilteredSleepHourSMA(Arrays.copyOfRange(this.f5687a, i8, i8 + 60)));
                } else if (type == Type.Moyang) {
                    int i9 = i * 60;
                    this.g.put(Integer.valueOf(i2), new FilteredSleepHourMoyang(Arrays.copyOfRange(this.f5687a, i9, i9 + 60)));
                } else if (type == Type.IDO) {
                    int i10 = i * 60;
                    this.g.put(Integer.valueOf(i2), new FilteredSleepHourIDO(Arrays.copyOfRange(this.f5687a, i10, i10 + 60)));
                } else if (type == Type.Matrix) {
                    int i11 = i * 60;
                    this.g.put(Integer.valueOf(i2), new FilteredSleepHourMoyang(Arrays.copyOfRange(this.f5687a, i11, i11 + 60)));
                } else if (type == Type.TouchELX) {
                    int i12 = i * 60;
                    this.g.put(Integer.valueOf(i2), new FilteredSleepHourTouchElx(Arrays.copyOfRange(this.f5687a, i12, i12 + 60)));
                } else if (type == Type.EastApex) {
                    int i13 = i * 60;
                    this.g.put(Integer.valueOf(i2), new FilteredSleepHourEastApex(Arrays.copyOfRange(this.f5687a, i13, i13 + 60)));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.b = new ArrayList<>();
        Iterator<Integer> it = this.h.iterator();
        while (it.hasNext()) {
            this.b.add(Integer.valueOf(getSleepHour(it.next().intValue()).getCountDeepSleep()));
        }
        this.d = new ArrayList<>();
        Iterator<Integer> it2 = this.h.iterator();
        while (it2.hasNext()) {
            this.d.add(Integer.valueOf(getSleepHour(it2.next().intValue()).getCountLightSleep()));
        }
        this.c = new ArrayList<>();
        Iterator<Integer> it3 = this.h.iterator();
        while (it3.hasNext()) {
            this.c.add(Integer.valueOf(getSleepHour(it3.next().intValue()).getCountAwake()));
        }
        this.e = new ArrayList<>();
        Iterator<Integer> it4 = this.h.iterator();
        while (it4.hasNext()) {
            this.e.add(Integer.valueOf(getSleepHour(it4.next().intValue()).getCountREM()));
        }
    }

    public byte[] getFilteredData() {
        return this.f5687a;
    }

    public ArrayList<Integer> getHourKeys() {
        return this.h;
    }

    public ArrayList<Integer> getHourlyAwake() {
        return this.c;
    }

    public ArrayList<Integer> getHourlyDeepSleep() {
        return this.b;
    }

    public ArrayList<Integer> getHourlyLightSleep() {
        return this.d;
    }

    public ArrayList<Integer> getHourlyREM() {
        return this.e;
    }

    public FilteredSleepHour getSleepHour(int i) {
        return this.g.get(Integer.valueOf(i));
    }
}
