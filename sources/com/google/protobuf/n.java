package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public final class n extends l<GeneratedMessageLite.b> {

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11746a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f11746a = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11746a[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11746a[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11746a[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f11746a[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f11746a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f11746a[WireFormat.FieldType.FIXED32.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f11746a[WireFormat.FieldType.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f11746a[WireFormat.FieldType.UINT32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f11746a[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f11746a[WireFormat.FieldType.SFIXED64.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f11746a[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f11746a[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f11746a[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f11746a[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f11746a[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f11746a[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f11746a[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    @Override // com.google.protobuf.l
    public int a(Map.Entry<?, ?> entry) {
        return ((GeneratedMessageLite.b) entry.getKey()).getNumber();
    }

    @Override // com.google.protobuf.l
    public Object b(ExtensionRegistryLite extensionRegistryLite, MessageLite messageLite, int i) {
        return extensionRegistryLite.findLiteExtensionByNumber(messageLite, i);
    }

    @Override // com.google.protobuf.l
    public FieldSet<GeneratedMessageLite.b> c(Object obj) {
        return ((GeneratedMessageLite.ExtendableMessage) obj).extensions;
    }

    @Override // com.google.protobuf.l
    public FieldSet<GeneratedMessageLite.b> d(Object obj) {
        return ((GeneratedMessageLite.ExtendableMessage) obj).ensureExtensionsAreMutable();
    }

    @Override // com.google.protobuf.l
    public boolean e(MessageLite messageLite) {
        return messageLite instanceof GeneratedMessageLite.ExtendableMessage;
    }

    @Override // com.google.protobuf.l
    public void f(Object obj) {
        c(obj).I();
    }

    @Override // com.google.protobuf.l
    public <UT, UB> UB g(q0 q0Var, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<GeneratedMessageLite.b> fieldSet, UB ub, x0<UT, UB> x0Var) throws IOException {
        Object u;
        ArrayList arrayList;
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        int number = generatedExtension.getNumber();
        if (generatedExtension.descriptor.isRepeated() && generatedExtension.descriptor.isPacked()) {
            switch (a.f11746a[generatedExtension.getLiteType().ordinal()]) {
                case 1:
                    arrayList = new ArrayList();
                    q0Var.F(arrayList);
                    break;
                case 2:
                    arrayList = new ArrayList();
                    q0Var.B(arrayList);
                    break;
                case 3:
                    arrayList = new ArrayList();
                    q0Var.j(arrayList);
                    break;
                case 4:
                    arrayList = new ArrayList();
                    q0Var.h(arrayList);
                    break;
                case 5:
                    arrayList = new ArrayList();
                    q0Var.x(arrayList);
                    break;
                case 6:
                    arrayList = new ArrayList();
                    q0Var.r(arrayList);
                    break;
                case 7:
                    arrayList = new ArrayList();
                    q0Var.y(arrayList);
                    break;
                case 8:
                    arrayList = new ArrayList();
                    q0Var.n(arrayList);
                    break;
                case 9:
                    arrayList = new ArrayList();
                    q0Var.u(arrayList);
                    break;
                case 10:
                    arrayList = new ArrayList();
                    q0Var.d(arrayList);
                    break;
                case 11:
                    arrayList = new ArrayList();
                    q0Var.w(arrayList);
                    break;
                case 12:
                    arrayList = new ArrayList();
                    q0Var.s(arrayList);
                    break;
                case 13:
                    arrayList = new ArrayList();
                    q0Var.e(arrayList);
                    break;
                case 14:
                    arrayList = new ArrayList();
                    q0Var.k(arrayList);
                    ub = (UB) u0.z(number, arrayList, generatedExtension.descriptor.getEnumType(), ub, x0Var);
                    break;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + generatedExtension.descriptor.getLiteType());
            }
            fieldSet.O(generatedExtension.descriptor, arrayList);
        } else {
            Object obj2 = null;
            if (generatedExtension.getLiteType() == WireFormat.FieldType.ENUM) {
                int q = q0Var.q();
                if (generatedExtension.descriptor.getEnumType().findValueByNumber(q) == null) {
                    return (UB) u0.M(number, q, ub, x0Var);
                }
                obj2 = Integer.valueOf(q);
            } else {
                switch (a.f11746a[generatedExtension.getLiteType().ordinal()]) {
                    case 1:
                        obj2 = Double.valueOf(q0Var.readDouble());
                        break;
                    case 2:
                        obj2 = Float.valueOf(q0Var.readFloat());
                        break;
                    case 3:
                        obj2 = Long.valueOf(q0Var.G());
                        break;
                    case 4:
                        obj2 = Long.valueOf(q0Var.t());
                        break;
                    case 5:
                        obj2 = Integer.valueOf(q0Var.q());
                        break;
                    case 6:
                        obj2 = Long.valueOf(q0Var.c());
                        break;
                    case 7:
                        obj2 = Integer.valueOf(q0Var.v());
                        break;
                    case 8:
                        obj2 = Boolean.valueOf(q0Var.f());
                        break;
                    case 9:
                        obj2 = Integer.valueOf(q0Var.i());
                        break;
                    case 10:
                        obj2 = Integer.valueOf(q0Var.D());
                        break;
                    case 11:
                        obj2 = Long.valueOf(q0Var.g());
                        break;
                    case 12:
                        obj2 = Integer.valueOf(q0Var.m());
                        break;
                    case 13:
                        obj2 = Long.valueOf(q0Var.z());
                        break;
                    case 14:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 15:
                        obj2 = q0Var.p();
                        break;
                    case 16:
                        obj2 = q0Var.a();
                        break;
                    case 17:
                        obj2 = q0Var.K(generatedExtension.getMessageDefaultInstance().getClass(), extensionRegistryLite);
                        break;
                    case 18:
                        obj2 = q0Var.M(generatedExtension.getMessageDefaultInstance().getClass(), extensionRegistryLite);
                        break;
                }
            }
            if (generatedExtension.isRepeated()) {
                fieldSet.h(generatedExtension.descriptor, obj2);
            } else {
                int i = a.f11746a[generatedExtension.getLiteType().ordinal()];
                if ((i == 17 || i == 18) && (u = fieldSet.u(generatedExtension.descriptor)) != null) {
                    obj2 = Internal.mergeMessage(u, obj2);
                }
                fieldSet.O(generatedExtension.descriptor, obj2);
            }
        }
        return ub;
    }

    @Override // com.google.protobuf.l
    public void h(q0 q0Var, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<GeneratedMessageLite.b> fieldSet) throws IOException {
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        fieldSet.O(generatedExtension.descriptor, q0Var.M(generatedExtension.getMessageDefaultInstance().getClass(), extensionRegistryLite));
    }

    @Override // com.google.protobuf.l
    public void i(ByteString byteString, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<GeneratedMessageLite.b> fieldSet) throws IOException {
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        MessageLite buildPartial = generatedExtension.getMessageDefaultInstance().newBuilderForType().buildPartial();
        d Q = d.Q(ByteBuffer.wrap(byteString.toByteArray()), true);
        n0.a().b(buildPartial, Q, extensionRegistryLite);
        fieldSet.O(generatedExtension.descriptor, buildPartial);
        if (Q.A() != Integer.MAX_VALUE) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    @Override // com.google.protobuf.l
    public void j(Writer writer, Map.Entry<?, ?> entry) throws IOException {
        GeneratedMessageLite.b bVar = (GeneratedMessageLite.b) entry.getKey();
        if (bVar.isRepeated()) {
            switch (a.f11746a[bVar.getLiteType().ordinal()]) {
                case 1:
                    u0.R(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 2:
                    u0.V(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 3:
                    u0.Z(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 4:
                    u0.i0(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 5:
                    u0.Y(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 6:
                    u0.U(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 7:
                    u0.T(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 8:
                    u0.P(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 9:
                    u0.h0(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 10:
                    u0.c0(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 11:
                    u0.d0(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 12:
                    u0.e0(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 13:
                    u0.f0(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 14:
                    u0.Y(bVar.getNumber(), (List) entry.getValue(), writer, bVar.isPacked());
                    return;
                case 15:
                    u0.Q(bVar.getNumber(), (List) entry.getValue(), writer);
                    return;
                case 16:
                    u0.g0(bVar.getNumber(), (List) entry.getValue(), writer);
                    return;
                case 17:
                    List list = (List) entry.getValue();
                    if (list == null || list.isEmpty()) {
                        return;
                    }
                    u0.X(bVar.getNumber(), (List) entry.getValue(), writer, n0.a().d(list.get(0).getClass()));
                    return;
                case 18:
                    List list2 = (List) entry.getValue();
                    if (list2 == null || list2.isEmpty()) {
                        return;
                    }
                    u0.b0(bVar.getNumber(), (List) entry.getValue(), writer, n0.a().d(list2.get(0).getClass()));
                    return;
                default:
                    return;
            }
        }
        switch (a.f11746a[bVar.getLiteType().ordinal()]) {
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
                writer.L(bVar.getNumber(), entry.getValue(), n0.a().d(entry.getValue().getClass()));
                return;
            case 18:
                writer.O(bVar.getNumber(), entry.getValue(), n0.a().d(entry.getValue().getClass()));
                return;
            default:
                return;
        }
    }
}
