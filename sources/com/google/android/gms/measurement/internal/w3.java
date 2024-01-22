package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class w3 implements Runnable {
    public final /* synthetic */ String h;
    public final /* synthetic */ String i = "_err";
    public final /* synthetic */ Bundle j;
    public final /* synthetic */ x3 k;

    public w3(x3 x3Var, String str, String str2, Bundle bundle) {
        this.k = x3Var;
        this.h = str;
        this.j = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.k.f10135a.e((zzat) Preconditions.checkNotNull(this.k.f10135a.zzv().S(this.h, this.i, this.j, "auto", this.k.f10135a.zzav().currentTimeMillis(), false, true)), this.h);
    }
}
