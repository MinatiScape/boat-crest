package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import com.google.protobuf.Extension;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public final class zzbt {
    public static final zzbt b;

    /* renamed from: a  reason: collision with root package name */
    public final Map<Object, zzcg.zzf<?, ?>> f8615a;

    static {
        a();
        b = new zzbt(true);
    }

    public zzbt() {
        new HashMap();
    }

    public zzbt(boolean z) {
        this.f8615a = Collections.emptyMap();
    }

    public static Class<?> a() {
        return Extension.class;
    }

    public static zzbt zzan() {
        return d0.b();
    }
}
