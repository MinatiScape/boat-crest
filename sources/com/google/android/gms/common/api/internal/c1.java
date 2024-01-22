package com.google.android.gms.common.api.internal;

import android.os.Bundle;
/* loaded from: classes6.dex */
public final class c1 implements Runnable {
    public final /* synthetic */ LifecycleCallback h;
    public final /* synthetic */ String i;
    public final /* synthetic */ zzb j;

    public c1(zzb zzbVar, LifecycleCallback lifecycleCallback, String str) {
        this.j = zzbVar;
        this.h = lifecycleCallback;
        this.i = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Bundle bundle;
        Bundle bundle2;
        Bundle bundle3;
        zzb zzbVar = this.j;
        i = zzbVar.i;
        if (i > 0) {
            LifecycleCallback lifecycleCallback = this.h;
            bundle = zzbVar.j;
            if (bundle != null) {
                bundle3 = zzbVar.j;
                bundle2 = bundle3.getBundle(this.i);
            } else {
                bundle2 = null;
            }
            lifecycleCallback.onCreate(bundle2);
        }
        i2 = this.j.i;
        if (i2 >= 2) {
            this.h.onStart();
        }
        i3 = this.j.i;
        if (i3 >= 3) {
            this.h.onResume();
        }
        i4 = this.j.i;
        if (i4 >= 4) {
            this.h.onStop();
        }
        i5 = this.j.i;
        if (i5 >= 5) {
            this.h.onDestroy();
        }
    }
}
