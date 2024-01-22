package com.crrepa.i0;
/* loaded from: classes9.dex */
public class l {
    public static <T> T a(String str, Class<T> cls) {
        try {
            return (T) new com.crrepa.n0.f().a(str, (Class<Object>) cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
