package com.google.android.gms.internal.auth;

import android.database.ContentObserver;
import android.os.Handler;
/* loaded from: classes6.dex */
public final class w extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzcg f8543a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(zzcg zzcgVar, Handler handler) {
        super(null);
        this.f8543a = zzcgVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.f8543a.zze();
    }
}
