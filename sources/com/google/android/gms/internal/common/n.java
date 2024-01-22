package com.google.android.gms.internal.common;

import java.io.IOException;
import java.util.Iterator;
/* loaded from: classes7.dex */
public final class n implements Iterable {
    public final /* synthetic */ CharSequence h;
    public final /* synthetic */ zzx i;

    public n(zzx zzxVar, CharSequence charSequence) {
        this.i = zzxVar;
        this.h = charSequence;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        Iterator d;
        d = this.i.d(this.h);
        return d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Iterator it = iterator();
        try {
            if (it.hasNext()) {
                sb.append(zzq.a(it.next(), ", "));
                while (it.hasNext()) {
                    sb.append((CharSequence) ", ");
                    sb.append(zzq.a(it.next(), ", "));
                }
            }
            sb.append(']');
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
