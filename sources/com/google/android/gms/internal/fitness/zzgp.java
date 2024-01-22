package com.google.android.gms.internal.fitness;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public class zzgp {
    public static volatile zzgp b;
    public static final zzgp c = new zzgp(true);

    /* renamed from: a  reason: collision with root package name */
    public final Map<Object, Object> f8862a;

    public zzgp() {
        new HashMap();
    }

    public static zzgp zzbf() {
        zzgp zzgpVar = b;
        if (zzgpVar == null) {
            synchronized (zzgp.class) {
                zzgpVar = b;
                if (zzgpVar == null) {
                    zzgpVar = c;
                    b = zzgpVar;
                }
            }
        }
        return zzgpVar;
    }

    public zzgp(boolean z) {
        this.f8862a = Collections.emptyMap();
    }
}
