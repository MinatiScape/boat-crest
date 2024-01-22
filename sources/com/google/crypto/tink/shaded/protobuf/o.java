package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes10.dex */
public class o implements y {

    /* renamed from: a  reason: collision with root package name */
    public static final o f10988a = new o();

    public static o c() {
        return f10988a;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.y
    public x a(Class<?> cls) {
        if (GeneratedMessageLite.class.isAssignableFrom(cls)) {
            try {
                return (x) GeneratedMessageLite.k(cls.asSubclass(GeneratedMessageLite.class)).g();
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for " + cls.getName(), e);
            }
        }
        throw new IllegalArgumentException("Unsupported message type: " + cls.getName());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.y
    public boolean b(Class<?> cls) {
        return GeneratedMessageLite.class.isAssignableFrom(cls);
    }
}
