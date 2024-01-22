package com.google.protobuf;

import com.google.protobuf.MapEntryLite;
import java.util.Map;
/* loaded from: classes11.dex */
public class x implements w {
    public static <K, V> int a(int i, Object obj, Object obj2) {
        int i2 = 0;
        if (obj == null) {
            return 0;
        }
        Map<K, V> map = ((MapField) obj).getMap();
        MapEntry mapEntry = (MapEntry) obj2;
        if (map.isEmpty()) {
            return 0;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            i2 += CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(MapEntryLite.computeSerializedSize(mapEntry.getMetadata(), entry.getKey(), entry.getValue()));
        }
        return i2;
    }

    public static <K, V> Object b(Object obj, Object obj2) {
        MapField mapField = (MapField) obj;
        MapField<K, V> mapField2 = (MapField) obj2;
        if (!mapField.isMutable()) {
            mapField.copy();
        }
        mapField.mergeFrom(mapField2);
        return mapField;
    }

    @Override // com.google.protobuf.w
    public Object c(Object obj, Object obj2) {
        return b(obj, obj2);
    }

    @Override // com.google.protobuf.w
    public MapEntryLite.b<?, ?> d(Object obj) {
        return ((MapEntry) obj).getMetadata();
    }

    @Override // com.google.protobuf.w
    public Map<?, ?> e(Object obj) {
        return ((MapField) obj).getMutableMap();
    }

    @Override // com.google.protobuf.w
    public Object f(Object obj) {
        return MapField.newMapField((MapEntry) obj);
    }

    @Override // com.google.protobuf.w
    public Map<?, ?> g(Object obj) {
        return ((MapField) obj).getMap();
    }

    @Override // com.google.protobuf.w
    public Object h(Object obj) {
        ((MapField) obj).makeImmutable();
        return obj;
    }

    @Override // com.google.protobuf.w
    public int i(int i, Object obj, Object obj2) {
        return a(i, obj, obj2);
    }

    @Override // com.google.protobuf.w
    public boolean j(Object obj) {
        return !((MapField) obj).isMutable();
    }
}
