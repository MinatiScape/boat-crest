package com.google.android.youtube.player.internal;

import android.text.TextUtils;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class ab {
    public static <T> T a(T t) {
        Objects.requireNonNull(t, "null reference");
        return t;
    }

    public static <T> T a(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static String a(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
        return str;
    }

    public static void a(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }
}
