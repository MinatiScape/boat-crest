package com.coveiot.android.sleepalgorithm.filtering.jstyle;

import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.sleepalgorithm.filtering.Type;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepData;
import com.coveiot.android.sleepalgorithm.filtering.model.FilteredSleepData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class JStyleParsedSleepData extends JStyleSleepData {
    public static final int[] p = {12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    public static final Map<Integer, Integer> q;
    public int c;
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
    public int n;
    public int o;

    static {
        HashMap hashMap = new HashMap();
        q = hashMap;
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

    public JStyleParsedSleepData(byte[] bArr, JStyleSleepData.SUPPORTED_ENCODING supported_encoding) throws Exception {
        super(bArr, supported_encoding);
        this.c = -1;
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.o = 0;
        JStyleSleepData.SUPPORTED_ENCODING supported_encoding2 = JStyleSleepData.SUPPORTED_ENCODING.ENCODING_NONE;
        d();
    }

    public final void b() {
        int length = this.m.length - 1;
        while (true) {
            if (length < 0) {
                break;
            } else if (this.m[length] != -1) {
                this.o = length + 1;
                break;
            } else {
                length--;
            }
        }
        int i = this.o;
        if (i != 0) {
            if (i == 1440) {
                this.g = 59;
                this.e = 11;
                return;
            }
            this.g = i % 60;
            int i2 = i / 60;
            this.e = i2;
            this.e = p[i2];
        }
    }

    public final void c() {
        int i = 0;
        while (true) {
            byte[] bArr = this.m;
            if (i >= bArr.length) {
                break;
            } else if (bArr[i] != -1) {
                this.n = i;
                break;
            } else {
                i++;
            }
        }
        int i2 = this.n;
        if (i2 != 0) {
            this.d = i2 % 60;
            int i3 = i2 / 60;
            this.c = i3;
            this.c = p[i3];
        }
    }

    public final void d() {
        this.m = Arrays.copyOf(getSleepRawData(), getSleepRawData().length);
        c();
        b();
        if (this.d != -1 && this.c != -1 && this.g != -1 && this.e != -1) {
            int i = this.n;
            int i2 = this.o - 1;
            Arrays.fill(this.m, 0, i, (byte) -1);
            byte[] bArr = this.m;
            Arrays.fill(bArr, i2 + 1, bArr.length, (byte) -1);
            this.j = 0;
            this.i = 0;
            this.k = 0;
            this.h = 0;
            while (i <= i2) {
                byte[] bArr2 = this.m;
                if (bArr2[i] != -1) {
                    if ((bArr2[i] & 255) >= 0 && (bArr2[i] & 255) <= 14) {
                        this.k++;
                    } else if ((bArr2[i] & 255) >= 15) {
                        this.i++;
                    }
                } else {
                    this.j++;
                }
                i++;
            }
            int i3 = this.k;
            int i4 = this.i;
            int i5 = i3 + i4;
            this.h = i5;
            this.l = new SleepDataModel(this.m, this.c, this.d, this.e, this.g, i4, i3, this.j, i5, 0);
            return;
        }
        Arrays.fill(this.m, 0, getSleepRawData().length, (byte) -1);
        this.l = new SleepDataModel(this.m, -1, -1, -1, -1, 0, 0, 0, 0, 0);
    }

    public int getCountAwake() {
        return this.j;
    }

    public int getCountTotalDeepSleep() {
        return this.k;
    }

    public int getCountTotalLightSleep() {
        return this.i;
    }

    public int getCountTotalSleep() {
        return this.h;
    }

    public FilteredSleepData getFilteredSleepData() {
        return new FilteredSleepData(getSleepDataModel().getFilteredSleepData(), Type.JStyle, this.n, this.o);
    }

    public int getIndexOfMaxLightSleep() {
        return this.f;
    }

    public SleepDataModel getSleepDataModel() {
        return this.l;
    }

    public int getSleepEndHour() {
        return this.e;
    }

    public int getSleepEndMinute() {
        return this.g;
    }

    public int getSleepStartHour() {
        return this.c;
    }

    public int getSleepStartMinute() {
        return this.d;
    }
}
