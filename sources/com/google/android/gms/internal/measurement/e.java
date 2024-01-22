package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public final class e implements Iterator<zzap> {
    public int h = 0;
    public final /* synthetic */ zzat i;

    public e(zzat zzatVar) {
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
        String str2;
        int i = this.h;
        str = this.i.h;
        if (i < str.length()) {
            str2 = this.i.h;
            int i2 = this.h;
            this.h = i2 + 1;
            return new zzat(String.valueOf(str2.charAt(i2)));
        }
        throw new NoSuchElementException();
    }
}
