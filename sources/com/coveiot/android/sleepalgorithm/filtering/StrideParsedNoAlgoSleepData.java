package com.coveiot.android.sleepalgorithm.filtering;

import com.coveiot.android.sleepalgorithm.filtering.SleepData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class StrideParsedNoAlgoSleepData extends SleepData {
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

    public StrideParsedNoAlgoSleepData(byte[] bArr, SleepData.SUPPORTED_ENCODING supported_encoding) throws Exception {
        super(bArr, supported_encoding);
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        c();
    }

    public final void c() {
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

    public SleepDataModel getSleepDataModel() {
        return this.l;
    }
}
