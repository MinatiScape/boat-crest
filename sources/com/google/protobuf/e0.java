package com.google.protobuf;

import com.google.protobuf.FieldSet;
import com.google.protobuf.LazyField;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes11.dex */
public final class e0<T> implements s0<T> {

    /* renamed from: a  reason: collision with root package name */
    public final MessageLite f11727a;
    public final x0<?, ?> b;
    public final boolean c;
    public final l<?> d;

    public e0(x0<?, ?> x0Var, l<?> lVar, MessageLite messageLite) {
        this.b = x0Var;
        this.c = lVar.e(messageLite);
        this.d = lVar;
        this.f11727a = messageLite;
    }

    public static <T> e0<T> l(x0<?, ?> x0Var, l<?> lVar, MessageLite messageLite) {
        return new e0<>(x0Var, lVar, messageLite);
    }

    @Override // com.google.protobuf.s0
    public int a(T t) {
        int hashCode = this.b.g(t).hashCode();
        return this.c ? (hashCode * 53) + this.d.c(t).hashCode() : hashCode;
    }

    @Override // com.google.protobuf.s0
    public boolean b(T t, T t2) {
        if (this.b.g(t).equals(this.b.g(t2))) {
            if (this.c) {
                return this.d.c(t).equals(this.d.c(t2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.protobuf.s0
    public void c(T t, T t2) {
        u0.H(this.b, t, t2);
        if (this.c) {
            u0.F(this.d, t, t2);
        }
    }

    @Override // com.google.protobuf.s0
    public void d(T t) {
        this.b.j(t);
        this.d.f(t);
    }

    @Override // com.google.protobuf.s0
    public final boolean e(T t) {
        return this.d.c(t).E();
    }

    @Override // com.google.protobuf.s0
    public int f(T t) {
        int j = j(this.b, t) + 0;
        return this.c ? j + this.d.c(t).v() : j;
    }

    @Override // com.google.protobuf.s0
    public void g(T t, Writer writer) throws IOException {
        Iterator<Map.Entry<?, Object>> H = this.d.c(t).H();
        while (H.hasNext()) {
            Map.Entry<?, Object> next = H.next();
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

    @Override // com.google.protobuf.s0
    public void h(T t, q0 q0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        k(this.b, this.d, t, q0Var, extensionRegistryLite);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00cb A[EDGE_INSN: B:57:0x00cb->B:34:0x00cb ?: BREAK  , SYNTHETIC] */
    @Override // com.google.protobuf.s0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void i(T r11, byte[] r12, int r13, int r14, com.google.protobuf.c.b r15) throws java.io.IOException {
        /*
            r10 = this;
            r0 = r11
            com.google.protobuf.GeneratedMessageLite r0 = (com.google.protobuf.GeneratedMessageLite) r0
            com.google.protobuf.UnknownFieldSetLite r1 = r0.unknownFields
            com.google.protobuf.UnknownFieldSetLite r2 = com.google.protobuf.UnknownFieldSetLite.getDefaultInstance()
            if (r1 != r2) goto L11
            com.google.protobuf.UnknownFieldSetLite r1 = com.google.protobuf.UnknownFieldSetLite.newInstance()
            r0.unknownFields = r1
        L11:
            com.google.protobuf.GeneratedMessageLite$ExtendableMessage r11 = (com.google.protobuf.GeneratedMessageLite.ExtendableMessage) r11
            com.google.protobuf.FieldSet r11 = r11.ensureExtensionsAreMutable()
            r0 = 0
            r2 = r0
        L19:
            if (r13 >= r14) goto Ld7
            int r4 = com.google.protobuf.c.I(r12, r13, r15)
            int r13 = r15.f11721a
            int r3 = com.google.protobuf.WireFormat.MESSAGE_SET_ITEM_TAG
            r5 = 2
            if (r13 == r3) goto L6b
            int r3 = com.google.protobuf.WireFormat.getTagWireType(r13)
            if (r3 != r5) goto L66
            com.google.protobuf.l<?> r2 = r10.d
            com.google.protobuf.ExtensionRegistryLite r3 = r15.d
            com.google.protobuf.MessageLite r5 = r10.f11727a
            int r6 = com.google.protobuf.WireFormat.getTagFieldNumber(r13)
            java.lang.Object r2 = r2.b(r3, r5, r6)
            r8 = r2
            com.google.protobuf.GeneratedMessageLite$GeneratedExtension r8 = (com.google.protobuf.GeneratedMessageLite.GeneratedExtension) r8
            if (r8 == 0) goto L5b
            com.google.protobuf.n0 r13 = com.google.protobuf.n0.a()
            com.google.protobuf.MessageLite r2 = r8.getMessageDefaultInstance()
            java.lang.Class r2 = r2.getClass()
            com.google.protobuf.s0 r13 = r13.d(r2)
            int r13 = com.google.protobuf.c.p(r13, r12, r4, r14, r15)
            com.google.protobuf.GeneratedMessageLite$b r2 = r8.descriptor
            java.lang.Object r3 = r15.c
            r11.O(r2, r3)
            goto L64
        L5b:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.protobuf.c.G(r2, r3, r4, r5, r6, r7)
        L64:
            r2 = r8
            goto L19
        L66:
            int r13 = com.google.protobuf.c.N(r13, r12, r4, r14, r15)
            goto L19
        L6b:
            r13 = 0
            r3 = r0
        L6d:
            if (r4 >= r14) goto Lcb
            int r4 = com.google.protobuf.c.I(r12, r4, r15)
            int r6 = r15.f11721a
            int r7 = com.google.protobuf.WireFormat.getTagFieldNumber(r6)
            int r8 = com.google.protobuf.WireFormat.getTagWireType(r6)
            if (r7 == r5) goto Lac
            r9 = 3
            if (r7 == r9) goto L83
            goto Lc1
        L83:
            if (r2 == 0) goto La1
            com.google.protobuf.n0 r6 = com.google.protobuf.n0.a()
            com.google.protobuf.MessageLite r7 = r2.getMessageDefaultInstance()
            java.lang.Class r7 = r7.getClass()
            com.google.protobuf.s0 r6 = r6.d(r7)
            int r4 = com.google.protobuf.c.p(r6, r12, r4, r14, r15)
            com.google.protobuf.GeneratedMessageLite$b r6 = r2.descriptor
            java.lang.Object r7 = r15.c
            r11.O(r6, r7)
            goto L6d
        La1:
            if (r8 != r5) goto Lc1
            int r4 = com.google.protobuf.c.b(r12, r4, r15)
            java.lang.Object r3 = r15.c
            com.google.protobuf.ByteString r3 = (com.google.protobuf.ByteString) r3
            goto L6d
        Lac:
            if (r8 != 0) goto Lc1
            int r4 = com.google.protobuf.c.I(r12, r4, r15)
            int r13 = r15.f11721a
            com.google.protobuf.l<?> r2 = r10.d
            com.google.protobuf.ExtensionRegistryLite r6 = r15.d
            com.google.protobuf.MessageLite r7 = r10.f11727a
            java.lang.Object r2 = r2.b(r6, r7, r13)
            com.google.protobuf.GeneratedMessageLite$GeneratedExtension r2 = (com.google.protobuf.GeneratedMessageLite.GeneratedExtension) r2
            goto L6d
        Lc1:
            int r7 = com.google.protobuf.WireFormat.MESSAGE_SET_ITEM_END_TAG
            if (r6 != r7) goto Lc6
            goto Lcb
        Lc6:
            int r4 = com.google.protobuf.c.N(r6, r12, r4, r14, r15)
            goto L6d
        Lcb:
            if (r3 == 0) goto Ld4
            int r13 = com.google.protobuf.WireFormat.makeTag(r13, r5)
            r1.storeField(r13, r3)
        Ld4:
            r13 = r4
            goto L19
        Ld7:
            if (r13 != r14) goto Lda
            return
        Lda:
            com.google.protobuf.InvalidProtocolBufferException r11 = com.google.protobuf.InvalidProtocolBufferException.parseFailure()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.e0.i(java.lang.Object, byte[], int, int, com.google.protobuf.c$b):void");
    }

    public final <UT, UB> int j(x0<UT, UB> x0Var, T t) {
        return x0Var.i(x0Var.g(t));
    }

    public final <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void k(x0<UT, UB> x0Var, l<ET> lVar, T t, q0 q0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        UB f = x0Var.f(t);
        FieldSet<ET> d = lVar.d(t);
        do {
            try {
                if (q0Var.A() == Integer.MAX_VALUE) {
                    return;
                }
            } finally {
                x0Var.o(t, f);
            }
        } while (m(q0Var, extensionRegistryLite, lVar, d, x0Var, f));
    }

    public final <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> boolean m(q0 q0Var, ExtensionRegistryLite extensionRegistryLite, l<ET> lVar, FieldSet<ET> fieldSet, x0<UT, UB> x0Var, UB ub) throws IOException {
        int tag = q0Var.getTag();
        if (tag != WireFormat.MESSAGE_SET_ITEM_TAG) {
            if (WireFormat.getTagWireType(tag) == 2) {
                Object b = lVar.b(extensionRegistryLite, this.f11727a, WireFormat.getTagFieldNumber(tag));
                if (b != null) {
                    lVar.h(q0Var, b, extensionRegistryLite, fieldSet);
                    return true;
                }
                return x0Var.m(ub, q0Var);
            }
            return q0Var.C();
        }
        int i = 0;
        Object obj = null;
        ByteString byteString = null;
        while (q0Var.A() != Integer.MAX_VALUE) {
            int tag2 = q0Var.getTag();
            if (tag2 == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                i = q0Var.i();
                obj = lVar.b(extensionRegistryLite, this.f11727a, i);
            } else if (tag2 == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                if (obj != null) {
                    lVar.h(q0Var, obj, extensionRegistryLite, fieldSet);
                } else {
                    byteString = q0Var.p();
                }
            } else if (!q0Var.C()) {
                break;
            }
        }
        if (q0Var.getTag() == WireFormat.MESSAGE_SET_ITEM_END_TAG) {
            if (byteString != null) {
                if (obj != null) {
                    lVar.i(byteString, obj, extensionRegistryLite, fieldSet);
                } else {
                    x0Var.d(ub, i, byteString);
                }
            }
            return true;
        }
        throw InvalidProtocolBufferException.invalidEndTag();
    }

    public final <UT, UB> void n(x0<UT, UB> x0Var, T t, Writer writer) throws IOException {
        x0Var.s(x0Var.g(t), writer);
    }

    @Override // com.google.protobuf.s0
    public T newInstance() {
        return (T) this.f11727a.newBuilderForType().buildPartial();
    }
}
