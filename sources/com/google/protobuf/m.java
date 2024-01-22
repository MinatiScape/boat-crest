package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public final class m extends l<Descriptors.FieldDescriptor> {

    /* renamed from: a  reason: collision with root package name */
    public static final long f11744a = k();
    public static final /* synthetic */ int b = 0;

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11745a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f11745a = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11745a[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11745a[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11745a[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f11745a[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f11745a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f11745a[WireFormat.FieldType.FIXED32.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f11745a[WireFormat.FieldType.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f11745a[WireFormat.FieldType.UINT32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f11745a[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f11745a[WireFormat.FieldType.SFIXED64.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f11745a[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f11745a[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f11745a[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f11745a[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f11745a[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f11745a[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f11745a[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    public static <T> long k() {
        return a1.N(GeneratedMessageV3.ExtendableMessage.class.getDeclaredField("extensions"));
    }

    @Override // com.google.protobuf.l
    public int a(Map.Entry<?, ?> entry) {
        return ((Descriptors.FieldDescriptor) entry.getKey()).getNumber();
    }

    @Override // com.google.protobuf.l
    public Object b(ExtensionRegistryLite extensionRegistryLite, MessageLite messageLite, int i) {
        return ((ExtensionRegistry) extensionRegistryLite).findExtensionByNumber(((Message) messageLite).getDescriptorForType(), i);
    }

    @Override // com.google.protobuf.l
    public FieldSet<Descriptors.FieldDescriptor> c(Object obj) {
        return (FieldSet) a1.H(obj, f11744a);
    }

    @Override // com.google.protobuf.l
    public FieldSet<Descriptors.FieldDescriptor> d(Object obj) {
        FieldSet<Descriptors.FieldDescriptor> c = c(obj);
        if (c.D()) {
            FieldSet<Descriptors.FieldDescriptor> clone = c.clone();
            l(obj, clone);
            return clone;
        }
        return c;
    }

    @Override // com.google.protobuf.l
    public boolean e(MessageLite messageLite) {
        return messageLite instanceof GeneratedMessageV3.ExtendableMessage;
    }

    @Override // com.google.protobuf.l
    public void f(Object obj) {
        c(obj).I();
    }

    @Override // com.google.protobuf.l
    public <UT, UB> UB g(q0 q0Var, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<Descriptors.FieldDescriptor> fieldSet, UB ub, x0<UT, UB> x0Var) throws IOException {
        Object u;
        ArrayList arrayList;
        ArrayList arrayList2;
        ExtensionRegistry.ExtensionInfo extensionInfo = (ExtensionRegistry.ExtensionInfo) obj;
        int number = extensionInfo.descriptor.getNumber();
        if (extensionInfo.descriptor.isRepeated() && extensionInfo.descriptor.isPacked()) {
            switch (a.f11745a[extensionInfo.descriptor.getLiteType().ordinal()]) {
                case 1:
                    arrayList = new ArrayList();
                    q0Var.F(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 2:
                    arrayList = new ArrayList();
                    q0Var.B(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 3:
                    arrayList = new ArrayList();
                    q0Var.j(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 4:
                    arrayList = new ArrayList();
                    q0Var.h(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 5:
                    arrayList = new ArrayList();
                    q0Var.x(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 6:
                    arrayList = new ArrayList();
                    q0Var.r(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 7:
                    arrayList = new ArrayList();
                    q0Var.y(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 8:
                    arrayList = new ArrayList();
                    q0Var.n(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 9:
                    arrayList = new ArrayList();
                    q0Var.u(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 10:
                    arrayList = new ArrayList();
                    q0Var.d(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 11:
                    arrayList = new ArrayList();
                    q0Var.w(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 12:
                    arrayList = new ArrayList();
                    q0Var.s(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 13:
                    arrayList = new ArrayList();
                    q0Var.e(arrayList);
                    arrayList2 = arrayList;
                    break;
                case 14:
                    ArrayList<Integer> arrayList3 = new ArrayList();
                    q0Var.k(arrayList3);
                    arrayList2 = new ArrayList();
                    for (Integer num : arrayList3) {
                        int intValue = num.intValue();
                        Descriptors.EnumValueDescriptor findValueByNumber = extensionInfo.descriptor.getEnumType().findValueByNumber(intValue);
                        if (findValueByNumber != null) {
                            arrayList2.add(findValueByNumber);
                        } else {
                            ub = (UB) u0.M(number, intValue, ub, x0Var);
                        }
                    }
                    break;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + extensionInfo.descriptor.getLiteType());
            }
            fieldSet.O(extensionInfo.descriptor, arrayList2);
        } else {
            Object obj2 = null;
            if (extensionInfo.descriptor.getLiteType() == WireFormat.FieldType.ENUM) {
                int q = q0Var.q();
                obj2 = extensionInfo.descriptor.getEnumType().findValueByNumber(q);
                if (obj2 == null) {
                    return (UB) u0.M(number, q, ub, x0Var);
                }
            } else {
                switch (a.f11745a[extensionInfo.descriptor.getLiteType().ordinal()]) {
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
                        obj2 = q0Var.K(extensionInfo.defaultInstance.getClass(), extensionRegistryLite);
                        break;
                    case 18:
                        obj2 = q0Var.M(extensionInfo.defaultInstance.getClass(), extensionRegistryLite);
                        break;
                }
            }
            if (extensionInfo.descriptor.isRepeated()) {
                fieldSet.h(extensionInfo.descriptor, obj2);
            } else {
                int i = a.f11745a[extensionInfo.descriptor.getLiteType().ordinal()];
                if ((i == 17 || i == 18) && (u = fieldSet.u(extensionInfo.descriptor)) != null) {
                    obj2 = Internal.mergeMessage(u, obj2);
                }
                fieldSet.O(extensionInfo.descriptor, obj2);
            }
        }
        return ub;
    }

    @Override // com.google.protobuf.l
    public void h(q0 q0Var, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<Descriptors.FieldDescriptor> fieldSet) throws IOException {
        ExtensionRegistry.ExtensionInfo extensionInfo = (ExtensionRegistry.ExtensionInfo) obj;
        if (ExtensionRegistryLite.isEagerlyParseMessageSets()) {
            fieldSet.O(extensionInfo.descriptor, q0Var.M(extensionInfo.defaultInstance.getClass(), extensionRegistryLite));
            return;
        }
        fieldSet.O(extensionInfo.descriptor, new LazyField(extensionInfo.defaultInstance, extensionRegistryLite, q0Var.p()));
    }

    @Override // com.google.protobuf.l
    public void i(ByteString byteString, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<Descriptors.FieldDescriptor> fieldSet) throws IOException {
        ExtensionRegistry.ExtensionInfo extensionInfo = (ExtensionRegistry.ExtensionInfo) obj;
        Message buildPartial = extensionInfo.defaultInstance.newBuilderForType().buildPartial();
        if (ExtensionRegistryLite.isEagerlyParseMessageSets()) {
            d Q = d.Q(ByteBuffer.wrap(byteString.toByteArray()), true);
            n0.a().b(buildPartial, Q, extensionRegistryLite);
            fieldSet.O(extensionInfo.descriptor, buildPartial);
            if (Q.A() != Integer.MAX_VALUE) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
            return;
        }
        fieldSet.O(extensionInfo.descriptor, new LazyField(extensionInfo.defaultInstance, extensionRegistryLite, byteString));
    }

    @Override // com.google.protobuf.l
    public void j(Writer writer, Map.Entry<?, ?> entry) throws IOException {
        Descriptors.FieldDescriptor fieldDescriptor = (Descriptors.FieldDescriptor) entry.getKey();
        if (fieldDescriptor.isRepeated()) {
            switch (a.f11745a[fieldDescriptor.getLiteType().ordinal()]) {
                case 1:
                    u0.R(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 2:
                    u0.V(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 3:
                    u0.Z(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 4:
                    u0.i0(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 5:
                    u0.Y(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 6:
                    u0.U(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 7:
                    u0.T(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 8:
                    u0.P(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 9:
                    u0.h0(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 10:
                    u0.c0(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 11:
                    u0.d0(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 12:
                    u0.e0(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 13:
                    u0.f0(fieldDescriptor.getNumber(), (List) entry.getValue(), writer, fieldDescriptor.isPacked());
                    return;
                case 14:
                    ArrayList arrayList = new ArrayList();
                    for (Descriptors.EnumValueDescriptor enumValueDescriptor : (List) entry.getValue()) {
                        arrayList.add(Integer.valueOf(enumValueDescriptor.getNumber()));
                    }
                    u0.Y(fieldDescriptor.getNumber(), arrayList, writer, fieldDescriptor.isPacked());
                    return;
                case 15:
                    u0.Q(fieldDescriptor.getNumber(), (List) entry.getValue(), writer);
                    return;
                case 16:
                    u0.g0(fieldDescriptor.getNumber(), (List) entry.getValue(), writer);
                    return;
                case 17:
                    u0.W(fieldDescriptor.getNumber(), (List) entry.getValue(), writer);
                    return;
                case 18:
                    u0.a0(fieldDescriptor.getNumber(), (List) entry.getValue(), writer);
                    return;
                default:
                    return;
            }
        }
        switch (a.f11745a[fieldDescriptor.getLiteType().ordinal()]) {
            case 1:
                writer.p(fieldDescriptor.getNumber(), ((Double) entry.getValue()).doubleValue());
                return;
            case 2:
                writer.B(fieldDescriptor.getNumber(), ((Float) entry.getValue()).floatValue());
                return;
            case 3:
                writer.u(fieldDescriptor.getNumber(), ((Long) entry.getValue()).longValue());
                return;
            case 4:
                writer.f(fieldDescriptor.getNumber(), ((Long) entry.getValue()).longValue());
                return;
            case 5:
                writer.h(fieldDescriptor.getNumber(), ((Integer) entry.getValue()).intValue());
                return;
            case 6:
                writer.s(fieldDescriptor.getNumber(), ((Long) entry.getValue()).longValue());
                return;
            case 7:
                writer.c(fieldDescriptor.getNumber(), ((Integer) entry.getValue()).intValue());
                return;
            case 8:
                writer.v(fieldDescriptor.getNumber(), ((Boolean) entry.getValue()).booleanValue());
                return;
            case 9:
                writer.o(fieldDescriptor.getNumber(), ((Integer) entry.getValue()).intValue());
                return;
            case 10:
                writer.w(fieldDescriptor.getNumber(), ((Integer) entry.getValue()).intValue());
                return;
            case 11:
                writer.i(fieldDescriptor.getNumber(), ((Long) entry.getValue()).longValue());
                return;
            case 12:
                writer.H(fieldDescriptor.getNumber(), ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                writer.m(fieldDescriptor.getNumber(), ((Long) entry.getValue()).longValue());
                return;
            case 14:
                writer.h(fieldDescriptor.getNumber(), ((Descriptors.EnumValueDescriptor) entry.getValue()).getNumber());
                return;
            case 15:
                writer.M(fieldDescriptor.getNumber(), (ByteString) entry.getValue());
                return;
            case 16:
                writer.e(fieldDescriptor.getNumber(), (String) entry.getValue());
                return;
            case 17:
                writer.R(fieldDescriptor.getNumber(), entry.getValue());
                return;
            case 18:
                writer.Q(fieldDescriptor.getNumber(), entry.getValue());
                return;
            default:
                return;
        }
    }

    public void l(Object obj, FieldSet<Descriptors.FieldDescriptor> fieldSet) {
        a1.Z(obj, f11744a, fieldSet);
    }
}
