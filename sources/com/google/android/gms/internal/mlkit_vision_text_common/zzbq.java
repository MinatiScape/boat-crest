package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class zzbq {
    public static void a(Iterator it) {
        Objects.requireNonNull(it);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
}
