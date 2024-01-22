package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.LifecycleFragment;
/* loaded from: classes6.dex */
public final class c extends zag {
    public final /* synthetic */ Intent h;
    public final /* synthetic */ LifecycleFragment i;

    public c(Intent intent, LifecycleFragment lifecycleFragment, int i) {
        this.h = intent;
        this.i = lifecycleFragment;
    }

    @Override // com.google.android.gms.common.internal.zag
    public final void zaa() {
        Intent intent = this.h;
        if (intent != null) {
            this.i.startActivityForResult(intent, 2);
        }
    }
}
