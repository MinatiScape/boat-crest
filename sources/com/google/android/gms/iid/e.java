package com.google.android.gms.iid;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.gcm.zzj;
/* loaded from: classes6.dex */
public final class e extends zzj {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzaf f8486a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(zzaf zzafVar, Looper looper) {
        super(looper);
        this.f8486a = zzafVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        this.f8486a.zze(message);
    }
}
