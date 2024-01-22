package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Iterator;
import java.util.Set;
/* loaded from: classes8.dex */
public final class zzaa {
    public static int a(Set set) {
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i += next != null ? next.hashCode() : 0;
        }
        return i;
    }
}
