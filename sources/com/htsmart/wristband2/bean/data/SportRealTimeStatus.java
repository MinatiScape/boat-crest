package com.htsmart.wristband2.bean.data;
/* loaded from: classes11.dex */
public class SportRealTimeStatus {
    public static final int STATUS_PAUSE = 2;
    public static final int STATUS_RESUME = 3;
    public static final int STATUS_STOP = 0;

    /* renamed from: a  reason: collision with root package name */
    public int f11980a;
    public int b;

    public SportRealTimeStatus(int i, int i2) {
        this.f11980a = i;
        this.b = i2;
    }

    public int getSportStatus() {
        return this.b;
    }

    public int getSportTimeId() {
        return this.f11980a;
    }
}
