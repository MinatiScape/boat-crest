package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class j extends x {
    public final /* synthetic */ ConnectionResult b;
    public final /* synthetic */ l c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(l lVar, zabf zabfVar, ConnectionResult connectionResult) {
        super(zabfVar);
        this.c = lVar;
        this.b = connectionResult;
    }

    @Override // com.google.android.gms.common.api.internal.x
    @GuardedBy("mLock")
    public final void a() {
        this.c.j.d(this.b);
    }
}
