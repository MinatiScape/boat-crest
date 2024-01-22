package com.google.android.datatransport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class Encoding {

    /* renamed from: a  reason: collision with root package name */
    public final String f8051a;

    public Encoding(@NonNull String str) {
        Objects.requireNonNull(str, "name is null");
        this.f8051a = str;
    }

    public static Encoding of(@NonNull String str) {
        return new Encoding(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Encoding) {
            return this.f8051a.equals(((Encoding) obj).f8051a);
        }
        return false;
    }

    public String getName() {
        return this.f8051a;
    }

    public int hashCode() {
        return this.f8051a.hashCode() ^ 1000003;
    }

    @NonNull
    public String toString() {
        return "Encoding{name=\"" + this.f8051a + "\"}";
    }
}
