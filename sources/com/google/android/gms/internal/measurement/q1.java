package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;
/* loaded from: classes8.dex */
public final class q1 extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzha f8922a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q1(zzha zzhaVar, Handler handler) {
        super(null);
        this.f8922a = zzhaVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.f8922a.zzf();
    }
}
