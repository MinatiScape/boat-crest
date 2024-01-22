package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzjl {
    public static volatile zzjl b;
    public static volatile zzjl c;
    public static final zzjl d = new zzjl(true);

    /* renamed from: a  reason: collision with root package name */
    public final Map<u2, zzjx<?, ?>> f8962a;

    public zzjl() {
        this.f8962a = new HashMap();
    }

    public static zzjl zza() {
        zzjl zzjlVar = b;
        if (zzjlVar == null) {
            synchronized (zzjl.class) {
                zzjlVar = b;
                if (zzjlVar == null) {
                    zzjlVar = d;
                    b = zzjlVar;
                }
            }
        }
        return zzjlVar;
    }

    public static zzjl zzb() {
        zzjl zzjlVar = c;
        if (zzjlVar != null) {
            return zzjlVar;
        }
        synchronized (zzjl.class) {
            zzjl zzjlVar2 = c;
            if (zzjlVar2 != null) {
                return zzjlVar2;
            }
            zzjl b2 = a3.b(zzjl.class);
            c = b2;
            return b2;
        }
    }

    public final <ContainingType extends zzlg> zzjx<ContainingType, ?> zzc(ContainingType containingtype, int i) {
        return (zzjx<ContainingType, ?>) this.f8962a.get(new u2(containingtype, i));
    }

    public zzjl(boolean z) {
        this.f8962a = Collections.emptyMap();
    }
}
