package com.airbnb.lottie;

import androidx.annotation.Nullable;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class LottieResult<V> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final V f1984a;
    @Nullable
    public final Throwable b;

    public LottieResult(V v) {
        this.f1984a = v;
        this.b = null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LottieResult) {
            LottieResult lottieResult = (LottieResult) obj;
            if (getValue() == null || !getValue().equals(lottieResult.getValue())) {
                if (getException() == null || lottieResult.getException() == null) {
                    return false;
                }
                return getException().toString().equals(getException().toString());
            }
            return true;
        }
        return false;
    }

    @Nullable
    public Throwable getException() {
        return this.b;
    }

    @Nullable
    public V getValue() {
        return this.f1984a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{getValue(), getException()});
    }

    public LottieResult(Throwable th) {
        this.b = th;
        this.f1984a = null;
    }
}
