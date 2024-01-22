package com.mappls.sdk.direction.ui.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public final class a<T> {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final int f12583a;
    @Nullable
    public final String b;
    @Nullable
    public final T c;

    /* JADX WARN: Multi-variable type inference failed */
    public a(@NonNull int i, @Nullable Object obj, @Nullable String str) {
        this.f12583a = i;
        this.c = obj;
        this.b = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.f12583a != aVar.f12583a) {
            return false;
        }
        String str = this.b;
        if (str == null ? aVar.b == null : str.equals(aVar.b)) {
            T t = this.c;
            T t2 = aVar.c;
            return t != null ? t.equals(t2) : t2 == null;
        }
        return false;
    }

    public final int hashCode() {
        int a2 = c.a(this.f12583a) * 31;
        String str = this.b;
        int hashCode = (a2 + (str != null ? str.hashCode() : 0)) * 31;
        T t = this.c;
        return hashCode + (t != null ? t.hashCode() : 0);
    }

    public final String toString() {
        return "Resource{status=" + b.a(this.f12583a) + ", message='" + this.b + "', data=" + this.c + '}';
    }
}
