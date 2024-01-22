package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes10.dex */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    public static final k<?> f10986a = new l();
    public static final k<?> b = c();

    public static k<?> a() {
        k<?> kVar = b;
        if (kVar != null) {
            return kVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    public static k<?> b() {
        return f10986a;
    }

    public static k<?> c() {
        try {
            return (k) Class.forName("com.google.crypto.tink.shaded.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
