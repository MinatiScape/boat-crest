package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes6.dex */
public final class u0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f8288a;
    public final ConnectionResult b;

    public u0(ConnectionResult connectionResult, int i) {
        Preconditions.checkNotNull(connectionResult);
        this.b = connectionResult;
        this.f8288a = i;
    }

    public final int a() {
        return this.f8288a;
    }

    public final ConnectionResult b() {
        return this.b;
    }
}
