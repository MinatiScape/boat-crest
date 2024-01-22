package com.goodix.ble.libble.v2.gb.pojo;
/* loaded from: classes5.dex */
public class GBError extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private int f8013a;

    public GBError(int i, String str) {
        super(str);
        this.f8013a = i;
    }

    public GBError(int i, String str, Throwable th) {
        super(str, th);
        this.f8013a = i;
    }

    public int getErrorCode() {
        return this.f8013a;
    }
}
