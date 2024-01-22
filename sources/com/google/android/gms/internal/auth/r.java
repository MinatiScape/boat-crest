package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public final class r extends zzbd {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ s f8536a;

    public r(s sVar) {
        this.f8536a = sVar;
    }

    @Override // com.google.android.gms.internal.auth.zzbd, com.google.android.gms.internal.auth.zzbg
    public final void zzc(String str) {
        if (str != null) {
            this.f8536a.setResult((s) new zzbv(str));
        } else {
            this.f8536a.setResult((s) new zzbv(new Status(AuthApiStatusCodes.AUTH_APP_CERT_ERROR)));
        }
    }
}
