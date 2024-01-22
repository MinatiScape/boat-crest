package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import java.util.Map;
/* loaded from: classes10.dex */
public class v implements u {
    public static <K, V> int a(int i, Object obj, Object obj2) {
        MapFieldLite mapFieldLite = (MapFieldLite) obj;
        MapEntryLite mapEntryLite = (MapEntryLite) obj2;
        int i2 = 0;
        if (mapFieldLite.isEmpty()) {
            return 0;
        }
        for (Map.Entry<K, V> entry : mapFieldLite.entrySet()) {
            i2 += mapEntryLite.computeMessageSize(i, entry.getKey(), entry.getValue());
        }
        return i2;
    }

    public static <K, V> MapFieldLite<K, V> b(Object obj, Object obj2) {
        MapFieldLite<K, V> mapFieldLite = (MapFieldLite) obj;
        MapFieldLite<K, V> mapFieldLite2 = (MapFieldLite) obj2;
        if (!mapFieldLite2.isEmpty()) {
            if (!mapFieldLite.isMutable()) {
                mapFieldLite = mapFieldLite.mutableCopy();
            }
            mapFieldLite.mergeFrom(mapFieldLite2);
        }
        return mapFieldLite;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.u
    public Object c(Object obj, Object obj2) {
        return b(obj, obj2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.u
    public MapEntryLite.b<?, ?> d(Object obj) {
        return ((MapEntryLite) obj).b();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.u
    public Map<?, ?> e(Object obj) {
        return (MapFieldLite) obj;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.u
    public Object f(Object obj) {
        return MapFieldLite.emptyMapField().mutableCopy();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.u
    public Map<?, ?> g(Object obj) {
        return (MapFieldLite) obj;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.u
    public Object h(Object obj) {
        ((MapFieldLite) obj).makeImmutable();
        return obj;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.u
    public int i(int i, Object obj, Object obj2) {
        return a(i, obj, obj2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.u
    public boolean j(Object obj) {
        return !((MapFieldLite) obj).isMutable();
    }
}
