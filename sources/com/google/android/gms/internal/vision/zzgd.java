package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class zzgd {
    public static volatile zzgd b;
    public static volatile zzgd c;
    public static final zzgd d = new zzgd(true);

    /* renamed from: a  reason: collision with root package name */
    public final Map<a, zzgs.zzg<?, ?>> f10020a;

    /* loaded from: classes10.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final Object f10021a;
        public final int b;

        public a(Object obj, int i) {
            this.f10021a = obj;
            this.b = i;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return this.f10021a == aVar.f10021a && this.b == aVar.b;
            }
            return false;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.f10021a) * 65535) + this.b;
        }
    }

    public zzgd() {
        this.f10020a = new HashMap();
    }

    public static zzgd zzfk() {
        return new zzgd();
    }

    public static zzgd zzfl() {
        zzgd zzgdVar = b;
        if (zzgdVar == null) {
            synchronized (zzgd.class) {
                zzgdVar = b;
                if (zzgdVar == null) {
                    zzgdVar = d;
                    b = zzgdVar;
                }
            }
        }
        return zzgdVar;
    }

    public static zzgd zzfm() {
        zzgd zzgdVar = c;
        if (zzgdVar != null) {
            return zzgdVar;
        }
        synchronized (zzgd.class) {
            zzgd zzgdVar2 = c;
            if (zzgdVar2 != null) {
                return zzgdVar2;
            }
            zzgd a2 = l2.a(zzgd.class);
            c = a2;
            return a2;
        }
    }

    public final <ContainingType extends zzic> zzgs.zzg<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzgs.zzg<ContainingType, ?>) this.f10020a.get(new a(containingtype, i));
    }

    public zzgd(boolean z) {
        this.f10020a = Collections.emptyMap();
    }

    public final void zza(zzgs.zzg<?, ?> zzgVar) {
        this.f10020a.put(new a(zzgVar.f10023a, zzgVar.d.i), zzgVar);
    }
}
