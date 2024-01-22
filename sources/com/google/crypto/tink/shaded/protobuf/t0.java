package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
/* loaded from: classes10.dex */
public class t0 extends s0<UnknownFieldSetLite, UnknownFieldSetLite> {
    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: A */
    public UnknownFieldSetLite g(Object obj) {
        return ((GeneratedMessageLite) obj).unknownFields;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: B */
    public int h(UnknownFieldSetLite unknownFieldSetLite) {
        return unknownFieldSetLite.getSerializedSize();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: C */
    public int i(UnknownFieldSetLite unknownFieldSetLite) {
        return unknownFieldSetLite.getSerializedSizeAsMessageSet();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: D */
    public UnknownFieldSetLite k(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
        return unknownFieldSetLite2.equals(UnknownFieldSetLite.getDefaultInstance()) ? unknownFieldSetLite : UnknownFieldSetLite.k(unknownFieldSetLite, unknownFieldSetLite2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: E */
    public UnknownFieldSetLite n() {
        return UnknownFieldSetLite.l();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: F */
    public void o(Object obj, UnknownFieldSetLite unknownFieldSetLite) {
        p(obj, unknownFieldSetLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: G */
    public void p(Object obj, UnknownFieldSetLite unknownFieldSetLite) {
        ((GeneratedMessageLite) obj).unknownFields = unknownFieldSetLite;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: H */
    public UnknownFieldSetLite r(UnknownFieldSetLite unknownFieldSetLite) {
        unknownFieldSetLite.makeImmutable();
        return unknownFieldSetLite;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: I */
    public void s(UnknownFieldSetLite unknownFieldSetLite, Writer writer) throws IOException {
        unknownFieldSetLite.o(writer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: J */
    public void t(UnknownFieldSetLite unknownFieldSetLite, Writer writer) throws IOException {
        unknownFieldSetLite.writeTo(writer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    public void j(Object obj) {
        g(obj).makeImmutable();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    public boolean q(l0 l0Var) {
        return false;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: u */
    public void a(UnknownFieldSetLite unknownFieldSetLite, int i, int i2) {
        unknownFieldSetLite.n(WireFormat.a(i, 5), Integer.valueOf(i2));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: v */
    public void b(UnknownFieldSetLite unknownFieldSetLite, int i, long j) {
        unknownFieldSetLite.n(WireFormat.a(i, 1), Long.valueOf(j));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: w */
    public void c(UnknownFieldSetLite unknownFieldSetLite, int i, UnknownFieldSetLite unknownFieldSetLite2) {
        unknownFieldSetLite.n(WireFormat.a(i, 3), unknownFieldSetLite2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: x */
    public void d(UnknownFieldSetLite unknownFieldSetLite, int i, ByteString byteString) {
        unknownFieldSetLite.n(WireFormat.a(i, 2), byteString);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: y */
    public void e(UnknownFieldSetLite unknownFieldSetLite, int i, long j) {
        unknownFieldSetLite.n(WireFormat.a(i, 0), Long.valueOf(j));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.s0
    /* renamed from: z */
    public UnknownFieldSetLite f(Object obj) {
        UnknownFieldSetLite g = g(obj);
        if (g == UnknownFieldSetLite.getDefaultInstance()) {
            UnknownFieldSetLite l = UnknownFieldSetLite.l();
            p(obj, l);
            return l;
        }
        return g;
    }
}
