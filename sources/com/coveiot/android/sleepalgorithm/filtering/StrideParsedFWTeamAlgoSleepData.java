package com.coveiot.android.sleepalgorithm.filtering;

import com.coveiot.android.sleepalgorithm.filtering.SleepData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class StrideParsedFWTeamAlgoSleepData extends SleepData {
    public static final int SLEEP_DATA_ANALYSIS_END_HOUR = 11;
    public static final int SLEEP_DATA_ANALYSIS_START_HOUR = 19;
    public static final Map<Integer, Integer> n;
    public static final Map<Integer, Integer> o;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public SleepDataModel l;
    public byte[] m;

    static {
        HashMap hashMap = new HashMap();
        n = hashMap;
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
        HashMap hashMap2 = new HashMap();
        o = hashMap2;
        hashMap2.put(0, 12);
        hashMap2.put(1, 13);
        hashMap2.put(2, 14);
        hashMap2.put(3, 15);
        hashMap2.put(4, 16);
        hashMap2.put(5, 17);
        hashMap2.put(6, 18);
        hashMap2.put(7, 19);
        hashMap2.put(8, 20);
        hashMap2.put(9, 21);
        hashMap2.put(10, 22);
        hashMap2.put(11, 23);
        hashMap2.put(12, 0);
        hashMap2.put(13, 1);
        hashMap2.put(14, 2);
        hashMap2.put(15, 3);
        hashMap2.put(16, 4);
        hashMap2.put(17, 5);
        hashMap2.put(18, 6);
        hashMap2.put(19, 7);
        hashMap2.put(20, 8);
        hashMap2.put(21, 9);
        hashMap2.put(22, 10);
        hashMap2.put(23, 11);
    }

    public StrideParsedFWTeamAlgoSleepData(byte[] bArr, SleepData.SUPPORTED_ENCODING supported_encoding) throws Exception {
        super(bArr, supported_encoding);
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        e();
        d();
    }

    public final void c(int i) {
        while (true) {
            byte[] bArr = this.m;
            if (i >= bArr.length) {
                return;
            }
            if (bArr[i] != SleepData.VALUE_AWAKE) {
                int i2 = i;
                int i3 = i2;
                int i4 = 0;
                while (true) {
                    byte[] bArr2 = this.m;
                    if (i2 >= bArr2.length) {
                        break;
                    } else if (bArr2[i2] != SleepData.VALUE_AWAKE) {
                        if (bArr2[i2] == SleepData.VALUE_DEEPSLEEP) {
                            i4++;
                        }
                        i2++;
                        i3++;
                    } else {
                        int i5 = i2 - i;
                        if (i5 >= 90 && i4 <= i5 * 0.7d) {
                            if (this.d == -1) {
                                int i6 = i % 60;
                                this.e = i6;
                                this.d = o.get(Integer.valueOf((i - i6) / 60)).intValue();
                            }
                            int i7 = i2 % 60;
                            this.g = i7;
                            this.f = o.get(Integer.valueOf((i2 - i7) / 60)).intValue();
                        } else {
                            Arrays.fill(bArr2, i, i2, (byte) 0);
                        }
                    }
                }
                i = i3;
            }
            i++;
        }
    }

    public final void d() {
        int i;
        if (this.e != -1 && (i = this.d) != -1 && this.g != -1 && this.f != -1) {
            Map<Integer, Integer> map = o;
            int intValue = (map.get(Integer.valueOf(this.f)).intValue() * 60) + this.g;
            this.j = 0;
            this.i = 0;
            this.k = 0;
            this.h = 0;
            for (int intValue2 = (map.get(Integer.valueOf(i)).intValue() * 60) + this.e; intValue2 < intValue; intValue2++) {
                byte[] bArr = this.m;
                if (bArr[intValue2] == SleepData.VALUE_AWAKE) {
                    this.j++;
                } else if (bArr[intValue2] == SleepData.VALUE_DEEPSLEEP) {
                    this.k++;
                } else if (bArr[intValue2] == SleepData.VALUE_LIGHTSLEEP) {
                    this.i++;
                }
            }
            int i2 = this.k + this.i + this.j;
            this.h = i2;
            if (i2 == 0 || this.d == this.f) {
                this.g = -1;
                this.f = -1;
                this.e = -1;
                this.d = -1;
                Arrays.fill(this.m, 0, getSleepRawData().length, (byte) 0);
            }
            this.l = new SleepDataModel(this.m, this.d, this.e, this.f, this.g, this.i, this.k, this.j, this.h, 0);
            return;
        }
        Arrays.fill(this.m, 0, getSleepRawData().length, (byte) 0);
        this.l = new SleepDataModel(this.m, -1, -1, -1, -1, 0, 0, 0, 0, 0);
    }

    public final void e() {
        byte[] copyOf = Arrays.copyOf(getSleepRawData(), getSleepRawData().length);
        this.m = copyOf;
        Map<Integer, Integer> map = n;
        Arrays.fill(copyOf, 0, map.get(19).intValue() * 60, (byte) 0);
        Arrays.fill(this.m, map.get(11).intValue() * 60, this.m.length, (byte) 0);
        int intValue = map.get(19).intValue() * 60;
        while (true) {
            byte[] bArr = this.m;
            if (intValue >= bArr.length || bArr[intValue] != SleepData.VALUE_AWAKE) {
                break;
            }
            intValue++;
        }
        c(intValue);
    }

    public SleepDataModel getSleepDataModel() {
        return this.l;
    }
}
