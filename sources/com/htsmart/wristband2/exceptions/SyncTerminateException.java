package com.htsmart.wristband2.exceptions;
/* loaded from: classes11.dex */
public class SyncTerminateException extends WristbandException {

    /* renamed from: a  reason: collision with root package name */
    private int f12023a;

    public SyncTerminateException(int i) {
        this.f12023a = i;
    }

    public int getSyncDataType() {
        return this.f12023a;
    }
}
