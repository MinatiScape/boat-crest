package com.google.android.gms.common.internal;

import android.content.Intent;
import androidx.fragment.app.Fragment;
/* loaded from: classes6.dex */
public final class b extends zag {
    public final /* synthetic */ Intent h;
    public final /* synthetic */ Fragment i;
    public final /* synthetic */ int j;

    public b(Intent intent, Fragment fragment, int i) {
        this.h = intent;
        this.i = fragment;
        this.j = i;
    }

    @Override // com.google.android.gms.common.internal.zag
    public final void zaa() {
        Intent intent = this.h;
        if (intent != null) {
            this.i.startActivityForResult(intent, this.j);
        }
    }
}
