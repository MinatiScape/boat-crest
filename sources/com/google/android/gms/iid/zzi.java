package com.google.android.gms.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;
/* loaded from: classes6.dex */
public final class zzi extends Binder {

    /* renamed from: a  reason: collision with root package name */
    public final zze f8495a;

    public zzi(zze zzeVar) {
        this.f8495a = zzeVar;
    }

    public final void zzd(h hVar) {
        if (Binder.getCallingUid() == Process.myUid()) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "service received new intent via bind strategy");
            }
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "intent being queued for bg execution");
            }
            this.f8495a.h.execute(new j(this, hVar));
            return;
        }
        throw new SecurityException("Binding only allowed within app");
    }
}
