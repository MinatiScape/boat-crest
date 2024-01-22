package com.coveiot.android.sleepalgorithm.filtering;

import com.coveiot.android.sleepalgorithm.filtering.SleepData;
import com.coveiot.android.sleepalgorithm.filtering.model.FilteredSleepData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class CZ0ParsedSleepData extends SleepData {
    public static final int SLEEP_DATA_ANALYSIS_END_HOUR = 11;
    public static final int SLEEP_DATA_ANALYSIS_START_HOUR = 19;
    public static final int[] t = {19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    public static final Map<Integer, Integer> u;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public SleepDataModel p;
    public byte[] q;
    public int r;
    public int s;

    static {
        HashMap hashMap = new HashMap();
        u = hashMap;
        hashMap.put(12, 0);
        hashMap.put(13, 1);
        hashMap.put(14, 2);
        hashMap.put(15, 3);
        hashMap.put(16, 4);
        hashMap.put(17, 5);
        hashMap.put(18, 6);
        hashMap.put(19, 7);
        hashMap.put(20, 8);
        hashMap.put(21, 9);
        hashMap.put(22, 10);
        hashMap.put(23, 11);
        hashMap.put(0, 12);
        hashMap.put(1, 13);
        hashMap.put(2, 14);
        hashMap.put(3, 15);
        hashMap.put(4, 16);
        hashMap.put(5, 17);
        hashMap.put(6, 18);
        hashMap.put(7, 19);
        hashMap.put(8, 20);
        hashMap.put(9, 21);
        hashMap.put(10, 22);
        hashMap.put(11, 23);
    }

    public CZ0ParsedSleepData(byte[] bArr, SleepData.SUPPORTED_ENCODING supported_encoding) throws Exception {
        super(bArr, supported_encoding);
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        SleepData.SUPPORTED_ENCODING supported_encoding2 = SleepData.SUPPORTED_ENCODING.ENCODING_NONE;
        this.r = -1;
        this.s = -1;
        this.q = Arrays.copyOf(getSleepRawData(), getSleepRawData().length);
        SleepData.VALUE_AWAKE = 0;
        SleepData.VALUE_DEEPSLEEP = 2;
        SleepData.VALUE_LIGHTSLEEP = 1;
        f();
        d();
        c();
        e();
    }

    public final void c() {
        int i = this.i;
        if (i != -1) {
            this.f = t[i];
        }
    }

    public final void d() {
        int i = this.h;
        if (i != -1) {
            this.d = t[i];
        }
    }

    public final void e() {
        int i;
        byte[] bArr;
        byte[] copyOf = Arrays.copyOf(getSleepRawData(), getSleepRawData().length);
        this.q = copyOf;
        if (this.e != -1 && (i = this.d) != -1 && this.j != -1 && this.f != -1) {
            Map<Integer, Integer> map = u;
            int intValue = (map.get(Integer.valueOf(i)).intValue() * 60) + this.e;
            int intValue2 = (map.get(Integer.valueOf(this.f)).intValue() * 60) + this.j;
            Arrays.fill(this.q, 0, intValue, (byte) 0);
            Arrays.fill(this.q, intValue2 + 1, bArr.length - 1, (byte) 0);
            this.m = 0;
            this.l = 0;
            this.n = 0;
            this.o = 0;
            this.k = 0;
            while (intValue <= intValue2) {
                byte[] bArr2 = this.q;
                if (bArr2[intValue] == SleepData.VALUE_AWAKE) {
                    this.m++;
                } else if (bArr2[intValue] == SleepData.VALUE_DEEPSLEEP) {
                    this.n++;
                } else if (bArr2[intValue] == SleepData.VALUE_LIGHTSLEEP) {
                    this.l++;
                } else if (bArr2[intValue] == SleepData.VALUE_REM) {
                    this.o++;
                }
                intValue++;
            }
            int i2 = this.n + this.l + this.o;
            this.k = i2;
            if (i2 == 0 || this.d == this.f) {
                this.j = -1;
                this.f = -1;
                this.e = -1;
                this.d = -1;
                Arrays.fill(this.q, 0, getSleepRawData().length, (byte) 0);
            }
            this.p = new SleepDataModel(this.q, this.d, this.e, this.f, this.j, this.l, this.n, this.m, this.k, this.o);
            return;
        }
        Arrays.fill(copyOf, 0, getSleepRawData().length, (byte) 0);
        this.p = new SleepDataModel(this.q, -1, -1, -1, -1, 0, 0, 0, 0, 0);
    }

    public final void f() {
        int[] iArr;
        int i = 0;
        int intValue = u.get(Integer.valueOf(t[0])).intValue() * 60;
        while (true) {
            byte[] bArr = this.q;
            if (intValue >= bArr.length) {
                break;
            } else if (bArr[intValue] > 0) {
                this.s = intValue;
                break;
            } else {
                intValue++;
            }
        }
        int i2 = this.s;
        int i3 = -1;
        if (i2 != 0) {
            this.e = i2 % 60;
            this.h = i2 / 60;
            int i4 = -1;
            int i5 = 0;
            while (true) {
                int[] iArr2 = t;
                if (i5 >= iArr2.length) {
                    break;
                }
                if (iArr2[i5] == u.get(Integer.valueOf(this.h)).intValue()) {
                    i4 = i5;
                }
                i5++;
            }
            this.h = i4;
        }
        int intValue2 = u.get(Integer.valueOf(t[iArr.length - 1])).intValue() * 60;
        while (true) {
            if (intValue2 < u.get(Integer.valueOf(t[0])).intValue() * 60) {
                break;
            } else if (this.q[intValue2] > 0) {
                this.r = intValue2;
                break;
            } else {
                intValue2--;
            }
        }
        int i6 = this.r;
        if (i6 == 0) {
            return;
        }
        this.j = i6 % 60;
        this.i = i6 / 60;
        while (true) {
            int[] iArr3 = t;
            if (i < iArr3.length) {
                if (iArr3[i] == u.get(Integer.valueOf(this.i)).intValue()) {
                    i3 = i;
                }
                i++;
            } else {
                this.i = i3;
                return;
            }
        }
    }

    public int getCountAwake() {
        return this.m;
    }

    public int getCountTotalDeepSleep() {
        return this.n;
    }

    public int getCountTotalLightSleep() {
        return this.l;
    }

    public int getCountTotalSleep() {
        return this.k;
    }

    public FilteredSleepData getFilteredSleepData() {
        return new FilteredSleepData(getSleepDataModel().getFilteredSleepData(), Type.KaHaWithREM, this.s, this.r);
    }

    public int getIndexOfMaxLightSleep() {
        return this.g;
    }

    public SleepDataModel getSleepDataModel() {
        return this.p;
    }

    public int getSleepEndHour() {
        return this.f;
    }

    public int getSleepEndMinute() {
        return this.j;
    }

    public int getSleepStartHour() {
        return this.d;
    }

    public int getSleepStartMinute() {
        return this.e;
    }
}
