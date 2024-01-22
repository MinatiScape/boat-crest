package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class h implements Writer {

    /* renamed from: a  reason: collision with root package name */
    public final CodedOutputStream f10980a;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10981a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f10981a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10981a[WireFormat.FieldType.FIXED32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10981a[WireFormat.FieldType.INT32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10981a[WireFormat.FieldType.SFIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10981a[WireFormat.FieldType.SINT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10981a[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10981a[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10981a[WireFormat.FieldType.INT64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f10981a[WireFormat.FieldType.SFIXED64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f10981a[WireFormat.FieldType.SINT64.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f10981a[WireFormat.FieldType.UINT64.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f10981a[WireFormat.FieldType.STRING.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public h(CodedOutputStream codedOutputStream) {
        CodedOutputStream codedOutputStream2 = (CodedOutputStream) Internal.b(codedOutputStream, "output");
        this.f10980a = codedOutputStream2;
        codedOutputStream2.f10945a = this;
    }

    public static h P(CodedOutputStream codedOutputStream) {
        h hVar = codedOutputStream.f10945a;
        return hVar != null ? hVar : new h(codedOutputStream);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void A(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeBoolSizeNoTag(list.get(i4).booleanValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeBoolNoTag(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeBool(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void B(int i, float f) throws IOException {
        this.f10980a.writeFloat(i, f);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void C(int i) throws IOException {
        this.f10980a.writeTag(i, 4);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void D(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSInt32SizeNoTag(list.get(i4).intValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeSInt32NoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeSInt32(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void E(int i, int i2) throws IOException {
        this.f10980a.writeEnum(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void F(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeInt64SizeNoTag(list.get(i4).longValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeInt64NoTag(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeInt64(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void G(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeDoubleSizeNoTag(list.get(i4).doubleValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeDoubleNoTag(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeDouble(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void H(int i, int i2) throws IOException {
        this.f10980a.writeSInt32(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void I(int i, List<ByteString> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f10980a.writeBytes(i, list.get(i2));
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public <K, V> void J(int i, MapEntryLite.b<K, V> bVar, Map<K, V> map) throws IOException {
        if (this.f10980a.i()) {
            T(i, bVar, map);
            return;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.f10980a.writeTag(i, 2);
            this.f10980a.writeUInt32NoTag(MapEntryLite.a(bVar, entry.getKey(), entry.getValue()));
            MapEntryLite.e(this.f10980a, bVar, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void K(int i, Object obj, n0 n0Var) throws IOException {
        this.f10980a.m(i, (MessageLite) obj, n0Var);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void L(int i, List<?> list, n0 n0Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            N(i, list.get(i2), n0Var);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void M(int i, ByteString byteString) throws IOException {
        this.f10980a.writeBytes(i, byteString);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void N(int i, Object obj, n0 n0Var) throws IOException {
        this.f10980a.o(i, (MessageLite) obj, n0Var);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void O(int i, List<?> list, n0 n0Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            K(i, list.get(i2), n0Var);
        }
    }

    public final <V> void Q(int i, boolean z, V v, MapEntryLite.b<Boolean, V> bVar) throws IOException {
        this.f10980a.writeTag(i, 2);
        this.f10980a.writeUInt32NoTag(MapEntryLite.a(bVar, Boolean.valueOf(z), v));
        MapEntryLite.e(this.f10980a, bVar, Boolean.valueOf(z), v);
    }

    public final <V> void R(int i, MapEntryLite.b<Integer, V> bVar, Map<Integer, V> map) throws IOException {
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
            this.f10980a.writeTag(i, 2);
            this.f10980a.writeUInt32NoTag(MapEntryLite.a(bVar, Integer.valueOf(i4), v));
            MapEntryLite.e(this.f10980a, bVar, Integer.valueOf(i4), v);
        }
    }

    public final <V> void S(int i, MapEntryLite.b<Long, V> bVar, Map<Long, V> map) throws IOException {
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
            this.f10980a.writeTag(i, 2);
            this.f10980a.writeUInt32NoTag(MapEntryLite.a(bVar, Long.valueOf(j), v));
            MapEntryLite.e(this.f10980a, bVar, Long.valueOf(j), v);
        }
    }

    public final <K, V> void T(int i, MapEntryLite.b<K, V> bVar, Map<K, V> map) throws IOException {
        switch (a.f10981a[bVar.f10962a.ordinal()]) {
            case 1:
                V v = map.get(Boolean.FALSE);
                if (v != null) {
                    Q(i, false, v, bVar);
                }
                V v2 = map.get(Boolean.TRUE);
                if (v2 != null) {
                    Q(i, true, v2, bVar);
                    return;
                }
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                R(i, bVar, map);
                return;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                S(i, bVar, map);
                return;
            case 12:
                U(i, bVar, map);
                return;
            default:
                throw new IllegalArgumentException("does not support key type: " + bVar.f10962a);
        }
    }

    public final <V> void U(int i, MapEntryLite.b<String, V> bVar, Map<String, V> map) throws IOException {
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
            this.f10980a.writeTag(i, 2);
            this.f10980a.writeUInt32NoTag(MapEntryLite.a(bVar, str2, v));
            MapEntryLite.e(this.f10980a, bVar, str2, v);
        }
    }

    public final void V(int i, Object obj) throws IOException {
        if (obj instanceof String) {
            this.f10980a.writeString(i, (String) obj);
        } else {
            this.f10980a.writeBytes(i, (ByteString) obj);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeFloatSizeNoTag(list.get(i4).floatValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeFloatNoTag(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeFloat(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void b(int i, Object obj) throws IOException {
        if (obj instanceof ByteString) {
            this.f10980a.writeRawMessageSetExtension(i, (ByteString) obj);
        } else {
            this.f10980a.writeMessageSetExtension(i, (MessageLite) obj);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void c(int i, int i2) throws IOException {
        this.f10980a.writeFixed32(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void d(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof LazyStringList) {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i2 < list.size()) {
                V(i, lazyStringList.getRaw(i2));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeString(i, list.get(i2));
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void e(int i, String str) throws IOException {
        this.f10980a.writeString(i, str);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void f(int i, long j) throws IOException {
        this.f10980a.writeUInt64(i, j);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void g(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeInt32SizeNoTag(list.get(i4).intValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeInt32NoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeInt32(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void h(int i, int i2) throws IOException {
        this.f10980a.writeInt32(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void i(int i, long j) throws IOException {
        this.f10980a.writeSFixed64(i, j);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void j(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeFixed32SizeNoTag(list.get(i4).intValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeFixed32NoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeFixed32(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void k(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeUInt32SizeNoTag(list.get(i4).intValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeUInt32NoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeUInt32(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void l(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSInt64SizeNoTag(list.get(i4).longValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeSInt64NoTag(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeSInt64(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void m(int i, long j) throws IOException {
        this.f10980a.writeSInt64(i, j);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void n(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeEnumSizeNoTag(list.get(i4).intValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeEnumNoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeEnum(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void o(int i, int i2) throws IOException {
        this.f10980a.writeUInt32(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void p(int i, double d) throws IOException {
        this.f10980a.writeDouble(i, d);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void q(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSFixed64SizeNoTag(list.get(i4).longValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeSFixed64NoTag(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeSFixed64(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void r(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeUInt64SizeNoTag(list.get(i4).longValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeUInt64NoTag(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeUInt64(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void s(int i, long j) throws IOException {
        this.f10980a.writeFixed64(i, j);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public Writer.FieldOrder t() {
        return Writer.FieldOrder.ASCENDING;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void u(int i, long j) throws IOException {
        this.f10980a.writeInt64(i, j);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void v(int i, boolean z) throws IOException {
        this.f10980a.writeBool(i, z);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void w(int i, int i2) throws IOException {
        this.f10980a.writeSFixed32(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void x(int i) throws IOException {
        this.f10980a.writeTag(i, 3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void y(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeFixed64SizeNoTag(list.get(i4).longValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeFixed64NoTag(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeFixed64(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void z(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f10980a.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.computeSFixed32SizeNoTag(list.get(i4).intValue());
            }
            this.f10980a.writeUInt32NoTag(i3);
            while (i2 < list.size()) {
                this.f10980a.writeSFixed32NoTag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f10980a.writeSFixed32(i, list.get(i2).intValue());
            i2++;
        }
    }
}
