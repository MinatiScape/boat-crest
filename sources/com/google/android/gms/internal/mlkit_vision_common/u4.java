package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Objects;
/* loaded from: classes8.dex */
public final class u4 {
    public static void a(Object obj, Object obj2) {
        if (obj == null) {
            Objects.toString(obj2);
            throw new NullPointerException("null key in entry: null=".concat(String.valueOf(obj2)));
        } else if (obj2 != null) {
        } else {
            throw new NullPointerException("null value in entry: " + obj + "=null");
        }
    }
}
