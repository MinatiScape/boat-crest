package com.coveiot.android.crpsdk;

import kotlin.Metadata;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/coveiot/android/crpsdk/BleConnectStateChangeEvent;", "", "", "state", "I", "getState", "()I", "<init>", "(I)V", "crpSdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class BleConnectStateChangeEvent {

    /* renamed from: a  reason: collision with root package name */
    public final int f4108a;

    public BleConnectStateChangeEvent(int i) {
        this.f4108a = i;
    }

    public final int getState() {
        return this.f4108a;
    }
}
