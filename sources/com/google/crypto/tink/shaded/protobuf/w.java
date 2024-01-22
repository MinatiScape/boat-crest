package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes10.dex */
public final class w {

    /* renamed from: a  reason: collision with root package name */
    public static final u f10998a = c();
    public static final u b = new v();

    public static u a() {
        return f10998a;
    }

    public static u b() {
        return b;
    }

    public static u c() {
        try {
            return (u) Class.forName("com.google.crypto.tink.shaded.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
