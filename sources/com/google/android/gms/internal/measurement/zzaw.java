package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public abstract class zzaw {

    /* renamed from: a  reason: collision with root package name */
    public final List<zzbl> f8941a = new ArrayList();

    public final zzap a(String str) {
        if (this.f8941a.contains(zzh.zze(str))) {
            String valueOf = String.valueOf(str);
            throw new UnsupportedOperationException(valueOf.length() != 0 ? "Command not implemented: ".concat(valueOf) : new String("Command not implemented: "));
        }
        throw new IllegalArgumentException("Command not supported");
    }

    public abstract zzap zza(String str, zzg zzgVar, List<zzap> list);
}
