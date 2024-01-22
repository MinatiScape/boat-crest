package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class k extends x {
    public final /* synthetic */ BaseGmsClient.ConnectionProgressReportCallbacks b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(l lVar, zabf zabfVar, BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        super(zabfVar);
        this.b = connectionProgressReportCallbacks;
    }

    @Override // com.google.android.gms.common.api.internal.x
    @GuardedBy("mLock")
    public final void a() {
        this.b.onReportServiceBinding(new ConnectionResult(16, null));
    }
}
