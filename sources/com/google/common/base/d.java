package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class d {
    public static d compile(String str) {
        return l.a(str);
    }

    public static boolean isPcreLike() {
        return l.g();
    }

    public abstract int flags();

    public abstract c matcher(CharSequence charSequence);

    public abstract String pattern();

    public abstract String toString();
}
