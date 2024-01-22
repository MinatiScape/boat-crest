package com.google.protobuf;

import java.io.IOException;
/* loaded from: classes11.dex */
public abstract class x0<T, B> {
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

    public final void l(B b, q0 q0Var) throws IOException {
        while (q0Var.A() != Integer.MAX_VALUE && m(b, q0Var)) {
        }
    }

    public final boolean m(B b, q0 q0Var) throws IOException {
        int tag = q0Var.getTag();
        int tagFieldNumber = WireFormat.getTagFieldNumber(tag);
        int tagWireType = WireFormat.getTagWireType(tag);
        if (tagWireType == 0) {
            e(b, tagFieldNumber, q0Var.G());
            return true;
        } else if (tagWireType == 1) {
            b(b, tagFieldNumber, q0Var.c());
            return true;
        } else if (tagWireType == 2) {
            d(b, tagFieldNumber, q0Var.p());
            return true;
        } else if (tagWireType != 3) {
            if (tagWireType != 4) {
                if (tagWireType == 5) {
                    a(b, tagFieldNumber, q0Var.v());
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            return false;
        } else {
            B n = n();
            int makeTag = WireFormat.makeTag(tagFieldNumber, 4);
            l(n, q0Var);
            if (makeTag == q0Var.getTag()) {
                c(b, tagFieldNumber, r(n));
                return true;
            }
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    public abstract B n();

    public abstract void o(Object obj, B b);

    public abstract void p(Object obj, T t);

    public abstract boolean q(q0 q0Var);

    public abstract T r(B b);

    public abstract void s(T t, Writer writer) throws IOException;

    public abstract void t(T t, Writer writer) throws IOException;
}
