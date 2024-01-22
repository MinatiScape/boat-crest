package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import java.nio.Buffer;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class c {
    public static void a(Buffer buffer) {
        buffer.clear();
    }

    public static void b(Buffer buffer) {
        buffer.flip();
    }

    public static void c(Buffer buffer, int i) {
        buffer.limit(i);
    }

    public static void d(Buffer buffer, int i) {
        buffer.position(i);
    }
}
