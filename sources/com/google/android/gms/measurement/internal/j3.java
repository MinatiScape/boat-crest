package com.google.android.gms.measurement.internal;

import android.os.Handler;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.Constants;
/* loaded from: classes10.dex */
public final class j3 {

    /* renamed from: a  reason: collision with root package name */
    public i3 f10120a;
    public final /* synthetic */ zzjy b;

    public j3(zzjy zzjyVar) {
        this.b = zzjyVar;
    }

    @WorkerThread
    public final void a(long j) {
        Handler handler;
        this.f10120a = new i3(this, this.b.zzs.zzav().currentTimeMillis(), j);
        handler = this.b.b;
        handler.postDelayed(this.f10120a, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    @WorkerThread
    public final void b() {
        Handler handler;
        this.b.zzg();
        if (this.f10120a != null) {
            handler = this.b.b;
            handler.removeCallbacks(this.f10120a);
        }
        this.b.zzs.zzm().p.zza(false);
    }
}
