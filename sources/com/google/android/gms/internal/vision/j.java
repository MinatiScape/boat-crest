package com.google.android.gms.internal.vision;

import android.util.Log;
/* loaded from: classes10.dex */
public final class j extends zzbe<Long> {
    public j(zzbk zzbkVar, String str, Long l) {
        super(zzbkVar, str, l, null);
    }

    @Override // com.google.android.gms.internal.vision.zzbe
    /* renamed from: m */
    public final Long d(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf(Long.parseLong((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        String zzac = super.zzac();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zzac).length() + 25 + valueOf.length());
        sb.append("Invalid long value for ");
        sb.append(zzac);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
