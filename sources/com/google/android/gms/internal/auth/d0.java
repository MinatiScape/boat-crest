package com.google.android.gms.internal.auth;

import android.util.Base64;
import android.util.Log;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class d0 extends zzdc {
    public d0(zzcz zzczVar, String str, Object obj, boolean z, zzhu zzhuVar, byte[] bArr) {
        super(zzczVar, "getTokenRefactor__blocked_packages", obj, true, null);
    }

    @Override // com.google.android.gms.internal.auth.zzdc
    public final Object a(Object obj) {
        try {
            return zzhr.zzk(Base64.decode((String) obj, 3));
        } catch (IOException | IllegalArgumentException unused) {
            String zzc = super.zzc();
            Log.e("PhenotypeFlag", "Invalid byte[] value for " + zzc + ": " + ((String) obj));
            return null;
        }
    }
}
