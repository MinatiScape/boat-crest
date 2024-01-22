package com.google.protobuf;

import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
/* loaded from: classes11.dex */
public class z0 extends x0<UnknownFieldSet, UnknownFieldSet.Builder> {
    @Override // com.google.protobuf.x0
    /* renamed from: A */
    public UnknownFieldSet g(Object obj) {
        return ((GeneratedMessageV3) obj).unknownFields;
    }

    @Override // com.google.protobuf.x0
    /* renamed from: B */
    public int h(UnknownFieldSet unknownFieldSet) {
        return unknownFieldSet.getSerializedSize();
    }

    @Override // com.google.protobuf.x0
    /* renamed from: C */
    public int i(UnknownFieldSet unknownFieldSet) {
        return unknownFieldSet.getSerializedSizeAsMessageSet();
    }

    @Override // com.google.protobuf.x0
    /* renamed from: D */
    public UnknownFieldSet k(UnknownFieldSet unknownFieldSet, UnknownFieldSet unknownFieldSet2) {
        return unknownFieldSet.toBuilder().mergeFrom(unknownFieldSet2).build();
    }

    @Override // com.google.protobuf.x0
    /* renamed from: E */
    public UnknownFieldSet.Builder n() {
        return UnknownFieldSet.newBuilder();
    }

    @Override // com.google.protobuf.x0
    /* renamed from: F */
    public void o(Object obj, UnknownFieldSet.Builder builder) {
        ((GeneratedMessageV3) obj).unknownFields = builder.build();
    }

    @Override // com.google.protobuf.x0
    /* renamed from: G */
    public void p(Object obj, UnknownFieldSet unknownFieldSet) {
        ((GeneratedMessageV3) obj).unknownFields = unknownFieldSet;
    }

    @Override // com.google.protobuf.x0
    /* renamed from: H */
    public UnknownFieldSet r(UnknownFieldSet.Builder builder) {
        return builder.build();
    }

    @Override // com.google.protobuf.x0
    /* renamed from: I */
    public void s(UnknownFieldSet unknownFieldSet, Writer writer) throws IOException {
        unknownFieldSet.writeAsMessageSetTo(writer);
    }

    @Override // com.google.protobuf.x0
    /* renamed from: J */
    public void t(UnknownFieldSet unknownFieldSet, Writer writer) throws IOException {
        unknownFieldSet.writeTo(writer);
    }

    @Override // com.google.protobuf.x0
    public void j(Object obj) {
    }

    @Override // com.google.protobuf.x0
    public boolean q(q0 q0Var) {
        return q0Var.P();
    }

    @Override // com.google.protobuf.x0
    /* renamed from: u */
    public void a(UnknownFieldSet.Builder builder, int i, int i2) {
        builder.mergeField(i, UnknownFieldSet.Field.newBuilder().addFixed32(i2).build());
    }

    @Override // com.google.protobuf.x0
    /* renamed from: v */
    public void b(UnknownFieldSet.Builder builder, int i, long j) {
        builder.mergeField(i, UnknownFieldSet.Field.newBuilder().addFixed64(j).build());
    }

    @Override // com.google.protobuf.x0
    /* renamed from: w */
    public void c(UnknownFieldSet.Builder builder, int i, UnknownFieldSet unknownFieldSet) {
        builder.mergeField(i, UnknownFieldSet.Field.newBuilder().addGroup(unknownFieldSet).build());
    }

    @Override // com.google.protobuf.x0
    /* renamed from: x */
    public void d(UnknownFieldSet.Builder builder, int i, ByteString byteString) {
        builder.mergeField(i, UnknownFieldSet.Field.newBuilder().addLengthDelimited(byteString).build());
    }

    @Override // com.google.protobuf.x0
    /* renamed from: y */
    public void e(UnknownFieldSet.Builder builder, int i, long j) {
        builder.mergeField(i, UnknownFieldSet.Field.newBuilder().addVarint(j).build());
    }

    @Override // com.google.protobuf.x0
    /* renamed from: z */
    public UnknownFieldSet.Builder f(Object obj) {
        return ((GeneratedMessageV3) obj).unknownFields.toBuilder();
    }
}
