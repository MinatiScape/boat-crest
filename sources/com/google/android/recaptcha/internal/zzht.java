package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzht implements Iterator {
    private final Iterator zza;

    public zzht(Iterator it) {
        this.zza = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.zza.next();
        return entry.getValue() instanceof zzhu ? new zzhs(entry, null) : entry;
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.zza.remove();
    }
}
