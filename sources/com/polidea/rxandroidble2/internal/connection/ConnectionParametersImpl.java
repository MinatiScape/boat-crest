package com.polidea.rxandroidble2.internal.connection;

import com.polidea.rxandroidble2.ConnectionParameters;
/* loaded from: classes12.dex */
public class ConnectionParametersImpl implements ConnectionParameters {

    /* renamed from: a  reason: collision with root package name */
    public final int f13406a;
    public final int b;
    public final int c;

    public ConnectionParametersImpl(int i, int i2, int i3) {
        this.f13406a = i;
        this.b = i2;
        this.c = i3;
    }

    @Override // com.polidea.rxandroidble2.ConnectionParameters
    public int getConnectionInterval() {
        return this.f13406a;
    }

    @Override // com.polidea.rxandroidble2.ConnectionParameters
    public int getSlaveLatency() {
        return this.b;
    }

    @Override // com.polidea.rxandroidble2.ConnectionParameters
    public int getSupervisionTimeout() {
        return this.c;
    }
}
