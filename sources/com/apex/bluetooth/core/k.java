package com.apex.bluetooth.core;

import androidx.annotation.NonNull;
import com.apex.bluetooth.enumeration.EABleConnectState;
/* loaded from: classes.dex */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    public EABleConnectState f2190a = EABleConnectState.STATE_IDLE;

    public EABleConnectState a() {
        return this.f2190a;
    }

    public void a(@NonNull EABleConnectState eABleConnectState) {
        if (this.f2190a != eABleConnectState) {
            this.f2190a = eABleConnectState;
        }
    }
}
