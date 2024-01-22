package com.mappls.sdk.category.utils;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes11.dex */
public final class c<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final d f12548a;
    @Nullable
    public final T b;
    @Nullable
    public final String c;

    /* loaded from: classes11.dex */
    public static final class a {
        @NotNull
        public static c a() {
            return new c(d.b, null, null);
        }

        @NotNull
        public static c a(@Nullable Object obj) {
            return new c(d.f12549a, obj, null);
        }

        @NotNull
        public static c a(@NotNull String msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            return new c(d.c, null, msg);
        }
    }

    public c(@NotNull d status, @Nullable T t, @Nullable String str) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.f12548a = status;
        this.b = t;
        this.c = str;
    }

    @Nullable
    public final T a() {
        return this.b;
    }

    @Nullable
    public final String b() {
        return this.c;
    }

    @NotNull
    public final d c() {
        return this.f12548a;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            return this.f12548a == cVar.f12548a && Intrinsics.areEqual(this.b, cVar.b) && Intrinsics.areEqual(this.c, cVar.c);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f12548a.hashCode() * 31;
        T t = this.b;
        int hashCode2 = (hashCode + (t == null ? 0 : t.hashCode())) * 31;
        String str = this.c;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    @NotNull
    public final String toString() {
        return "Resource(status=" + this.f12548a + ", data=" + this.b + ", message=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
