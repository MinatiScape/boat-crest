package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes10.dex */
public final class t implements o0 {
    public static final y b = new a();

    /* renamed from: a  reason: collision with root package name */
    public final y f10993a;

    /* loaded from: classes10.dex */
    public class a implements y {
        @Override // com.google.crypto.tink.shaded.protobuf.y
        public x a(Class<?> cls) {
            throw new IllegalStateException("This should never be called.");
        }

        @Override // com.google.crypto.tink.shaded.protobuf.y
        public boolean b(Class<?> cls) {
            return false;
        }
    }

    /* loaded from: classes10.dex */
    public static class b implements y {

        /* renamed from: a  reason: collision with root package name */
        public y[] f10994a;

        public b(y... yVarArr) {
            this.f10994a = yVarArr;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.y
        public x a(Class<?> cls) {
            y[] yVarArr;
            for (y yVar : this.f10994a) {
                if (yVar.b(cls)) {
                    return yVar.a(cls);
                }
            }
            throw new UnsupportedOperationException("No factory is available for message type: " + cls.getName());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.y
        public boolean b(Class<?> cls) {
            for (y yVar : this.f10994a) {
                if (yVar.b(cls)) {
                    return true;
                }
            }
            return false;
        }
    }

    public t() {
        this(b());
    }

    public static y b() {
        return new b(o.c(), c());
    }

    public static y c() {
        try {
            return (y) Class.forName("com.google.crypto.tink.shaded.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return b;
        }
    }

    public static boolean d(x xVar) {
        return xVar.getSyntax() == ProtoSyntax.PROTO2;
    }

    public static <T> n0<T> e(Class<T> cls, x xVar) {
        if (GeneratedMessageLite.class.isAssignableFrom(cls)) {
            if (d(xVar)) {
                return a0.Q(cls, xVar, e0.b(), r.b(), p0.M(), m.b(), w.b());
            }
            return a0.Q(cls, xVar, e0.b(), r.b(), p0.M(), null, w.b());
        } else if (d(xVar)) {
            return a0.Q(cls, xVar, e0.a(), r.a(), p0.H(), m.a(), w.a());
        } else {
            return a0.Q(cls, xVar, e0.a(), r.a(), p0.I(), null, w.a());
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.o0
    public <T> n0<T> a(Class<T> cls) {
        p0.J(cls);
        x a2 = this.f10993a.a(cls);
        if (a2.a()) {
            if (GeneratedMessageLite.class.isAssignableFrom(cls)) {
                return b0.l(p0.M(), m.b(), a2.b());
            }
            return b0.l(p0.H(), m.a(), a2.b());
        }
        return e(cls, a2);
    }

    public t(y yVar) {
        this.f10993a = (y) Internal.b(yVar, "messageInfoFactory");
    }
}
