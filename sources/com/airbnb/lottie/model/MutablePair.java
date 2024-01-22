package com.airbnb.lottie.model;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Pair;
import com.goodix.ble.libcomx.util.HexStringBuilder;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class MutablePair<T> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public T f2029a;
    @Nullable
    public T b;

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            return a(pair.first, this.f2029a) && a(pair.second, this.b);
        }
        return false;
    }

    public int hashCode() {
        T t = this.f2029a;
        int hashCode = t == null ? 0 : t.hashCode();
        T t2 = this.b;
        return hashCode ^ (t2 != null ? t2.hashCode() : 0);
    }

    public void set(T t, T t2) {
        this.f2029a = t;
        this.b = t2;
    }

    public String toString() {
        return "Pair{" + this.f2029a + HexStringBuilder.DEFAULT_SEPARATOR + this.b + "}";
    }
}
