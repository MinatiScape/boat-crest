package com.google.iot.cbor;

import com.clevertap.android.sdk.Constants;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public abstract class CborMap extends CborObject {
    public static CborMap create(int i) {
        return new f(i);
    }

    public static CborMap createFromCborByteArray(byte[] bArr, int i, int i2) throws CborParseException {
        CborObject createFromCborByteArray = CborObject.createFromCborByteArray(bArr, i, i2);
        if (createFromCborByteArray instanceof CborMap) {
            return (CborMap) createFromCborByteArray;
        }
        throw new CborParseException("Not a map");
    }

    public static CborMap createFromJSONObject(JSONObject jSONObject) {
        CborObject createFromJavaObject;
        CborMap create = create();
        for (String str : jSONObject.keySet()) {
            if (jSONObject.isNull(str)) {
                createFromJavaObject = CborSimple.NULL;
            } else {
                Object obj = jSONObject.get(str);
                if (obj instanceof JSONArray) {
                    createFromJavaObject = CborArray.createFromJSONArray((JSONArray) obj);
                } else if (obj instanceof JSONObject) {
                    createFromJavaObject = createFromJSONObject((JSONObject) obj);
                } else {
                    try {
                        createFromJavaObject = CborObject.createFromJavaObject(obj);
                    } catch (CborConversionException e) {
                        throw new CborRuntimeException(e);
                    }
                }
            }
            create.put(str, createFromJavaObject);
        }
        return create;
    }

    public static CborMap createFromJavaObject(Map<?, ?> map) throws CborConversionException {
        CborMap create = create();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            create.mapValue().put(CborObject.createFromJavaObject(entry.getKey()), CborObject.createFromJavaObject(entry.getValue()));
        }
        return create;
    }

    public final boolean areAllKeysStrings() {
        for (CborObject cborObject : mapValue().keySet()) {
            if (!(cborObject instanceof CborTextString)) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        mapValue().clear();
    }

    public final boolean containsKey(String str) {
        return mapValue().containsKey(CborTextString.create(str));
    }

    public Set<Map.Entry<CborObject, CborObject>> entrySet() {
        return mapValue().entrySet();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CborMap) {
            CborMap cborMap = (CborMap) obj;
            return cborMap.getTag() == getTag() && mapValue().equals(cborMap.mapValue());
        }
        return false;
    }

    public CborObject get(CborObject cborObject) {
        return mapValue().get(cborObject);
    }

    @Override // com.google.iot.cbor.CborObject
    public final int getAdditionalInformation() {
        return CborInteger.a(mapValue().size());
    }

    @Override // com.google.iot.cbor.CborObject
    public final int getMajorType() {
        return 5;
    }

    public int hashCode() {
        return (Integer.hashCode(getTag()) * 1337) + mapValue().hashCode();
    }

    public boolean isEmpty() {
        return mapValue().isEmpty();
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0012  */
    @Override // com.google.iot.cbor.CborObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isValidJson() {
        /*
            r4 = this;
            java.util.Map r0 = r4.mapValue()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        Lc:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L2f
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            boolean r2 = r2 instanceof com.google.iot.cbor.CborTextString
            r3 = 0
            if (r2 != 0) goto L22
            return r3
        L22:
            java.lang.Object r1 = r1.getValue()
            com.google.iot.cbor.CborObject r1 = (com.google.iot.cbor.CborObject) r1
            boolean r1 = r1.isValidJson()
            if (r1 != 0) goto Lc
            return r3
        L2f:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.iot.cbor.CborMap.isValidJson():boolean");
    }

    public Set<CborObject> keySet() {
        return mapValue().keySet();
    }

    public final Set<String> keySetAsStrings() throws CborConversionException {
        HashSet hashSet = new HashSet();
        for (CborObject cborObject : mapValue().keySet()) {
            if (cborObject instanceof CborTextString) {
                hashSet.add(((CborTextString) cborObject).stringValue());
            } else {
                throw new CborConversionException("Key is not a string");
            }
        }
        return hashSet;
    }

    public abstract Map<CborObject, CborObject> mapValue();

    @CanIgnoreReturnValue
    public final CborObject put(String str, CborObject cborObject) {
        return mapValue().put(CborTextString.create(str), cborObject);
    }

    @CanIgnoreReturnValue
    public CborObject remove(CborObject cborObject) {
        return mapValue().remove(cborObject);
    }

    public int size() {
        return mapValue().size();
    }

    @Override // com.google.iot.cbor.CborObject
    public final String toJsonString() {
        StringBuilder sb = new StringBuilder("{");
        boolean z = true;
        for (Map.Entry<CborObject, CborObject> entry : mapValue().entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(Constants.SEPARATOR_COMMA);
            }
            if (entry.getKey() instanceof CborTextString) {
                sb.append(entry.getKey().toJsonString());
            } else {
                sb.append(JSONObject.quote(entry.getKey().toJsonString()));
            }
            sb.append(":");
            sb.append(entry.getValue().toJsonString());
        }
        sb.append("}");
        return sb.toString();
    }

    public final Map<String, Object> toNormalMap() throws CborConversionException {
        if (areAllKeysStrings()) {
            return toJavaObject();
        }
        throw new CborConversionException("Not all keys are strings");
    }

    @Override // com.google.iot.cbor.CborObject
    public String toString(int i) {
        int i2;
        StringBuilder sb = new StringBuilder("{");
        if (i >= 0) {
            i++;
        }
        Iterator<Map.Entry<CborObject, CborObject>> it = mapValue().entrySet().iterator();
        boolean z = true;
        while (true) {
            i2 = 0;
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<CborObject, CborObject> next = it.next();
            if (z) {
                z = false;
            } else {
                sb.append(Constants.SEPARATOR_COMMA);
            }
            if (i >= 0) {
                sb.append("\n");
                while (i2 < i) {
                    sb.append("\t");
                    i2++;
                }
            }
            sb.append(next.getKey().toString(i));
            sb.append(":");
            sb.append(next.getValue().toString(i));
        }
        if (!isEmpty() && i > 0) {
            int i3 = i - 1;
            sb.append("\n");
            while (i2 < i3) {
                sb.append("\t");
                i2++;
            }
        }
        sb.append("}");
        int tag = getTag();
        if (tag == -1) {
            return sb.toString();
        }
        return tag + "(" + sb.toString() + ")";
    }

    public static CborMap create(Map<CborObject, CborObject> map, int i) {
        return new f(map, i);
    }

    @Override // com.google.iot.cbor.CborObject
    public final CborMap copy() {
        CborMap create = create(getTag());
        for (Map.Entry<CborObject, CborObject> entry : mapValue().entrySet()) {
            create.mapValue().put(entry.getKey().copy(), entry.getValue().copy());
        }
        return create;
    }

    public final CborObject get(String str) {
        return mapValue().get(CborTextString.create(str));
    }

    @CanIgnoreReturnValue
    public final CborObject remove(String str) {
        return mapValue().remove(CborTextString.create(str));
    }

    @Override // com.google.iot.cbor.CborObject
    public final Map<Object, Object> toJavaObject() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<CborObject, CborObject> entry : mapValue().entrySet()) {
            linkedHashMap.put(entry.getKey().toJavaObject(), entry.getValue().toJavaObject());
        }
        return linkedHashMap;
    }

    public static CborMap create(Map<CborObject, CborObject> map) {
        return create(map, -1);
    }

    public static CborMap create() {
        return create(-1);
    }

    public static CborMap createFromCborByteArray(byte[] bArr) throws CborParseException {
        return createFromCborByteArray(bArr, 0, bArr.length);
    }

    @Override // com.google.iot.cbor.CborObject
    public <T> T toJavaObject(Class<T> cls) throws CborConversionException {
        if (cls.isAssignableFrom(Map.class)) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<CborObject, CborObject> entry : mapValue().entrySet()) {
                linkedHashMap.put(entry.getKey().toJavaObject(Object.class), entry.getValue().toJavaObject(Object.class));
            }
            return cls.cast(linkedHashMap);
        }
        throw new CborConversionException(cls + " is not assignable from map");
    }
}
