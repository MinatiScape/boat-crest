package com.google.android.gms.phenotype;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.phenotype.PhenotypeFlag;
/* loaded from: classes10.dex */
public final class l extends PhenotypeFlag<String> {
    public l(PhenotypeFlag.Factory factory, String str, String str2) {
        super(factory, str, str2, null);
    }

    @Override // com.google.android.gms.phenotype.PhenotypeFlag
    /* renamed from: j */
    public final String zza(SharedPreferences sharedPreferences) {
        try {
            return sharedPreferences.getString(this.b, null);
        } catch (ClassCastException e) {
            String valueOf = String.valueOf(this.b);
            Log.e("PhenotypeFlag", valueOf.length() != 0 ? "Invalid string value in SharedPreferences for ".concat(valueOf) : new String("Invalid string value in SharedPreferences for "), e);
            return null;
        }
    }

    @Override // com.google.android.gms.phenotype.PhenotypeFlag
    public final /* synthetic */ String zza(String str) {
        return str;
    }
}
