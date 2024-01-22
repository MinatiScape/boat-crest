package com.google.protobuf;
/* loaded from: classes11.dex */
public class q implements b0 {

    /* renamed from: a  reason: collision with root package name */
    public static final q f11750a = new q();

    public static q c() {
        return f11750a;
    }

    @Override // com.google.protobuf.b0
    public a0 a(Class<?> cls) {
        if (GeneratedMessageLite.class.isAssignableFrom(cls)) {
            try {
                return (a0) GeneratedMessageLite.getDefaultInstance(cls.asSubclass(GeneratedMessageLite.class)).buildMessageInfo();
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for " + cls.getName(), e);
            }
        }
        throw new IllegalArgumentException("Unsupported message type: " + cls.getName());
    }

    @Override // com.google.protobuf.b0
    public boolean b(Class<?> cls) {
        return GeneratedMessageLite.class.isAssignableFrom(cls);
    }
}
