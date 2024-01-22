package com.google.android.gms.internal.vision;

import android.util.Log;
/* loaded from: classes10.dex */
public final class i extends zzbe<Boolean> {
    public i(zzbk zzbkVar, String str, Boolean bool) {
        super(zzbkVar, str, bool, null);
    }

    @Override // com.google.android.gms.internal.vision.zzbe
    public final /* synthetic */ Boolean d(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzal.zzev.matcher(str).matches()) {
                return Boolean.TRUE;
            }
            if (zzal.zzew.matcher(str).matches()) {
                return Boolean.FALSE;
            }
        }
        String zzac = super.zzac();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zzac).length() + 28 + valueOf.length());
        sb.append("Invalid boolean value for ");
        sb.append(zzac);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
