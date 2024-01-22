package com.google.android.gms.auth;

import android.os.Bundle;
/* loaded from: classes6.dex */
public final class a implements d {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f8187a;
    public final /* synthetic */ Bundle b;

    public a(String str, Bundle bundle) {
        this.f8187a = str;
        this.b = bundle;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.google.android.gms.auth.zzl.c(java.lang.Object):java.lang.Object
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:76)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:51)
        Caused by: java.lang.NullPointerException
        */
    @Override // com.google.android.gms.auth.d
    public final /* bridge */ /* synthetic */ java.lang.Object zza(android.os.IBinder r3) throws android.os.RemoteException, java.io.IOException, com.google.android.gms.auth.GoogleAuthException {
        /*
            r2 = this;
            com.google.android.gms.internal.auth.zzf r3 = com.google.android.gms.internal.auth.zze.zzb(r3)
            java.lang.String r0 = r2.f8187a
            android.os.Bundle r1 = r2.b
            android.os.Bundle r3 = r3.zzd(r0, r1)
            com.google.android.gms.auth.zzl.c(r3)
            java.lang.String r0 = "Error"
            java.lang.String r0 = r3.getString(r0)
            java.lang.String r1 = "booleanResult"
            boolean r3 = r3.getBoolean(r1)
            if (r3 == 0) goto L1f
            r3 = 0
            return r3
        L1f:
            com.google.android.gms.auth.GoogleAuthException r3 = new com.google.android.gms.auth.GoogleAuthException
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.a.zza(android.os.IBinder):java.lang.Object");
    }
}
