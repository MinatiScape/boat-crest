package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public final class d implements Iterator<zzap> {
    public int h = 0;
    public final /* synthetic */ zzat i;

    public d(zzat zzatVar) {
        this.i = zzatVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        String str;
        int i = this.h;
        str = this.i.h;
        return i < str.length();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ zzap next() {
        String str;
        int i = this.h;
        str = this.i.h;
        if (i < str.length()) {
            int i2 = this.h;
            this.h = i2 + 1;
            return new zzat(String.valueOf(i2));
        }
        throw new NoSuchElementException();
    }
}
