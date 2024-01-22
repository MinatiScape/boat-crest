package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class zzdi {
    @CheckForNull
    public static Object a(Map map, @CheckForNull Object obj) {
        Objects.requireNonNull(map);
        try {
            return map.get(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    public static boolean b(Map map, @CheckForNull Object obj) {
        Objects.requireNonNull(map);
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }
}
