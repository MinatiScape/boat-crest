package com.coveiot.android.sleepalgorithm.filtering;

import com.coveiot.android.sleepalgorithm.filtering.SleepData;
import com.coveiot.android.sleepalgorithm.filtering.model.FilteredSleepData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class IDOParsedSleepData extends SleepData {
    public static final int[] r = {12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    public static final Map<Integer, Integer> s;
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
    public SleepDataModel n;
    public byte[] o;
    public int p;
    public int q;

    static {
        HashMap hashMap = new HashMap();
        s = hashMap;
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

    public IDOParsedSleepData(byte[] bArr, SleepData.SUPPORTED_ENCODING supported_encoding) throws Exception {
        super(bArr, supported_encoding);
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        SleepData.SUPPORTED_ENCODING supported_encoding2 = SleepData.SUPPORTED_ENCODING.ENCODING_NONE;
        this.p = -1;
        this.q = -1;
        this.o = Arrays.copyOf(getSleepRawData(), getSleepRawData().length);
        d();
        SleepData.VALUE_AWAKE = 1;
        SleepData.VALUE_LIGHTSLEEP = 2;
        SleepData.VALUE_DEEPSLEEP = 3;
        SleepData.VALUE_REM = 4;
        c();
    }

    public final void c() {
        int i;
        byte[] bArr;
        byte[] copyOf = Arrays.copyOf(getSleepRawData(), getSleepRawData().length);
        this.o = copyOf;
        if (this.e != -1 && (i = this.d) != -1 && this.h != -1 && this.f != -1) {
            Map<Integer, Integer> map = s;
            int intValue = (map.get(Integer.valueOf(i)).intValue() * 60) + this.e;
            int intValue2 = (map.get(Integer.valueOf(this.f)).intValue() * 60) + this.h;
            Arrays.fill(this.o, 0, intValue, (byte) -1);
            Arrays.fill(this.o, intValue2, bArr.length - 1, (byte) -1);
            this.k = 0;
            this.j = 0;
            this.l = 0;
            this.m = 0;
            this.i = 0;
            while (intValue < intValue2) {
                byte[] bArr2 = this.o;
                if (bArr2[intValue] == SleepData.VALUE_AWAKE) {
                    this.k++;
                } else if (bArr2[intValue] == SleepData.VALUE_DEEPSLEEP) {
                    this.l++;
                } else if (bArr2[intValue] == SleepData.VALUE_LIGHTSLEEP) {
                    this.j++;
                } else if (bArr2[intValue] == SleepData.VALUE_REM) {
                    this.m++;
                }
                intValue++;
            }
            int i2 = this.l;
            int i3 = this.j;
            int i4 = this.m;
            int i5 = this.k;
            int i6 = i2 + i3 + i4 + i5;
            this.i = i6;
            this.n = new SleepDataModel(this.o, this.d, this.e, this.f, this.h, i3, i2, i5, i6, i4);
            return;
        }
        Arrays.fill(copyOf, 0, getSleepRawData().length, (byte) 0);
        this.n = new SleepDataModel(this.o, -1, -1, -1, -1, 0, 0, 0, 0, 0);
    }

    public final void d() {
        int[] iArr;
        int[] iArr2;
        int intValue = s.get(Integer.valueOf(r[0])).intValue() * 60;
        while (true) {
            byte[] bArr = this.o;
            if (intValue < bArr.length) {
                if (bArr[intValue] > 0 && bArr[intValue] != SleepData.VALUE_AWAKE) {
                    this.q = intValue;
                    break;
                }
                intValue++;
            } else {
                break;
            }
        }
        int i = this.q;
        if (i != 0) {
            this.e = i % 60;
            int i2 = i / 60;
            this.d = i2;
            this.d = r[i2];
        }
        int intValue2 = s.get(Integer.valueOf(r[iArr.length - 1])).intValue() * 60;
        while (true) {
            Map<Integer, Integer> map = s;
            iArr2 = r;
            if (intValue2 < map.get(Integer.valueOf(iArr2[0])).intValue() * 60) {
                break;
            }
            byte[] bArr2 = this.o;
            if (bArr2[intValue2] > 0 && bArr2[intValue2] != SleepData.VALUE_AWAKE) {
                this.p = intValue2 + 1;
                break;
            }
            intValue2--;
        }
        int i3 = this.p;
        if (i3 != 0) {
            if (i3 == 1440) {
                this.h = 59;
                this.f = 11;
                return;
            }
            this.h = i3 % 60;
            int i4 = i3 / 60;
            this.f = i4;
            this.f = iArr2[i4];
        }
    }

    public int getCountAwake() {
        return this.k;
    }

    public int getCountTotalDeepSleep() {
        return this.l;
    }

    public int getCountTotalLightSleep() {
        return this.j;
    }

    public int getCountTotalRemSleep() {
        return this.m;
    }

    public int getCountTotalSleep() {
        return this.i;
    }

    public FilteredSleepData getFilteredSleepData() {
        return new FilteredSleepData(getSleepDataModel().getFilteredSleepData(), Type.IDO, this.q, this.p);
    }

    public int getIndexOfMaxLightSleep() {
        return this.g;
    }

    public SleepDataModel getSleepDataModel() {
        return this.n;
    }

    public int getSleepEndHour() {
        return this.f;
    }

    public int getSleepEndMinute() {
        return this.h;
    }

    public int getSleepStartHour() {
        return this.d;
    }

    public int getSleepStartMinute() {
        return this.e;
    }
}
