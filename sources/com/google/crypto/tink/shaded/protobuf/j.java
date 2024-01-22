package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes10.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f10983a = e();

    public static ExtensionRegistryLite a() {
        ExtensionRegistryLite c = c("newInstance");
        return c != null ? c : new ExtensionRegistryLite();
    }

    public static ExtensionRegistryLite b() {
        ExtensionRegistryLite c = c("getEmptyRegistry");
        return c != null ? c : ExtensionRegistryLite.e;
    }

    public static final ExtensionRegistryLite c(String str) {
        Class<?> cls = f10983a;
        if (cls == null) {
            return null;
        }
        try {
            return (ExtensionRegistryLite) cls.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean d(ExtensionRegistryLite extensionRegistryLite) {
        Class<?> cls = f10983a;
        return cls != null && cls.isAssignableFrom(extensionRegistryLite.getClass());
    }

    public static Class<?> e() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
