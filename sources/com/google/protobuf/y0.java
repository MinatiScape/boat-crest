package com.google.protobuf;

import java.io.IOException;
/* loaded from: classes11.dex */
public class y0 extends x0<UnknownFieldSetLite, UnknownFieldSetLite> {
    @Override // com.google.protobuf.x0
    /* renamed from: A */
    public UnknownFieldSetLite g(Object obj) {
        return ((GeneratedMessageLite) obj).unknownFields;
    }

    @Override // com.google.protobuf.x0
    /* renamed from: B */
    public int h(UnknownFieldSetLite unknownFieldSetLite) {
        return unknownFieldSetLite.getSerializedSize();
    }

    @Override // com.google.protobuf.x0
    /* renamed from: C */
    public int i(UnknownFieldSetLite unknownFieldSetLite) {
        return unknownFieldSetLite.getSerializedSizeAsMessageSet();
    }

    @Override // com.google.protobuf.x0
    /* renamed from: D */
    public UnknownFieldSetLite k(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
        return unknownFieldSetLite2.equals(UnknownFieldSetLite.getDefaultInstance()) ? unknownFieldSetLite : UnknownFieldSetLite.mutableCopyOf(unknownFieldSetLite, unknownFieldSetLite2);
    }

    @Override // com.google.protobuf.x0
    /* renamed from: E */
    public UnknownFieldSetLite n() {
        return UnknownFieldSetLite.newInstance();
    }

    @Override // com.google.protobuf.x0
    /* renamed from: F */
    public void o(Object obj, UnknownFieldSetLite unknownFieldSetLite) {
        p(obj, unknownFieldSetLite);
    }

    @Override // com.google.protobuf.x0
    /* renamed from: G */
    public void p(Object obj, UnknownFieldSetLite unknownFieldSetLite) {
        ((GeneratedMessageLite) obj).unknownFields = unknownFieldSetLite;
    }

    @Override // com.google.protobuf.x0
    /* renamed from: H */
    public UnknownFieldSetLite r(UnknownFieldSetLite unknownFieldSetLite) {
        unknownFieldSetLite.makeImmutable();
        return unknownFieldSetLite;
    }

    @Override // com.google.protobuf.x0
    /* renamed from: I */
    public void s(UnknownFieldSetLite unknownFieldSetLite, Writer writer) throws IOException {
        unknownFieldSetLite.writeAsMessageSetTo(writer);
    }

    @Override // com.google.protobuf.x0
    /* renamed from: J */
    public void t(UnknownFieldSetLite unknownFieldSetLite, Writer writer) throws IOException {
        unknownFieldSetLite.writeTo(writer);
    }

    @Override // com.google.protobuf.x0
    public void j(Object obj) {
        g(obj).makeImmutable();
    }

    @Override // com.google.protobuf.x0
    public boolean q(q0 q0Var) {
        return false;
    }

    @Override // com.google.protobuf.x0
    /* renamed from: u */
    public void a(UnknownFieldSetLite unknownFieldSetLite, int i, int i2) {
        unknownFieldSetLite.storeField(WireFormat.makeTag(i, 5), Integer.valueOf(i2));
    }

    @Override // com.google.protobuf.x0
    /* renamed from: v */
    public void b(UnknownFieldSetLite unknownFieldSetLite, int i, long j) {
        unknownFieldSetLite.storeField(WireFormat.makeTag(i, 1), Long.valueOf(j));
    }

    @Override // com.google.protobuf.x0
    /* renamed from: w */
    public void c(UnknownFieldSetLite unknownFieldSetLite, int i, UnknownFieldSetLite unknownFieldSetLite2) {
        unknownFieldSetLite.storeField(WireFormat.makeTag(i, 3), unknownFieldSetLite2);
    }

    @Override // com.google.protobuf.x0
    /* renamed from: x */
    public void d(UnknownFieldSetLite unknownFieldSetLite, int i, ByteString byteString) {
        unknownFieldSetLite.storeField(WireFormat.makeTag(i, 2), byteString);
    }

    @Override // com.google.protobuf.x0
    /* renamed from: y */
    public void e(UnknownFieldSetLite unknownFieldSetLite, int i, long j) {
        unknownFieldSetLite.storeField(WireFormat.makeTag(i, 0), Long.valueOf(j));
    }

    @Override // com.google.protobuf.x0
    /* renamed from: z */
    public UnknownFieldSetLite f(Object obj) {
        UnknownFieldSetLite g = g(obj);
        if (g == UnknownFieldSetLite.getDefaultInstance()) {
            UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
            p(obj, newInstance);
            return newInstance;
        }
        return g;
    }
}
