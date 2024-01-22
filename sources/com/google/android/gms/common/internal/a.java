package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;
/* loaded from: classes6.dex */
public final class a extends zag {
    public final /* synthetic */ Intent h;
    public final /* synthetic */ Activity i;
    public final /* synthetic */ int j;

    public a(Intent intent, Activity activity, int i) {
        this.h = intent;
        this.i = activity;
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
