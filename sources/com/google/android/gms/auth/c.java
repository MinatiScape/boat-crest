package com.google.android.gms.auth;
/* loaded from: classes6.dex */
public final class c implements d {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f8218a;

    public c(String str) {
        this.f8218a = str;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.google.android.gms.auth.zzl.c(java.lang.Object):java.lang.Object
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:76)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:51)
        Caused by: java.lang.NullPointerException
        */
    @Override // com.google.android.gms.auth.d
    public final /* bridge */ /* synthetic */ java.lang.Object zza(android.os.IBinder r5) throws android.os.RemoteException, java.io.IOException, com.google.android.gms.auth.GoogleAuthException {
        /*
            r4 = this;
            com.google.android.gms.internal.auth.zzf r5 = com.google.android.gms.internal.auth.zze.zzb(r5)
            java.lang.String r0 = r4.f8218a
            android.os.Bundle r5 = r5.zzg(r0)
            com.google.android.gms.auth.zzl.c(r5)
            java.lang.String r0 = "Error"
            java.lang.String r0 = r5.getString(r0)
            java.lang.String r1 = "userRecoveryIntent"
            android.os.Parcelable r5 = r5.getParcelable(r1)
            android.content.Intent r5 = (android.content.Intent) r5
            com.google.android.gms.internal.auth.zzby r1 = com.google.android.gms.internal.auth.zzby.zza(r0)
            com.google.android.gms.internal.auth.zzby r2 = com.google.android.gms.internal.auth.zzby.SUCCESS
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L2a
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            return r5
        L2a:
            boolean r2 = com.google.android.gms.internal.auth.zzby.zzb(r1)
            if (r2 == 0) goto L4a
            com.google.android.gms.common.logging.Logger r2 = com.google.android.gms.auth.zzl.b()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r3 = "isUserRecoverableError status: "
            java.lang.String r1 = r3.concat(r1)
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r2.w(r1, r3)
            com.google.android.gms.auth.UserRecoverableAuthException r1 = new com.google.android.gms.auth.UserRecoverableAuthException
            r1.<init>(r0, r5)
            throw r1
        L4a:
            com.google.android.gms.auth.GoogleAuthException r5 = new com.google.android.gms.auth.GoogleAuthException
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.c.zza(android.os.IBinder):java.lang.Object");
    }
}
