package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.LazyField;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
public final class b0<T> implements n0<T> {

    /* renamed from: a  reason: collision with root package name */
    public final MessageLite f10971a;
    public final s0<?, ?> b;
    public final boolean c;
    public final k<?> d;

    public b0(s0<?, ?> s0Var, k<?> kVar, MessageLite messageLite) {
        this.b = s0Var;
        this.c = kVar.e(messageLite);
        this.d = kVar;
        this.f10971a = messageLite;
    }

    public static <T> b0<T> l(s0<?, ?> s0Var, k<?> kVar, MessageLite messageLite) {
        return new b0<>(s0Var, kVar, messageLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public int a(T t) {
        int hashCode = this.b.g(t).hashCode();
        return this.c ? (hashCode * 53) + this.d.c(t).hashCode() : hashCode;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public boolean b(T t, T t2) {
        if (this.b.g(t).equals(this.b.g(t2))) {
            if (this.c) {
                return this.d.c(t).equals(this.d.c(t2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public void c(T t, T t2) {
        p0.G(this.b, t, t2);
        if (this.c) {
            p0.E(this.d, t, t2);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public void d(T t) {
        this.b.j(t);
        this.d.f(t);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public final boolean e(T t) {
        return this.d.c(t).t();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public int f(T t) {
        int j = j(this.b, t) + 0;
        return this.c ? j + this.d.c(t).k() : j;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00cb A[EDGE_INSN: B:57:0x00cb->B:34:0x00cb ?: BREAK  , SYNTHETIC] */
    @Override // com.google.crypto.tink.shaded.protobuf.n0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void g(T r11, byte[] r12, int r13, int r14, com.google.crypto.tink.shaded.protobuf.c.b r15) throws java.io.IOException {
        /*
            r10 = this;
            r0 = r11
            com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite r0 = (com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite) r0
            com.google.crypto.tink.shaded.protobuf.UnknownFieldSetLite r1 = r0.unknownFields
            com.google.crypto.tink.shaded.protobuf.UnknownFieldSetLite r2 = com.google.crypto.tink.shaded.protobuf.UnknownFieldSetLite.getDefaultInstance()
            if (r1 != r2) goto L11
            com.google.crypto.tink.shaded.protobuf.UnknownFieldSetLite r1 = com.google.crypto.tink.shaded.protobuf.UnknownFieldSetLite.l()
            r0.unknownFields = r1
        L11:
            com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$ExtendableMessage r11 = (com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.ExtendableMessage) r11
            com.google.crypto.tink.shaded.protobuf.FieldSet r11 = r11.r()
            r0 = 0
            r2 = r0
        L19:
            if (r13 >= r14) goto Ld7
            int r4 = com.google.crypto.tink.shaded.protobuf.c.I(r12, r13, r15)
            int r13 = r15.f10973a
            int r3 = com.google.crypto.tink.shaded.protobuf.WireFormat.f10966a
            r5 = 2
            if (r13 == r3) goto L6b
            int r3 = com.google.crypto.tink.shaded.protobuf.WireFormat.getTagWireType(r13)
            if (r3 != r5) goto L66
            com.google.crypto.tink.shaded.protobuf.k<?> r2 = r10.d
            com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite r3 = r15.d
            com.google.crypto.tink.shaded.protobuf.MessageLite r5 = r10.f10971a
            int r6 = com.google.crypto.tink.shaded.protobuf.WireFormat.getTagFieldNumber(r13)
            java.lang.Object r2 = r2.b(r3, r5, r6)
            r8 = r2
            com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$GeneratedExtension r8 = (com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.GeneratedExtension) r8
            if (r8 == 0) goto L5b
            com.google.crypto.tink.shaded.protobuf.i0 r13 = com.google.crypto.tink.shaded.protobuf.i0.a()
            com.google.crypto.tink.shaded.protobuf.MessageLite r2 = r8.getMessageDefaultInstance()
            java.lang.Class r2 = r2.getClass()
            com.google.crypto.tink.shaded.protobuf.n0 r13 = r13.d(r2)
            int r13 = com.google.crypto.tink.shaded.protobuf.c.p(r13, r12, r4, r14, r15)
            com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$b r2 = r8.d
            java.lang.Object r3 = r15.c
            r11.C(r2, r3)
            goto L64
        L5b:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.crypto.tink.shaded.protobuf.c.G(r2, r3, r4, r5, r6, r7)
        L64:
            r2 = r8
            goto L19
        L66:
            int r13 = com.google.crypto.tink.shaded.protobuf.c.N(r13, r12, r4, r14, r15)
            goto L19
        L6b:
            r13 = 0
            r3 = r0
        L6d:
            if (r4 >= r14) goto Lcb
            int r4 = com.google.crypto.tink.shaded.protobuf.c.I(r12, r4, r15)
            int r6 = r15.f10973a
            int r7 = com.google.crypto.tink.shaded.protobuf.WireFormat.getTagFieldNumber(r6)
            int r8 = com.google.crypto.tink.shaded.protobuf.WireFormat.getTagWireType(r6)
            if (r7 == r5) goto Lac
            r9 = 3
            if (r7 == r9) goto L83
            goto Lc1
        L83:
            if (r2 == 0) goto La1
            com.google.crypto.tink.shaded.protobuf.i0 r6 = com.google.crypto.tink.shaded.protobuf.i0.a()
            com.google.crypto.tink.shaded.protobuf.MessageLite r7 = r2.getMessageDefaultInstance()
            java.lang.Class r7 = r7.getClass()
            com.google.crypto.tink.shaded.protobuf.n0 r6 = r6.d(r7)
            int r4 = com.google.crypto.tink.shaded.protobuf.c.p(r6, r12, r4, r14, r15)
            com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$b r6 = r2.d
            java.lang.Object r7 = r15.c
            r11.C(r6, r7)
            goto L6d
        La1:
            if (r8 != r5) goto Lc1
            int r4 = com.google.crypto.tink.shaded.protobuf.c.b(r12, r4, r15)
            java.lang.Object r3 = r15.c
            com.google.crypto.tink.shaded.protobuf.ByteString r3 = (com.google.crypto.tink.shaded.protobuf.ByteString) r3
            goto L6d
        Lac:
            if (r8 != 0) goto Lc1
            int r4 = com.google.crypto.tink.shaded.protobuf.c.I(r12, r4, r15)
            int r13 = r15.f10973a
            com.google.crypto.tink.shaded.protobuf.k<?> r2 = r10.d
            com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite r6 = r15.d
            com.google.crypto.tink.shaded.protobuf.MessageLite r7 = r10.f10971a
            java.lang.Object r2 = r2.b(r6, r7, r13)
            com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite$GeneratedExtension r2 = (com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.GeneratedExtension) r2
            goto L6d
        Lc1:
            int r7 = com.google.crypto.tink.shaded.protobuf.WireFormat.b
            if (r6 != r7) goto Lc6
            goto Lcb
        Lc6:
            int r4 = com.google.crypto.tink.shaded.protobuf.c.N(r6, r12, r4, r14, r15)
            goto L6d
        Lcb:
            if (r3 == 0) goto Ld4
            int r13 = com.google.crypto.tink.shaded.protobuf.WireFormat.a(r13, r5)
            r1.n(r13, r3)
        Ld4:
            r13 = r4
            goto L19
        Ld7:
            if (r13 != r14) goto Lda
            return
        Lda:
            com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException r11 = com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.parseFailure()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.b0.g(java.lang.Object, byte[], int, int, com.google.crypto.tink.shaded.protobuf.c$b):void");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public void h(T t, l0 l0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        k(this.b, this.d, t, l0Var, extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public void i(T t, Writer writer) throws IOException {
        Iterator<Map.Entry<?, Object>> w = this.d.c(t).w();
        while (w.hasNext()) {
            Map.Entry<?, Object> next = w.next();
            FieldSet.FieldDescriptorLite fieldDescriptorLite = (FieldSet.FieldDescriptorLite) next.getKey();
            if (fieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !fieldDescriptorLite.isRepeated() && !fieldDescriptorLite.isPacked()) {
                if (next instanceof LazyField.b) {
                    writer.b(fieldDescriptorLite.getNumber(), ((LazyField.b) next).a().toByteString());
                } else {
                    writer.b(fieldDescriptorLite.getNumber(), next.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        n(this.b, t, writer);
    }

    public final <UT, UB> int j(s0<UT, UB> s0Var, T t) {
        return s0Var.i(s0Var.g(t));
    }

    public final <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void k(s0<UT, UB> s0Var, k<ET> kVar, T t, l0 l0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        UB f = s0Var.f(t);
        FieldSet<ET> d = kVar.d(t);
        do {
            try {
                if (l0Var.A() == Integer.MAX_VALUE) {
                    return;
                }
            } finally {
                s0Var.o(t, f);
            }
        } while (m(l0Var, extensionRegistryLite, kVar, d, s0Var, f));
    }

    public final <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> boolean m(l0 l0Var, ExtensionRegistryLite extensionRegistryLite, k<ET> kVar, FieldSet<ET> fieldSet, s0<UT, UB> s0Var, UB ub) throws IOException {
        int tag = l0Var.getTag();
        if (tag != WireFormat.f10966a) {
            if (WireFormat.getTagWireType(tag) == 2) {
                Object b = kVar.b(extensionRegistryLite, this.f10971a, WireFormat.getTagFieldNumber(tag));
                if (b != null) {
                    kVar.h(l0Var, b, extensionRegistryLite, fieldSet);
                    return true;
                }
                return s0Var.m(ub, l0Var);
            }
            return l0Var.C();
        }
        int i = 0;
        Object obj = null;
        ByteString byteString = null;
        while (l0Var.A() != Integer.MAX_VALUE) {
            int tag2 = l0Var.getTag();
            if (tag2 == WireFormat.c) {
                i = l0Var.i();
                obj = kVar.b(extensionRegistryLite, this.f10971a, i);
            } else if (tag2 == WireFormat.d) {
                if (obj != null) {
                    kVar.h(l0Var, obj, extensionRegistryLite, fieldSet);
                } else {
                    byteString = l0Var.p();
                }
            } else if (!l0Var.C()) {
                break;
            }
        }
        if (l0Var.getTag() == WireFormat.b) {
            if (byteString != null) {
                if (obj != null) {
                    kVar.i(byteString, obj, extensionRegistryLite, fieldSet);
                } else {
                    s0Var.d(ub, i, byteString);
                }
            }
            return true;
        }
        throw InvalidProtocolBufferException.invalidEndTag();
    }

    public final <UT, UB> void n(s0<UT, UB> s0Var, T t, Writer writer) throws IOException {
        s0Var.s(s0Var.g(t), writer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.n0
    public T newInstance() {
        return (T) this.f10971a.newBuilderForType().buildPartial();
    }
}
