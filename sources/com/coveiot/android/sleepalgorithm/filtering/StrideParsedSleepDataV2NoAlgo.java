package com.coveiot.android.sleepalgorithm.filtering;

import com.coveiot.android.sleepalgorithm.filtering.SleepData;
import com.coveiot.android.sleepalgorithm.filtering.model.FilteredSleepData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class StrideParsedSleepDataV2NoAlgo extends SleepData {
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
    public SleepDataModel o;
    public byte[] p;
    public SleepData.SUPPORTED_ENCODING q;
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

    public StrideParsedSleepDataV2NoAlgo(byte[] bArr, SleepData.SUPPORTED_ENCODING supported_encoding) throws Exception {
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
        this.q = SleepData.SUPPORTED_ENCODING.ENCODING_NONE;
        this.r = -1;
        this.s = -1;
        this.q = supported_encoding;
        this.p = Arrays.copyOf(getSleepRawData(), getSleepRawData().length);
        int i = 0;
        while (true) {
            byte[] bArr2 = this.p;
            if (i < bArr2.length) {
                if (bArr2[i] == 3) {
                    bArr2[i] = -1;
                }
                i++;
            } else {
                SleepData.VALUE_AWAKE = 0;
                SleepData.VALUE_DEEPSLEEP = 2;
                SleepData.VALUE_LIGHTSLEEP = 1;
                f();
                d();
                c();
                e();
                return;
            }
        }
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
        int i2;
        int i3;
        byte[] copyOf = Arrays.copyOf(getSleepRawData(), getSleepRawData().length);
        this.p = copyOf;
        if (this.e != -1 && (i = this.d) != -1 && this.j != -1 && this.f != -1) {
            Map<Integer, Integer> map = u;
            int intValue = (map.get(Integer.valueOf(i)).intValue() * 60) + this.e;
            int intValue2 = (map.get(Integer.valueOf(this.f)).intValue() * 60) + this.j;
            Arrays.fill(this.p, 0, intValue, (byte) 0);
            Arrays.fill(this.p, intValue2, bArr.length - 1, (byte) 0);
            this.m = 0;
            this.l = 0;
            this.n = 0;
            this.k = 0;
            while (intValue < intValue2) {
                byte[] bArr2 = this.p;
                if (bArr2[intValue] == SleepData.VALUE_AWAKE) {
                    this.m++;
                } else if (bArr2[intValue] == SleepData.VALUE_DEEPSLEEP) {
                    this.n++;
                } else if (bArr2[intValue] == SleepData.VALUE_LIGHTSLEEP) {
                    this.l++;
                }
                intValue++;
            }
            int i4 = this.n;
            int i5 = this.l;
            int i6 = i4 + i5;
            this.k = i6;
            if (i6 != 0 && (i2 = this.d) != (i3 = this.f)) {
                this.o = new SleepDataModel(this.p, i2, this.e, i3, this.j, i5, i4, this.m, i6, 0);
                return;
            }
            try {
                this.o = new StrideParsedNoAlgoSleepData(getSleepRawData(), this.q).getSleepDataModel();
                return;
            } catch (Exception unused) {
                this.j = -1;
                this.f = -1;
                this.e = -1;
                this.d = -1;
                Arrays.fill(this.p, 0, getSleepRawData().length, (byte) 0);
                this.o = new SleepDataModel(this.p, -1, -1, -1, -1, 0, 0, 0, 0, 0);
                return;
            }
        }
        Arrays.fill(copyOf, 0, getSleepRawData().length, (byte) 0);
        this.o = new SleepDataModel(this.p, -1, -1, -1, -1, 0, 0, 0, 0, 0);
    }

    public final void f() {
        int[] iArr;
        int i = 0;
        int intValue = u.get(Integer.valueOf(t[0])).intValue() * 60;
        while (true) {
            byte[] bArr = this.p;
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
            } else if (this.p[intValue2] > 0) {
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
        return new FilteredSleepData(getSleepDataModel().getFilteredSleepData(), Type.KaHa, this.s, this.r);
    }

    public int getIndexOfMaxLightSleep() {
        return this.g;
    }

    public SleepDataModel getSleepDataModel() {
        return this.o;
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
