package com.google.protobuf;
/* loaded from: classes11.dex */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f11742a = e();

    public static ExtensionRegistryLite a() {
        ExtensionRegistryLite c = c("newInstance");
        return c != null ? c : new ExtensionRegistryLite();
    }

    public static ExtensionRegistryLite b() {
        ExtensionRegistryLite c = c("getEmptyRegistry");
        return c != null ? c : ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
    }

    public static final ExtensionRegistryLite c(String str) {
        Class<?> cls = f11742a;
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
        Class<?> cls = f11742a;
        return cls != null && cls.isAssignableFrom(extensionRegistryLite.getClass());
    }

    public static Class<?> e() {
        try {
            ExtensionRegistry extensionRegistry = ExtensionRegistry.EMPTY_REGISTRY;
            return ExtensionRegistry.class;
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
