package com.google.android.gms.internal.clearcut;

import android.database.ContentObserver;
import android.os.Handler;
/* loaded from: classes7.dex */
public final class a extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzab f8568a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(zzab zzabVar, Handler handler) {
        super(null);
        this.f8568a = zzabVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.f8568a.zzh();
        this.f8568a.c();
    }
}
