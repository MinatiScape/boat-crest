package com.google.android.gms.internal.firebase_ml;

import com.clevertap.android.sdk.Constants;
import java.io.IOException;
/* loaded from: classes7.dex */
public class zzgk implements zzgj {

    /* renamed from: a  reason: collision with root package name */
    public final String f8762a;

    public zzgk() {
        this(null);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzgj
    public void zza(zzgg<?> zzggVar) throws IOException {
        String str = this.f8762a;
        if (str != null) {
            zzggVar.put(Constants.KEY_KEY, str);
        }
    }

    public zzgk(String str) {
        this(str, null);
    }

    public zzgk(String str, String str2) {
        this.f8762a = str;
    }
}
