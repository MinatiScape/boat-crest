package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes7.dex */
public final class n0 {

    /* renamed from: a  reason: collision with root package name */
    public final zzis f8707a;
    public final StringBuilder b;
    public final zziv c;
    public final List<Type> d;

    public n0(zzgx zzgxVar, StringBuilder sb) {
        Class<?> cls = zzgxVar.getClass();
        this.d = Arrays.asList(cls);
        this.c = zziv.zza(cls, true);
        this.b = sb;
        this.f8707a = new zzis(zzgxVar);
    }
}
