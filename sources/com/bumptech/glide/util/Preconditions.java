package com.bumptech.glide.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class Preconditions {
    public static void checkArgument(boolean z, @NonNull String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    @NonNull
    public static String checkNotEmpty(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Must not be null or empty");
        }
        return str;
    }

    @NonNull
    public static <T> T checkNotNull(@Nullable T t) {
        return (T) checkNotNull(t, "Argument must not be null");
    }

    @NonNull
    public static <T> T checkNotNull(@Nullable T t, @NonNull String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    @NonNull
    public static <T extends Collection<Y>, Y> T checkNotEmpty(@NonNull T t) {
        if (t.isEmpty()) {
            throw new IllegalArgumentException("Must not be empty.");
        }
        return t;
    }
}