package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
/* loaded from: classes6.dex */
public final class n extends m<Bundle> {
    public n(int i, int i2, Bundle bundle) {
        super(i, 1, bundle);
    }

    @Override // com.google.android.gms.cloudmessaging.m
    public final void a(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("data");
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        c(bundle2);
    }

    @Override // com.google.android.gms.cloudmessaging.m
    public final boolean d() {
        return false;
    }
}
