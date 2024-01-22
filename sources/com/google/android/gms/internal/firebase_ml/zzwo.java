package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzwz;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public class zzwo {
    public static volatile zzwo b;
    public static final zzwo c = new zzwo(true);

    /* renamed from: a  reason: collision with root package name */
    public final Map<a, zzwz.zze<?, ?>> f8811a;

    /* loaded from: classes7.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final Object f8812a;
        public final int b;

        public a(Object obj, int i) {
            this.f8812a = obj;
            this.b = i;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return this.f8812a == aVar.f8812a && this.b == aVar.b;
            }
            return false;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.f8812a) * 65535) + this.b;
        }
    }

    public zzwo() {
        this.f8811a = new HashMap();
    }

    public static zzwo zzuc() {
        zzwo zzwoVar = b;
        if (zzwoVar == null) {
            synchronized (zzwo.class) {
                zzwoVar = b;
                if (zzwoVar == null) {
                    zzwoVar = c;
                    b = zzwoVar;
                }
            }
        }
        return zzwoVar;
    }

    public final <ContainingType extends zzyk> zzwz.zze<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzwz.zze<ContainingType, ?>) this.f8811a.get(new a(containingtype, i));
    }

    public zzwo(boolean z) {
        this.f8811a = Collections.emptyMap();
    }
}
