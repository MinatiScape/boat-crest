package com.blankj.utilcode.util;

import java.lang.reflect.Type;
/* loaded from: classes.dex */
public final class CloneUtils {
    public CloneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static <T> T deepClone(T t, Type type) {
        try {
            return (T) b.H(b.a1(t), type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
