package com.mappls.sdk.nearby.plugin.util;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class d<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final e f13068a;
    @Nullable
    public final T b;
    @Nullable
    public final String c;

    /* loaded from: classes10.dex */
    public static final class a {
        @NotNull
        public static d a() {
            return new d(e.b, null, null);
        }

        @NotNull
        public static d a(@Nullable Object obj) {
            return new d(e.f13069a, obj, null);
        }

        @NotNull
        public static d a(@NotNull String msg, @Nullable ArrayList arrayList) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            return new d(e.c, arrayList, msg);
        }
    }

    public d(@NotNull e status, @Nullable T t, @Nullable String str) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.f13068a = status;
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
    public final e c() {
        return this.f13068a;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            return this.f13068a == dVar.f13068a && Intrinsics.areEqual(this.b, dVar.b) && Intrinsics.areEqual(this.c, dVar.c);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f13068a.hashCode() * 31;
        T t = this.b;
        int hashCode2 = (hashCode + (t == null ? 0 : t.hashCode())) * 31;
        String str = this.c;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    @NotNull
    public final String toString() {
        return "Resource(status=" + this.f13068a + ", data=" + this.b + ", message=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
