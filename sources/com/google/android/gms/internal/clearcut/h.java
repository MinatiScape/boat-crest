package com.google.android.gms.internal.clearcut;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes7.dex */
public final class h<T> extends zzae<T> {
    public final Object k;
    public String l;
    public T m;
    public final /* synthetic */ zzan n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(zzao zzaoVar, String str, Object obj, zzan zzanVar) {
        super(zzaoVar, str, obj, null);
        this.n = zzanVar;
        this.k = new Object();
    }

    @Override // com.google.android.gms.internal.clearcut.zzae
    public final T zza(SharedPreferences sharedPreferences) {
        try {
            return zzb(sharedPreferences.getString(this.b, ""));
        } catch (ClassCastException e) {
            String valueOf = String.valueOf(this.b);
            Log.e("PhenotypeFlag", valueOf.length() != 0 ? "Invalid byte[] value in SharedPreferences for ".concat(valueOf) : new String("Invalid byte[] value in SharedPreferences for "), e);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzae
    public final T zzb(String str) {
        T t;
        try {
            synchronized (this.k) {
                if (!str.equals(this.l)) {
                    this.l = str;
                    this.m = (T) this.n.zzb(Base64.decode(str, 3));
                }
                t = this.m;
            }
            return t;
        } catch (IOException | IllegalArgumentException unused) {
            String str2 = this.b;
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 27 + String.valueOf(str).length());
            sb.append("Invalid byte[] value for ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
