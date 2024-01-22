package com.mappls.sdk.plugins.places.common.utils;

import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public final class d<T> {

    /* renamed from: a  reason: collision with root package name */
    public int f13143a;
    public T b;
    public String c;

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/Object;TT;Ljava/lang/String;)V */
    /* JADX WARN: Multi-variable type inference failed */
    public d(int i, Object obj, String str) {
        this.f13143a = i;
        this.b = obj;
        this.c = str;
    }

    public static d a() {
        return new d(2, null, null);
    }

    public static <T> d<T> a(@Nullable T t) {
        return new d<>(1, t, null);
    }

    public static d a(String str) {
        return new d(3, null, str);
    }
}
