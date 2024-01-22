package com.google.android.gms.internal.vision;

import android.database.ContentObserver;
import android.os.Handler;
/* loaded from: classes10.dex */
public final class c extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzaq f9965a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(zzaq zzaqVar, Handler handler) {
        super(null);
        this.f9965a = zzaqVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.f9965a.zzv();
    }
}
