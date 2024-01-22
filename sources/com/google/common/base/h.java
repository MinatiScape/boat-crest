package com.google.common.base;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
/* loaded from: classes10.dex */
public final class h {
    public static /* synthetic */ void b() {
    }

    @CanIgnoreReturnValue
    public static String c() {
        new Runnable() { // from class: com.google.common.base.g
            @Override // java.lang.Runnable
            public final void run() {
                h.b();
            }
        }.run();
        return "";
    }
}
