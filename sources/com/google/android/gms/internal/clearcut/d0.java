package com.google.android.gms.internal.clearcut;

import com.google.protobuf.ExtensionRegistry;
/* loaded from: classes7.dex */
public final class d0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f8574a = a();

    public static Class<?> a() {
        try {
            ExtensionRegistry extensionRegistry = ExtensionRegistry.EMPTY_REGISTRY;
            return ExtensionRegistry.class;
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzbt b() {
        Class<?> cls = f8574a;
        if (cls != null) {
            try {
                return (zzbt) cls.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception unused) {
            }
        }
        return zzbt.b;
    }
}
