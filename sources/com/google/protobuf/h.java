package com.google.protobuf;

import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public final class h implements Writer {

    /* renamed from: a  reason: collision with root package name */
    public final CodedOutputStream f11731a;

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11732a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f11732a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11732a[WireFormat.FieldType.FIXED32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11732a[WireFormat.FieldType.INT32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11732a[WireFormat.FieldType.SFIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f11732a[WireFormat.FieldType.SINT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f11732a[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f11732a[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f11732a[WireFormat.FieldType.INT64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f11732a[WireFormat.FieldType.SFIXED64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f11732a[WireFormat.FieldType.SINT64.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f11732a[WireFormat.FieldType.UINT64.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f11732a[WireFormat.FieldType.STRING.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public h(CodedOutputStream codedOutputStream) {
        CodedOutputStream codedOutputStream2 = (CodedOutputStream) Internal.checkNotNull(codedOutputStream, "output");
        this.f11731a = codedOutputStream2;
        codedOutputStream2.wrapper = this;
    }

    public static h T(CodedOutputStream codedOutputStream) {
        h hVar = codedOutputStream.wrapper;
        return hVar != null ? hVar : new h(codedOutputStream);
    }

    @Override // com.google.protobuf.Writer
    public void A(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeBoolSizeNoTag(list.get(i4).booleanValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeBoolNoTag(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeBool(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void B(int i, float f) throws IOException {
        this.f11731a.writeFloat(i, f);
    }

    @Override // com.google.protobuf.Writer
    public void C(int i) throws IOException {
        this.f11731a.writeTag(i, 4);
    }

    @Override // com.google.protobuf.Writer
    public void D(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSInt32SizeNoTag(list.get(i4).intValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeSInt32NoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeSInt32(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void E(int i, int i2) throws IOException {
        this.f11731a.writeEnum(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void F(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeInt64SizeNoTag(list.get(i4).longValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeInt64NoTag(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeInt64(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void G(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeDoubleSizeNoTag(list.get(i4).doubleValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeDoubleNoTag(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeDouble(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void H(int i, int i2) throws IOException {
        this.f11731a.writeSInt32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void I(int i, List<ByteString> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f11731a.writeBytes(i, list.get(i2));
        }
    }

    @Override // com.google.protobuf.Writer
    public void J(int i, List<?> list, s0 s0Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            O(i, list.get(i2), s0Var);
        }
    }

    @Override // com.google.protobuf.Writer
    public void K(int i, List<?> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            R(i, list.get(i2));
        }
    }

    @Override // com.google.protobuf.Writer
    public void L(int i, Object obj, s0 s0Var) throws IOException {
        this.f11731a.writeGroup(i, (MessageLite) obj, s0Var);
    }

    @Override // com.google.protobuf.Writer
    public void M(int i, ByteString byteString) throws IOException {
        this.f11731a.writeBytes(i, byteString);
    }

    @Override // com.google.protobuf.Writer
    public void N(int i, List<?> list, s0 s0Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            L(i, list.get(i2), s0Var);
        }
    }

    @Override // com.google.protobuf.Writer
    public void O(int i, Object obj, s0 s0Var) throws IOException {
        this.f11731a.writeMessage(i, (MessageLite) obj, s0Var);
    }

    @Override // com.google.protobuf.Writer
    public void P(int i, List<?> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            Q(i, list.get(i2));
        }
    }

    @Override // com.google.protobuf.Writer
    public void Q(int i, Object obj) throws IOException {
        this.f11731a.writeMessage(i, (MessageLite) obj);
    }

    @Override // com.google.protobuf.Writer
    public void R(int i, Object obj) throws IOException {
        this.f11731a.writeGroup(i, (MessageLite) obj);
    }

    @Override // com.google.protobuf.Writer
    public <K, V> void S(int i, MapEntryLite.b<K, V> bVar, Map<K, V> map) throws IOException {
        if (this.f11731a.isSerializationDeterministic()) {
            X(i, bVar, map);
            return;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.f11731a.writeTag(i, 2);
            this.f11731a.writeUInt32NoTag(MapEntryLite.computeSerializedSize(bVar, entry.getKey(), entry.getValue()));
            MapEntryLite.writeTo(this.f11731a, bVar, entry.getKey(), entry.getValue());
        }
    }

    public final <V> void U(int i, boolean z, V v, MapEntryLite.b<Boolean, V> bVar) throws IOException {
        this.f11731a.writeTag(i, 2);
        this.f11731a.writeUInt32NoTag(MapEntryLite.computeSerializedSize(bVar, Boolean.valueOf(z), v));
        MapEntryLite.writeTo(this.f11731a, bVar, Boolean.valueOf(z), v);
    }

    public final <V> void V(int i, MapEntryLite.b<Integer, V> bVar, Map<Integer, V> map) throws IOException {
        int size = map.size();
        int[] iArr = new int[size];
        int i2 = 0;
        for (Integer num : map.keySet()) {
            iArr[i2] = num.intValue();
            i2++;
        }
        Arrays.sort(iArr);
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = iArr[i3];
            V v = map.get(Integer.valueOf(i4));
            this.f11731a.writeTag(i, 2);
            this.f11731a.writeUInt32NoTag(MapEntryLite.computeSerializedSize(bVar, Integer.valueOf(i4), v));
            MapEntryLite.writeTo(this.f11731a, bVar, Integer.valueOf(i4), v);
        }
    }

    public final <V> void W(int i, MapEntryLite.b<Long, V> bVar, Map<Long, V> map) throws IOException {
        int size = map.size();
        long[] jArr = new long[size];
        int i2 = 0;
        for (Long l : map.keySet()) {
            jArr[i2] = l.longValue();
            i2++;
        }
        Arrays.sort(jArr);
        for (int i3 = 0; i3 < size; i3++) {
            long j = jArr[i3];
            V v = map.get(Long.valueOf(j));
            this.f11731a.writeTag(i, 2);
            this.f11731a.writeUInt32NoTag(MapEntryLite.computeSerializedSize(bVar, Long.valueOf(j), v));
            MapEntryLite.writeTo(this.f11731a, bVar, Long.valueOf(j), v);
        }
    }

    public final <K, V> void X(int i, MapEntryLite.b<K, V> bVar, Map<K, V> map) throws IOException {
        switch (a.f11732a[bVar.f11700a.ordinal()]) {
            case 1:
                V v = map.get(Boolean.FALSE);
                if (v != null) {
                    U(i, false, v, bVar);
                }
                V v2 = map.get(Boolean.TRUE);
                if (v2 != null) {
                    U(i, true, v2, bVar);
                    return;
                }
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                V(i, bVar, map);
                return;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                W(i, bVar, map);
                return;
            case 12:
                Y(i, bVar, map);
                return;
            default:
                throw new IllegalArgumentException("does not support key type: " + bVar.f11700a);
        }
    }

    public final <V> void Y(int i, MapEntryLite.b<String, V> bVar, Map<String, V> map) throws IOException {
        int size = map.size();
        String[] strArr = new String[size];
        int i2 = 0;
        for (String str : map.keySet()) {
            strArr[i2] = str;
            i2++;
        }
        Arrays.sort(strArr);
        for (int i3 = 0; i3 < size; i3++) {
            String str2 = strArr[i3];
            V v = map.get(str2);
            this.f11731a.writeTag(i, 2);
            this.f11731a.writeUInt32NoTag(MapEntryLite.computeSerializedSize(bVar, str2, v));
            MapEntryLite.writeTo(this.f11731a, bVar, str2, v);
        }
    }

    public final void Z(int i, Object obj) throws IOException {
        if (obj instanceof String) {
            this.f11731a.writeString(i, (String) obj);
        } else {
            this.f11731a.writeBytes(i, (ByteString) obj);
        }
    }

    @Override // com.google.protobuf.Writer
    public void a(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeFloatSizeNoTag(list.get(i4).floatValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeFloatNoTag(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeFloat(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public final void b(int i, Object obj) throws IOException {
        if (obj instanceof ByteString) {
            this.f11731a.writeRawMessageSetExtension(i, (ByteString) obj);
        } else {
            this.f11731a.writeMessageSetExtension(i, (MessageLite) obj);
        }
    }

    @Override // com.google.protobuf.Writer
    public void c(int i, int i2) throws IOException {
        this.f11731a.writeFixed32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void d(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof LazyStringList) {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i2 < list.size()) {
                Z(i, lazyStringList.getRaw(i2));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeString(i, list.get(i2));
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void e(int i, String str) throws IOException {
        this.f11731a.writeString(i, str);
    }

    @Override // com.google.protobuf.Writer
    public void f(int i, long j) throws IOException {
        this.f11731a.writeUInt64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public void g(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeInt32SizeNoTag(list.get(i4).intValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeInt32NoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeInt32(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void h(int i, int i2) throws IOException {
        this.f11731a.writeInt32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void i(int i, long j) throws IOException {
        this.f11731a.writeSFixed64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public void j(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeFixed32SizeNoTag(list.get(i4).intValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeFixed32NoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeFixed32(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void k(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeUInt32SizeNoTag(list.get(i4).intValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeUInt32NoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeUInt32(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void l(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSInt64SizeNoTag(list.get(i4).longValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeSInt64NoTag(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeSInt64(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void m(int i, long j) throws IOException {
        this.f11731a.writeSInt64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public void n(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeEnumSizeNoTag(list.get(i4).intValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeEnumNoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeEnum(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void o(int i, int i2) throws IOException {
        this.f11731a.writeUInt32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void p(int i, double d) throws IOException {
        this.f11731a.writeDouble(i, d);
    }

    @Override // com.google.protobuf.Writer
    public void q(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSFixed64SizeNoTag(list.get(i4).longValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeSFixed64NoTag(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeSFixed64(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void r(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeUInt64SizeNoTag(list.get(i4).longValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeUInt64NoTag(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeUInt64(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void s(int i, long j) throws IOException {
        this.f11731a.writeFixed64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public Writer.FieldOrder t() {
        return Writer.FieldOrder.ASCENDING;
    }

    @Override // com.google.protobuf.Writer
    public void u(int i, long j) throws IOException {
        this.f11731a.writeInt64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public void v(int i, boolean z) throws IOException {
        this.f11731a.writeBool(i, z);
    }

    @Override // com.google.protobuf.Writer
    public void w(int i, int i2) throws IOException {
        this.f11731a.writeSFixed32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void x(int i) throws IOException {
        this.f11731a.writeTag(i, 3);
    }

    @Override // com.google.protobuf.Writer
    public void y(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeFixed64SizeNoTag(list.get(i4).longValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeFixed64NoTag(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeFixed64(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void z(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f11731a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSFixed32SizeNoTag(list.get(i4).intValue());
            }
            this.f11731a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f11731a.writeSFixed32NoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f11731a.writeSFixed32(i, list.get(i2).intValue());
            i2++;
        }
    }
}
