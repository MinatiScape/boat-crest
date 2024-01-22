package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
/* loaded from: classes6.dex */
public final class k extends m<Void> {
    public k(int i, int i2, Bundle bundle) {
        super(i, 2, bundle);
    }

    @Override // com.google.android.gms.cloudmessaging.m
    public final void a(Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            c(null);
        } else {
            b(new zzp(4, "Invalid response to one way request"));
        }
    }

    @Override // com.google.android.gms.cloudmessaging.m
    public final boolean d() {
        return true;
    }
}
