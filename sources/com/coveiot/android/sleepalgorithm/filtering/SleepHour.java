package com.coveiot.android.sleepalgorithm.filtering;
/* loaded from: classes6.dex */
public class SleepHour {

    /* renamed from: a  reason: collision with root package name */
    public int f5685a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = -1;
    public int g = -1;
    public int h = -1;
    public int i = -1;
    public int j = -1;
    public int k = -1;
    public int l = -1;
    public byte[] m;

    public SleepHour(byte[] bArr) throws Exception {
        if (bArr.length == 60) {
            this.m = bArr;
            c();
            return;
        }
        throw new Exception("Not enough data");
    }

    public int a(int i, int i2) {
        int i3 = 0;
        if (i2 >= 0 && i2 < 60) {
            while (i2 < 60) {
                if (this.m[i2] == i) {
                    i3++;
                }
                i2++;
            }
        }
        return i3;
    }

    public int b(int i, int i2) {
        int i3 = 0;
        if (i2 > 0 && i2 < 60) {
            while (i2 >= 0) {
                if (this.m[i2] == i) {
                    i3++;
                }
                i2--;
            }
        }
        return i3;
    }

    public final void c() {
        for (int i = 0; i < 60; i++) {
            byte[] bArr = this.m;
            if (bArr[i] == SleepData.VALUE_AWAKE) {
                this.c++;
            } else if (bArr[i] == SleepData.VALUE_DEEPSLEEP) {
                this.f5685a++;
            } else if (bArr[i] == SleepData.VALUE_REM) {
                this.e++;
            } else if (bArr[i] == SleepData.VALUE_LIGHTSLEEP) {
                this.b++;
                this.j = i;
            } else {
                this.d++;
            }
            if (this.k == -1 && bArr[i] == SleepData.VALUE_LIGHTSLEEP) {
                this.k = i;
            }
            if (this.l == -1 && bArr[i] == SleepData.VALUE_AWAKE) {
                this.l = i;
            }
            if (this.h == -1 && i > 0 && bArr[i - 1] == SleepData.VALUE_LIGHTSLEEP && bArr[i] == SleepData.VALUE_AWAKE) {
                this.h = i;
            }
            if (this.i == -1 && i > 0 && bArr[i - 1] == SleepData.VALUE_DEEPSLEEP && bArr[i] == SleepData.VALUE_AWAKE) {
                this.i = i;
            }
            if (i < 59 && bArr[i] == SleepData.VALUE_AWAKE) {
                int i2 = i + 1;
                if (bArr[i2] == SleepData.VALUE_LIGHTSLEEP) {
                    this.f = i2;
                }
            }
            if (i < 59 && bArr[i] == SleepData.VALUE_AWAKE) {
                int i3 = i + 1;
                if (bArr[i3] == SleepData.VALUE_DEEPSLEEP) {
                    this.g = i3;
                }
            }
        }
    }

    public int getCountAwake() {
        return this.c;
    }

    public int getCountDeepSleep() {
        return this.f5685a;
    }

    public int getCountInvalid() {
        return this.d;
    }

    public int getCountLightSleep() {
        return this.b;
    }

    public int getCountOfAwakeAfter(int i) {
        return a(SleepData.VALUE_AWAKE, i);
    }

    public int getCountOfAwakeBefore(int i) {
        return b(SleepData.VALUE_AWAKE, i);
    }

    public int getCountOfDeepSleepAfter(int i) {
        return a(SleepData.VALUE_DEEPSLEEP, i);
    }

    public int getCountOfDeepSleepBefore(int i) {
        return b(SleepData.VALUE_DEEPSLEEP, i);
    }

    public int getCountOfLightSleepAfter(int i) {
        return a(SleepData.VALUE_LIGHTSLEEP, i);
    }

    public int getCountOfLightSleepBefore(int i) {
        return b(SleepData.VALUE_LIGHTSLEEP, i);
    }

    public int getCountRemSleep() {
        return this.e;
    }

    public int getIndexFirstDeepSleepToAwakeTransition() {
        return this.i;
    }

    public int getIndexFirstLightSleepToAwakeTransition() {
        return this.h;
    }

    public int getIndexFirstValueAwake() {
        return this.l;
    }

    public int getIndexFirstValueLightSleep() {
        return this.k;
    }

    public int getIndexLastAwakeToDeepSleepTransition() {
        return this.g;
    }

    public int getIndexLastAwakeToLightSleepTransition() {
        return this.f;
    }

    public int getIndexOfLastLightSleep() {
        return this.j;
    }

    public byte[] getRawHourData() {
        return this.m;
    }
}
