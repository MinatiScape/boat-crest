package com.google.android.recaptcha.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class zzee {
    public static boolean zza(Collection collection, Iterator it) {
        Objects.requireNonNull(it);
        boolean z = false;
        while (it.hasNext()) {
            z |= collection.add(it.next());
        }
        return z;
    }
}
