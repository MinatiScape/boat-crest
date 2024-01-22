package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
/* loaded from: classes10.dex */
public abstract class s0<T, B> {
    public abstract void a(B b, int i, int i2);

    public abstract void b(B b, int i, long j);

    public abstract void c(B b, int i, T t);

    public abstract void d(B b, int i, ByteString byteString);

    public abstract void e(B b, int i, long j);

    public abstract B f(Object obj);

    public abstract T g(Object obj);

    public abstract int h(T t);

    public abstract int i(T t);

    public abstract void j(Object obj);

    public abstract T k(T t, T t2);

    public final void l(B b, l0 l0Var) throws IOException {
        while (l0Var.A() != Integer.MAX_VALUE && m(b, l0Var)) {
        }
    }

    public final boolean m(B b, l0 l0Var) throws IOException {
        int tag = l0Var.getTag();
        int tagFieldNumber = WireFormat.getTagFieldNumber(tag);
        int tagWireType = WireFormat.getTagWireType(tag);
        if (tagWireType == 0) {
            e(b, tagFieldNumber, l0Var.G());
            return true;
        } else if (tagWireType == 1) {
            b(b, tagFieldNumber, l0Var.c());
            return true;
        } else if (tagWireType == 2) {
            d(b, tagFieldNumber, l0Var.p());
            return true;
        } else if (tagWireType != 3) {
            if (tagWireType != 4) {
                if (tagWireType == 5) {
                    a(b, tagFieldNumber, l0Var.v());
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            return false;
        } else {
            B n = n();
            int a2 = WireFormat.a(tagFieldNumber, 4);
            l(n, l0Var);
            if (a2 == l0Var.getTag()) {
                c(b, tagFieldNumber, r(n));
                return true;
            }
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    public abstract B n();

    public abstract void o(Object obj, B b);

    public abstract void p(Object obj, T t);

    public abstract boolean q(l0 l0Var);

    public abstract T r(B b);

    public abstract void s(T t, Writer writer) throws IOException;

    public abstract void t(T t, Writer writer) throws IOException;
}
