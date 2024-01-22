package com.google.android.gms.internal.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.jspecify.nullness.NullMarked;
@NullMarked
/* loaded from: classes7.dex */
public final class zzx {

    /* renamed from: a  reason: collision with root package name */
    public final zzo f8631a;
    public final boolean b;
    public final m c;

    public zzx(m mVar, boolean z, zzo zzoVar, int i) {
        this.c = mVar;
        this.b = z;
        this.f8631a = zzoVar;
    }

    public static zzx zzc(zzo zzoVar) {
        return new zzx(new m(zzoVar), false, j.b, Integer.MAX_VALUE);
    }

    public final Iterator d(CharSequence charSequence) {
        return new l(this.c, this, charSequence);
    }

    public final zzx zzb() {
        return new zzx(this.c, true, this.f8631a, Integer.MAX_VALUE);
    }

    public final Iterable zzd(CharSequence charSequence) {
        return new n(this, charSequence);
    }

    public final List zzf(CharSequence charSequence) {
        Objects.requireNonNull(charSequence);
        Iterator d = d(charSequence);
        ArrayList arrayList = new ArrayList();
        while (d.hasNext()) {
            arrayList.add((String) d.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
