package com.google.android.gms.internal.vision;

import android.util.Base64;
import android.util.Log;
import java.io.IOException;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes10.dex */
public final class k<T> extends zzbe<T> {
    public final /* synthetic */ zzbh j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(zzbk zzbkVar, String str, Object obj, zzbh zzbhVar) {
        super(zzbkVar, str, obj, null);
        this.j = zzbhVar;
    }

    @Override // com.google.android.gms.internal.vision.zzbe
    public final T d(Object obj) {
        if (obj instanceof String) {
            try {
                return (T) this.j.zzb(Base64.decode((String) obj, 3));
            } catch (IOException | IllegalArgumentException unused) {
            }
        }
        String zzac = super.zzac();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zzac).length() + 27 + valueOf.length());
        sb.append("Invalid byte[] value for ");
        sb.append(zzac);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
