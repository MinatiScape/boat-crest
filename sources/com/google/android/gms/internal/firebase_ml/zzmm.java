package com.google.android.gms.internal.firebase_ml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes7.dex */
public final class zzmm {

    /* renamed from: a  reason: collision with root package name */
    public final zzlw f8787a;
    public final a2 b;
    public final int c;

    public zzmm(a2 a2Var) {
        this(a2Var, false, r1.b, Integer.MAX_VALUE);
    }

    public static zzmm zza(zzlw zzlwVar) {
        zzml.checkNotNull(zzlwVar);
        return new zzmm(new z1(zzlwVar));
    }

    public zzmm(a2 a2Var, boolean z, zzlw zzlwVar, int i) {
        this.b = a2Var;
        this.f8787a = zzlwVar;
        this.c = Integer.MAX_VALUE;
    }

    public final List<String> zza(CharSequence charSequence) {
        zzml.checkNotNull(charSequence);
        Iterator<String> a2 = this.b.a(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (a2.hasNext()) {
            arrayList.add(a2.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
