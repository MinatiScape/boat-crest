package com.google.android.gms.internal.clearcut;

import android.content.SharedPreferences;
import android.util.Log;
/* loaded from: classes7.dex */
public final class g extends zzae<String> {
    public g(zzao zzaoVar, String str, String str2) {
        super(zzaoVar, str, str2, null);
    }

    @Override // com.google.android.gms.internal.clearcut.zzae
    /* renamed from: n */
    public final String zza(SharedPreferences sharedPreferences) {
        try {
            return sharedPreferences.getString(this.b, null);
        } catch (ClassCastException e) {
            String valueOf = String.valueOf(this.b);
            Log.e("PhenotypeFlag", valueOf.length() != 0 ? "Invalid string value in SharedPreferences for ".concat(valueOf) : new String("Invalid string value in SharedPreferences for "), e);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzae
    public final /* synthetic */ String zzb(String str) {
        return str;
    }
}
