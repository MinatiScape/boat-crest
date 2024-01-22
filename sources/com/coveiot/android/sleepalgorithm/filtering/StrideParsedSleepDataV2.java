package com.coveiot.android.sleepalgorithm.filtering;

import com.coveiot.android.sleepalgorithm.filtering.SleepData;
import com.coveiot.android.sleepalgorithm.filtering.model.FilteredSleepData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class StrideParsedSleepDataV2 extends SleepData {
    public static final int SLEEP_DATA_ANALYSIS_END_HOUR = 11;
    public static final int SLEEP_DATA_ANALYSIS_START_HOUR = 19;
    public static final int[] s = {19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    public static final Map<Integer, Integer> t;
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
    public SleepData.SUPPORTED_ENCODING r;

    static {
        HashMap hashMap = new HashMap();
        t = hashMap;
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

    public StrideParsedSleepDataV2(byte[] bArr, SleepData.SUPPORTED_ENCODING supported_encoding) throws Exception {
        super(bArr, supported_encoding);
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.k = -1;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.r = SleepData.SUPPORTED_ENCODING.ENCODING_NONE;
        this.r = supported_encoding;
        SleepData.VALUE_AWAKE = 0;
        SleepData.VALUE_DEEPSLEEP = 2;
        SleepData.VALUE_LIGHTSLEEP = 1;
        h();
        d();
        c();
        g();
    }

    public final void c() {
        int i = this.j;
        if (i != -1) {
            int[] iArr = s;
            int indexFirstLightSleepToAwakeTransition = getSleepHour(iArr[i]).getIndexFirstLightSleepToAwakeTransition();
            if (indexFirstLightSleepToAwakeTransition == -1) {
                indexFirstLightSleepToAwakeTransition = getSleepHour(iArr[this.j]).getIndexFirstDeepSleepToAwakeTransition();
            }
            if (indexFirstLightSleepToAwakeTransition == -1) {
                indexFirstLightSleepToAwakeTransition = getSleepHour(iArr[this.j]).getIndexFirstValueAwake();
            }
            if (indexFirstLightSleepToAwakeTransition == -1) {
                indexFirstLightSleepToAwakeTransition = getSleepHour(iArr[this.j]).getIndexOfLastLightSleep();
            }
            if (indexFirstLightSleepToAwakeTransition == -1) {
                indexFirstLightSleepToAwakeTransition = 1;
            }
            this.f = iArr[this.j];
            this.k = indexFirstLightSleepToAwakeTransition;
        }
    }

    public final void d() {
        int i = this.i;
        if (i != -1) {
            int[] iArr = s;
            int indexLastAwakeToLightSleepTransition = getSleepHour(iArr[i]).getIndexLastAwakeToLightSleepTransition();
            if (indexLastAwakeToLightSleepTransition == -1) {
                indexLastAwakeToLightSleepTransition = getSleepHour(iArr[this.i]).getIndexLastAwakeToDeepSleepTransition();
            }
            if (indexLastAwakeToLightSleepTransition == -1) {
                indexLastAwakeToLightSleepTransition = getSleepHour(iArr[this.i]).getIndexFirstValueLightSleep();
            }
            if (indexLastAwakeToLightSleepTransition == -1) {
                indexLastAwakeToLightSleepTransition = 59;
            }
            this.d = iArr[this.i];
            this.e = indexLastAwakeToLightSleepTransition;
        }
    }

    public final boolean e(int i) {
        int[] iArr = s;
        int i2 = iArr[i];
        int i3 = i > 0 ? iArr[i - 1] : i2;
        if (i2 >= 6 && i2 != 23) {
            if (getSleepHour(i2).getCountLightSleep() >= 15) {
                return true;
            }
        } else if (getSleepHour(i2).getCountLightSleep() >= 15) {
            return true;
        } else {
            if (getSleepHour(i2).getCountLightSleep() >= 0 && getSleepHour(i3).getCountLightSleep() >= 15) {
                if (i > 0) {
                    i--;
                }
                if (f(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean f(int i) {
        int i2 = s[i];
        if (i2 >= 6 && i2 != 23) {
            if (getSleepHour(i2).getCountAwake() <= 15) {
                return true;
            }
        } else if (getSleepHour(i2).getCountAwake() <= 20) {
            return true;
        }
        return false;
    }

    public final void g() {
        int i;
        byte[] bArr;
        int i2;
        int i3;
        byte[] copyOf = Arrays.copyOf(getSleepRawData(), getSleepRawData().length);
        this.q = copyOf;
        if (this.e != -1 && (i = this.d) != -1 && this.k != -1 && this.f != -1) {
            Map<Integer, Integer> map = t;
            int intValue = (map.get(Integer.valueOf(i)).intValue() * 60) + this.e;
            int intValue2 = (map.get(Integer.valueOf(this.f)).intValue() * 60) + this.k;
            Arrays.fill(this.q, 0, intValue, (byte) 0);
            Arrays.fill(this.q, intValue2, bArr.length - 1, (byte) 0);
            this.n = 0;
            this.m = 0;
            this.o = 0;
            this.l = 0;
            while (intValue < intValue2) {
                byte[] bArr2 = this.q;
                if (bArr2[intValue] == SleepData.VALUE_AWAKE) {
                    this.n++;
                } else if (bArr2[intValue] == SleepData.VALUE_DEEPSLEEP) {
                    this.o++;
                } else if (bArr2[intValue] == SleepData.VALUE_LIGHTSLEEP) {
                    this.m++;
                }
                intValue++;
            }
            int i4 = this.o;
            int i5 = this.m;
            int i6 = i4 + i5;
            this.l = i6;
            if (i6 != 0 && (i2 = this.d) != (i3 = this.f)) {
                this.p = new SleepDataModel(this.q, i2, this.e, i3, this.k, i5, i4, this.n, i6, 0);
                return;
            }
            try {
                this.p = new StrideParsedFWTeamAlgoSleepData(getSleepRawData(), this.r).getSleepDataModel();
                return;
            } catch (Exception unused) {
                this.k = -1;
                this.f = -1;
                this.e = -1;
                this.d = -1;
                Arrays.fill(this.q, 0, getSleepRawData().length, (byte) 0);
                this.p = new SleepDataModel(this.q, -1, -1, -1, -1, 0, 0, 0, 0, 0);
                return;
            }
        }
        Arrays.fill(copyOf, 0, getSleepRawData().length, (byte) 0);
        this.p = new SleepDataModel(this.q, -1, -1, -1, -1, 0, 0, 0, 0, 0);
    }

    public int getCountAwake() {
        return this.n;
    }

    public int getCountTotalDeepSleep() {
        return this.o;
    }

    public int getCountTotalLightSleep() {
        return this.m;
    }

    public int getCountTotalSleep() {
        return this.l;
    }

    public FilteredSleepData getFilteredSleepData() {
        return new FilteredSleepData(getSleepDataModel().getFilteredSleepData(), Type.KaHa, -1, -1);
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
        return this.k;
    }

    public int getSleepStartHour() {
        return this.d;
    }

    public int getSleepStartMinute() {
        return this.e;
    }

    public final void h() {
        for (int i = 3; i < 11; i++) {
            SleepHour sleepHour = getSleepHour(s[i]);
            if (sleepHour.getCountLightSleep() > this.h) {
                this.h = sleepHour.getCountLightSleep();
                this.g = i;
            }
        }
        if (this.h < 15) {
            return;
        }
        boolean z = true;
        boolean z2 = true;
        for (int i2 = this.g; i2 >= 0; i2--) {
            if (!e(i2) || !f(i2)) {
                int[] iArr = s;
                if (getSleepHour(iArr[i2]).getCountLightSleep() >= 5) {
                    this.i = i2;
                } else {
                    this.i = i2 + 1;
                }
                if (!z2 || i2 <= 5) {
                    break;
                }
                int i3 = i2 - 1;
                if (i3 < 0) {
                    i3 = 0;
                }
                if (getSleepHour(iArr[i3]).getCountLightSleep() <= 15) {
                    break;
                }
                z2 = false;
            }
        }
        int i4 = this.g;
        while (true) {
            int[] iArr2 = s;
            if (i4 >= iArr2.length) {
                return;
            }
            if (!e(i4) || !f(i4)) {
                if (getSleepHour(iArr2[i4]).getCountLightSleep() >= 5) {
                    this.j = i4;
                } else {
                    this.j = i4 - 1;
                }
                if (!z || i4 > 10) {
                    return;
                }
                int i5 = i4 + 1;
                if (i5 >= iArr2.length) {
                    i5 = i4;
                }
                int i6 = i4 + 2;
                if (i6 >= iArr2.length) {
                    i6 = i4;
                }
                if (getSleepHour(iArr2[i5]).getCountLightSleep() > 15) {
                    z = false;
                } else if (getSleepHour(iArr2[i6]).getCountLightSleep() <= 15) {
                    return;
                }
            }
            i4++;
        }
    }
}
