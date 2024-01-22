package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SendSleepDataRequest extends BleBaseRequest {
    public int f;
    public int g;
    public int h;

    public SendSleepDataRequest(int i, int i2, int i3) {
        this.f = i;
        this.g = i2;
        this.h = i3;
    }

    public int getTotalDeepSleep() {
        return this.g;
    }

    public int getTotalLightSleep() {
        return this.h;
    }

    public int getTotalSleep() {
        return this.f;
    }

    public String toString() {
        return "SendSleepDataRequest{totalSleep=" + this.f + ", totalDeepSleep=" + this.g + ", totalLightSleep=" + this.h + '}';
    }
}
