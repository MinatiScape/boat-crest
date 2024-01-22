package com.google.android.gms.phenotype;

import android.database.ContentObserver;
import android.os.Handler;
/* loaded from: classes10.dex */
public final class a extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zza f10165a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(zza zzaVar, Handler handler) {
        super(null);
        this.f10165a = zzaVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.f10165a.zzb();
    }
}
