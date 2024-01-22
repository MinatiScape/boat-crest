package com.google.android.gms.auth;
/* loaded from: classes6.dex */
public final class b implements d {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AccountChangeEventsRequest f8217a;

    public b(AccountChangeEventsRequest accountChangeEventsRequest) {
        this.f8217a = accountChangeEventsRequest;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.google.android.gms.auth.zzl.c(java.lang.Object):java.lang.Object
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:76)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:51)
        Caused by: java.lang.NullPointerException
        */
    @Override // com.google.android.gms.auth.d
    public final /* bridge */ /* synthetic */ java.lang.Object zza(android.os.IBinder r2) throws android.os.RemoteException, java.io.IOException, com.google.android.gms.auth.GoogleAuthException {
        /*
            r1 = this;
            com.google.android.gms.internal.auth.zzf r2 = com.google.android.gms.internal.auth.zze.zzb(r2)
            com.google.android.gms.auth.AccountChangeEventsRequest r0 = r1.f8217a
            com.google.android.gms.auth.AccountChangeEventsResponse r2 = r2.zzh(r0)
            com.google.android.gms.auth.zzl.c(r2)
            java.util.List r2 = r2.getEvents()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.b.zza(android.os.IBinder):java.lang.Object");
    }
}
