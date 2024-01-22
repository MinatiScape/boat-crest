package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class l extends k<GeneratedMessageLite.b> {

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10985a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f10985a = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10985a[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10985a[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10985a[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10985a[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10985a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10985a[WireFormat.FieldType.FIXED32.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10985a[WireFormat.FieldType.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f10985a[WireFormat.FieldType.UINT32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f10985a[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f10985a[WireFormat.FieldType.SFIXED64.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f10985a[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f10985a[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f10985a[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f10985a[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f10985a[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f10985a[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f10985a[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k
    public int a(Map.Entry<?, ?> entry) {
        return ((GeneratedMessageLite.b) entry.getKey()).getNumber();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k
    public Object b(ExtensionRegistryLite extensionRegistryLite, MessageLite messageLite, int i) {
        return extensionRegistryLite.findLiteExtensionByNumber(messageLite, i);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k
    public FieldSet<GeneratedMessageLite.b> c(Object obj) {
        return ((GeneratedMessageLite.ExtendableMessage) obj).extensions;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k
    public FieldSet<GeneratedMessageLite.b> d(Object obj) {
        return ((GeneratedMessageLite.ExtendableMessage) obj).r();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k
    public boolean e(MessageLite messageLite) {
        return messageLite instanceof GeneratedMessageLite.ExtendableMessage;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k
    public void f(Object obj) {
        c(obj).x();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k
    public <UT, UB> UB g(l0 l0Var, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<GeneratedMessageLite.b> fieldSet, UB ub, s0<UT, UB> s0Var) throws IOException {
        Object j;
        ArrayList arrayList;
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        int number = generatedExtension.getNumber();
        if (generatedExtension.d.isRepeated() && generatedExtension.d.isPacked()) {
            switch (a.f10985a[generatedExtension.getLiteType().ordinal()]) {
                case 1:
                    arrayList = new ArrayList();
                    l0Var.F(arrayList);
                    break;
                case 2:
                    arrayList = new ArrayList();
                    l0Var.B(arrayList);
                    break;
                case 3:
                    arrayList = new ArrayList();
                    l0Var.j(arrayList);
                    break;
                case 4:
                    arrayList = new ArrayList();
                    l0Var.h(arrayList);
                    break;
                case 5:
                    arrayList = new ArrayList();
                    l0Var.x(arrayList);
                    break;
                case 6:
                    arrayList = new ArrayList();
                    l0Var.r(arrayList);
                    break;
                case 7:
                    arrayList = new ArrayList();
                    l0Var.y(arrayList);
                    break;
                case 8:
                    arrayList = new ArrayList();
                    l0Var.n(arrayList);
                    break;
                case 9:
                    arrayList = new ArrayList();
                    l0Var.u(arrayList);
                    break;
                case 10:
                    arrayList = new ArrayList();
                    l0Var.d(arrayList);
                    break;
                case 11:
                    arrayList = new ArrayList();
                    l0Var.w(arrayList);
                    break;
                case 12:
                    arrayList = new ArrayList();
                    l0Var.s(arrayList);
                    break;
                case 13:
                    arrayList = new ArrayList();
                    l0Var.e(arrayList);
                    break;
                case 14:
                    arrayList = new ArrayList();
                    l0Var.k(arrayList);
                    ub = (UB) p0.z(number, arrayList, generatedExtension.d.getEnumType(), ub, s0Var);
                    break;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + generatedExtension.d.getLiteType());
            }
            fieldSet.C(generatedExtension.d, arrayList);
        } else {
            Object obj2 = null;
            if (generatedExtension.getLiteType() == WireFormat.FieldType.ENUM) {
                int q = l0Var.q();
                if (generatedExtension.d.getEnumType().findValueByNumber(q) == null) {
                    return (UB) p0.L(number, q, ub, s0Var);
                }
                obj2 = Integer.valueOf(q);
            } else {
                switch (a.f10985a[generatedExtension.getLiteType().ordinal()]) {
                    case 1:
                        obj2 = Double.valueOf(l0Var.readDouble());
                        break;
                    case 2:
                        obj2 = Float.valueOf(l0Var.readFloat());
                        break;
                    case 3:
                        obj2 = Long.valueOf(l0Var.G());
                        break;
                    case 4:
                        obj2 = Long.valueOf(l0Var.t());
                        break;
                    case 5:
                        obj2 = Integer.valueOf(l0Var.q());
                        break;
                    case 6:
                        obj2 = Long.valueOf(l0Var.c());
                        break;
                    case 7:
                        obj2 = Integer.valueOf(l0Var.v());
                        break;
                    case 8:
                        obj2 = Boolean.valueOf(l0Var.f());
                        break;
                    case 9:
                        obj2 = Integer.valueOf(l0Var.i());
                        break;
                    case 10:
                        obj2 = Integer.valueOf(l0Var.D());
                        break;
                    case 11:
                        obj2 = Long.valueOf(l0Var.g());
                        break;
                    case 12:
                        obj2 = Integer.valueOf(l0Var.m());
                        break;
                    case 13:
                        obj2 = Long.valueOf(l0Var.z());
                        break;
                    case 14:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 15:
                        obj2 = l0Var.p();
                        break;
                    case 16:
                        obj2 = l0Var.a();
                        break;
                    case 17:
                        obj2 = l0Var.J(generatedExtension.getMessageDefaultInstance().getClass(), extensionRegistryLite);
                        break;
                    case 18:
                        obj2 = l0Var.N(generatedExtension.getMessageDefaultInstance().getClass(), extensionRegistryLite);
                        break;
                }
            }
            if (generatedExtension.isRepeated()) {
                fieldSet.a(generatedExtension.d, obj2);
            } else {
                int i = a.f10985a[generatedExtension.getLiteType().ordinal()];
                if ((i == 17 || i == 18) && (j = fieldSet.j(generatedExtension.d)) != null) {
                    obj2 = Internal.d(j, obj2);
                }
                fieldSet.C(generatedExtension.d, obj2);
            }
        }
        return ub;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k
    public void h(l0 l0Var, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<GeneratedMessageLite.b> fieldSet) throws IOException {
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        fieldSet.C(generatedExtension.d, l0Var.N(generatedExtension.getMessageDefaultInstance().getClass(), extensionRegistryLite));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k
    public void i(ByteString byteString, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<GeneratedMessageLite.b> fieldSet) throws IOException {
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        MessageLite buildPartial = generatedExtension.getMessageDefaultInstance().newBuilderForType().buildPartial();
        d P = d.P(ByteBuffer.wrap(byteString.toByteArray()), true);
        i0.a().b(buildPartial, P, extensionRegistryLite);
        fieldSet.C(generatedExtension.d, buildPartial);
        if (P.A() != Integer.MAX_VALUE) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.k
    public void j(Writer writer, Map.Entry<?, ?> entry) throws IOException {
        GeneratedMessageLite.b bVar = (GeneratedMessageLite.b) entry.getKey();
        if (bVar.isRepeated()) {
            switch (a.f10985a[bVar.getLiteType().ordinal()]) {
                case 1:
                    p0.P(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 2:
                    p0.T(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 3:
                    p0.W(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 4:
                    p0.e0(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 5:
                    p0.V(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 6:
                    p0.S(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 7:
                    p0.R(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 8:
                    p0.N(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 9:
                    p0.d0(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 10:
                    p0.Y(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 11:
                    p0.Z(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 12:
                    p0.a0(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 13:
                    p0.b0(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 14:
                    p0.V(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 15:
                    p0.O(bVar.getNumber(), (List) entry.getValue(), writer);
                    return;
                case 16:
                    p0.c0(bVar.getNumber(), (List) entry.getValue(), writer);
                    return;
                case 17:
                    List list = (List) entry.getValue();
                    if (list == null || list.isEmpty()) {
                        return;
                    }
                    p0.U(bVar.getNumber(), (List) entry.getValue(), writer, i0.a().d(list.get(0).getClass()));
                    return;
                case 18:
                    List list2 = (List) entry.getValue();
                    if (list2 == null || list2.isEmpty()) {
                        return;
                    }
                    p0.X(bVar.getNumber(), (List) entry.getValue(), writer, i0.a().d(list2.get(0).getClass()));
                    return;
                default:
                    return;
            }
        }
        switch (a.f10985a[bVar.getLiteType().ordinal()]) {
            case 1:
                writer.p(bVar.getNumber(), ((Double) entry.getValue()).doubleValue());
                return;
            case 2:
                writer.B(bVar.getNumber(), ((Float) entry.getValue()).floatValue());
                return;
            case 3:
                writer.u(bVar.getNumber(), ((Long) entry.getValue()).longValue());
                return;
            case 4:
                writer.f(bVar.getNumber(), ((Long) entry.getValue()).longValue());
                return;
            case 5:
                writer.h(bVar.getNumber(), ((Integer) entry.getValue()).intValue());
                return;
            case 6:
                writer.s(bVar.getNumber(), ((Long) entry.getValue()).longValue());
                return;
            case 7:
                writer.c(bVar.getNumber(), ((Integer) entry.getValue()).intValue());
                return;
            case 8:
                writer.v(bVar.getNumber(), ((Boolean) entry.getValue()).booleanValue());
                return;
            case 9:
                writer.o(bVar.getNumber(), ((Integer) entry.getValue()).intValue());
                return;
            case 10:
                writer.w(bVar.getNumber(), ((Integer) entry.getValue()).intValue());
                return;
            case 11:
                writer.i(bVar.getNumber(), ((Long) entry.getValue()).longValue());
                return;
            case 12:
                writer.H(bVar.getNumber(), ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                writer.m(bVar.getNumber(), ((Long) entry.getValue()).longValue());
                return;
            case 14:
                writer.h(bVar.getNumber(), ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                writer.M(bVar.getNumber(), (ByteString) entry.getValue());
                return;
            case 16:
                writer.e(bVar.getNumber(), (String) entry.getValue());
                return;
            case 17:
                writer.K(bVar.getNumber(), entry.getValue(), i0.a().d(entry.getValue().getClass()));
                return;
            case 18:
                writer.N(bVar.getNumber(), entry.getValue(), i0.a().d(entry.getValue().getClass()));
                return;
            default:
                return;
        }
    }
}
