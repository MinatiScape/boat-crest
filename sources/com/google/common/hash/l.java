package com.google.common.hash;

import com.google.common.annotations.GwtIncompatible;
import java.nio.Buffer;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class l {
    public static void a(Buffer buffer) {
        buffer.clear();
    }

    public static void b(Buffer buffer) {
        buffer.flip();
    }

    public static void c(Buffer buffer, int i) {
        buffer.position(i);
    }
}
