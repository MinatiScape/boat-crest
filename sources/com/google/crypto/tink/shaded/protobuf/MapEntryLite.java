package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class MapEntryLite<K, V> {

    /* renamed from: a  reason: collision with root package name */
    public final b<K, V> f10960a;
    public final K b;
    public final V c;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10961a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f10961a = iArr;
            try {
                iArr[WireFormat.FieldType.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10961a[WireFormat.FieldType.ENUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10961a[WireFormat.FieldType.GROUP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class b<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public final WireFormat.FieldType f10962a;
        public final K b;
        public final WireFormat.FieldType c;
        public final V d;

        public b(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
            this.f10962a = fieldType;
            this.b = k;
            this.c = fieldType2;
            this.d = v;
        }
    }

    public MapEntryLite(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        this.f10960a = new b<>(fieldType, k, fieldType2, v);
        this.b = k;
        this.c = v;
    }

    public static <K, V> int a(b<K, V> bVar, K k, V v) {
        return FieldSet.e(bVar.f10962a, 1, k) + FieldSet.e(bVar.c, 2, v);
    }

    public static <K, V> Map.Entry<K, V> c(CodedInputStream codedInputStream, b<K, V> bVar, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        Object obj = bVar.b;
        Object obj2 = bVar.d;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == WireFormat.a(1, bVar.f10962a.getWireType())) {
                obj = d(codedInputStream, extensionRegistryLite, bVar.f10962a, obj);
            } else if (readTag == WireFormat.a(2, bVar.c.getWireType())) {
                obj2 = d(codedInputStream, extensionRegistryLite, bVar.c, obj2);
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public static <T> T d(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, WireFormat.FieldType fieldType, T t) throws IOException {
        int i = a.f10961a[fieldType.ordinal()];
        if (i == 1) {
            MessageLite.Builder builder = ((MessageLite) t).toBuilder();
            codedInputStream.readMessage(builder, extensionRegistryLite);
            return (T) builder.buildPartial();
        } else if (i != 2) {
            if (i != 3) {
                return (T) FieldSet.B(codedInputStream, fieldType, true);
            }
            throw new RuntimeException("Groups are not allowed in maps.");
        } else {
            return (T) Integer.valueOf(codedInputStream.readEnum());
        }
    }

    public static <K, V> void e(CodedOutputStream codedOutputStream, b<K, V> bVar, K k, V v) throws IOException {
        FieldSet.F(codedOutputStream, bVar.f10962a, 1, k);
        FieldSet.F(codedOutputStream, bVar.c, 2, v);
    }

    public static <K, V> MapEntryLite<K, V> newDefaultInstance(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        return new MapEntryLite<>(fieldType, k, fieldType2, v);
    }

    public b<K, V> b() {
        return this.f10960a;
    }

    public int computeMessageSize(int i, K k, V v) {
        return CodedOutputStream.computeTagSize(i) + CodedOutputStream.d(a(this.f10960a, k, v));
    }

    public K getKey() {
        return this.b;
    }

    public V getValue() {
        return this.c;
    }

    public Map.Entry<K, V> parseEntry(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return c(byteString.newCodedInput(), this.f10960a, extensionRegistryLite);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void parseInto(MapFieldLite<K, V> mapFieldLite, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
        b<K, V> bVar = this.f10960a;
        Object obj = bVar.b;
        Object obj2 = bVar.d;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == WireFormat.a(1, this.f10960a.f10962a.getWireType())) {
                obj = d(codedInputStream, extensionRegistryLite, this.f10960a.f10962a, obj);
            } else if (readTag == WireFormat.a(2, this.f10960a.c.getWireType())) {
                obj2 = d(codedInputStream, extensionRegistryLite, this.f10960a.c, obj2);
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        codedInputStream.checkLastTagWas(0);
        codedInputStream.popLimit(pushLimit);
        mapFieldLite.put(obj, obj2);
    }

    public void serializeTo(CodedOutputStream codedOutputStream, int i, K k, V v) throws IOException {
        codedOutputStream.writeTag(i, 2);
        codedOutputStream.writeUInt32NoTag(a(this.f10960a, k, v));
        e(codedOutputStream, this.f10960a, k, v);
    }
}
