package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes10.dex */
public final class e0 {

    /* renamed from: a  reason: collision with root package name */
    public static final c0 f10976a = c();
    public static final c0 b = new d0();

    public static c0 a() {
        return f10976a;
    }

    public static c0 b() {
        return b;
    }

    public static c0 c() {
        try {
            return (c0) Class.forName("com.google.crypto.tink.shaded.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
