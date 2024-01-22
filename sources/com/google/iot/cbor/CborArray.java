package com.google.iot.cbor;

import com.clevertap.android.sdk.Constants;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public abstract class CborArray extends CborObject implements Iterable<CborObject> {
    public static /* synthetic */ void b(ArrayList arrayList, CborObject cborObject) {
        arrayList.add(cborObject.toJavaObject());
    }

    public static CborArray create() {
        return new b();
    }

    public static CborArray createFromJSONArray(JSONArray jSONArray) {
        CborObject createFromJavaObject;
        CborArray create = create();
        for (int i = 0; i < jSONArray.length(); i++) {
            if (jSONArray.isNull(i)) {
                createFromJavaObject = CborSimple.NULL;
            } else {
                Object obj = jSONArray.get(i);
                if (obj instanceof JSONArray) {
                    createFromJavaObject = createFromJSONArray((JSONArray) obj);
                } else if (obj instanceof JSONObject) {
                    createFromJavaObject = CborMap.createFromJSONObject((JSONObject) obj);
                } else {
                    try {
                        createFromJavaObject = CborObject.createFromJavaObject(obj);
                    } catch (CborConversionException e) {
                        throw new CborRuntimeException(e);
                    }
                }
            }
            create.add(createFromJavaObject);
        }
        return create;
    }

    public static CborArray createFromJavaObject(Iterable<?> iterable) throws CborConversionException {
        CborArray create = create();
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            create.add(CborObject.createFromJavaObject(it.next()));
        }
        return create;
    }

    public void add(CborObject cborObject) {
        listValue().add(cborObject);
    }

    public void clear() {
        listValue().clear();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CborArray) {
            CborArray cborArray = (CborArray) obj;
            return cborArray.getTag() == getTag() && listValue().equals(cborArray.listValue());
        }
        return false;
    }

    @Override // com.google.iot.cbor.CborObject
    public final int getAdditionalInformation() {
        return CborInteger.a(size());
    }

    @Override // com.google.iot.cbor.CborObject
    public final int getMajorType() {
        return 4;
    }

    public int hashCode() {
        return (Integer.hashCode(getTag()) * 1337) + listValue().hashCode();
    }

    public boolean isEmpty() {
        return listValue().isEmpty();
    }

    @Override // com.google.iot.cbor.CborObject
    public boolean isValidJson() {
        Iterator<CborObject> it = iterator();
        while (it.hasNext()) {
            if (!it.next().isValidJson()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.Iterable
    public Iterator<CborObject> iterator() {
        return listValue().iterator();
    }

    public abstract List<CborObject> listValue();

    @CanIgnoreReturnValue
    public boolean remove(CborObject cborObject) {
        return listValue().remove(cborObject);
    }

    public int size() {
        return listValue().size();
    }

    @Override // com.google.iot.cbor.CborObject
    public final String toJsonString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<CborObject> it = iterator();
        boolean z = true;
        while (it.hasNext()) {
            CborObject next = it.next();
            if (z) {
                z = false;
            } else {
                sb.append(Constants.SEPARATOR_COMMA);
            }
            sb.append(next.toJsonString());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.iot.cbor.CborObject
    public final String toString(int i) {
        int i2;
        StringBuilder sb = new StringBuilder("[");
        if (i >= 0) {
            i++;
        }
        Iterator<CborObject> it = iterator();
        boolean z = true;
        while (true) {
            i2 = 0;
            if (!it.hasNext()) {
                break;
            }
            CborObject next = it.next();
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
            sb.append(next.toString(i));
        }
        if (!isEmpty() && i > 0) {
            int i3 = i - 1;
            sb.append("\n");
            while (i2 < i3) {
                sb.append("\t");
                i2++;
            }
        }
        sb.append("]");
        int tag = getTag();
        if (tag == -1) {
            return sb.toString();
        }
        return tag + "(" + sb.toString() + ")";
    }

    public static CborArray create(int i) {
        if (CborTag.isValid(i)) {
            return new b(null, i);
        }
        throw new IllegalArgumentException("Invalid tag value " + i);
    }

    @Override // com.google.iot.cbor.CborObject
    public CborArray copy() {
        CborArray create = create(getTag());
        Iterator<CborObject> it = iterator();
        while (it.hasNext()) {
            create.add(it.next().copy());
        }
        return create;
    }

    @Override // com.google.iot.cbor.CborObject
    public List<Object> toJavaObject() {
        final ArrayList arrayList = new ArrayList();
        forEach(new Consumer() { // from class: com.google.iot.cbor.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CborArray.b(arrayList, (CborObject) obj);
            }
        });
        return arrayList;
    }

    public static CborArray createFromJavaObject(Object[] objArr) throws CborConversionException {
        CborArray create = create();
        for (Object obj : objArr) {
            create.add(CborObject.createFromJavaObject(obj));
        }
        return create;
    }

    @Override // com.google.iot.cbor.CborObject
    public <T> T toJavaObject(Class<T> cls) throws CborConversionException {
        if (cls.isAssignableFrom(List.class)) {
            return cls.cast(toJavaObject());
        }
        int i = 0;
        if (cls.isAssignableFrom(float[].class)) {
            float[] fArr = new float[size()];
            Iterator<CborObject> it = iterator();
            while (i < size()) {
                Float f = (Float) it.next().toJavaObject(Float.class);
                if (f != null) {
                    fArr[i] = f.floatValue();
                    i++;
                } else {
                    throw new CborConversionException("element is not assignable from null");
                }
            }
            return cls.cast(fArr);
        } else if (cls.isAssignableFrom(double[].class)) {
            double[] dArr = new double[size()];
            Iterator<CborObject> it2 = iterator();
            while (i < size()) {
                Double d = (Double) it2.next().toJavaObject(Double.class);
                if (d != null) {
                    dArr[i] = d.doubleValue();
                    i++;
                } else {
                    throw new CborConversionException("element is not assignable from null");
                }
            }
            return cls.cast(dArr);
        } else if (cls.isAssignableFrom(int[].class)) {
            int[] iArr = new int[size()];
            Iterator<CborObject> it3 = iterator();
            while (i < size()) {
                Integer num = (Integer) it3.next().toJavaObject(Integer.class);
                if (num != null) {
                    iArr[i] = num.intValue();
                    i++;
                } else {
                    throw new CborConversionException("element is not assignable from null");
                }
            }
            return cls.cast(iArr);
        } else if (cls.isAssignableFrom(short[].class)) {
            short[] sArr = new short[size()];
            Iterator<CborObject> it4 = iterator();
            while (i < size()) {
                Short sh = (Short) it4.next().toJavaObject(Short.class);
                if (sh != null) {
                    sArr[i] = sh.shortValue();
                    i++;
                } else {
                    throw new CborConversionException("element is not assignable from null");
                }
            }
            return cls.cast(sArr);
        } else if (cls.isAssignableFrom(long[].class)) {
            long[] jArr = new long[size()];
            Iterator<CborObject> it5 = iterator();
            while (i < size()) {
                Long l = (Long) it5.next().toJavaObject(Long.class);
                if (l != null) {
                    jArr[i] = l.longValue();
                    i++;
                } else {
                    throw new CborConversionException("element is not assignable from null");
                }
            }
            return cls.cast(jArr);
        } else if (cls.isAssignableFrom(String[].class)) {
            String[] strArr = new String[size()];
            Iterator<CborObject> it6 = iterator();
            while (i < size()) {
                String str = (String) it6.next().toJavaObject(String.class);
                if (str != null) {
                    strArr[i] = str;
                    i++;
                } else {
                    throw new CborConversionException("element is not assignable from null");
                }
            }
            return cls.cast(strArr);
        } else {
            throw new CborConversionException(cls + " is not assignable from array");
        }
    }

    public static CborArray create(Iterable<CborObject> iterable) {
        return create(iterable, -1);
    }

    public static CborArray create(Iterable<CborObject> iterable, int i) {
        if (CborTag.isValid(i)) {
            return new b(iterable, i);
        }
        throw new IllegalArgumentException("Invalid tag value " + i);
    }

    public static CborArray createFromJavaObject(int[] iArr) {
        CborArray create = create();
        for (int i : iArr) {
            create.add(CborInteger.create(i));
        }
        return create;
    }

    public static CborArray createFromJavaObject(short[] sArr) {
        CborArray create = create();
        for (short s : sArr) {
            create.add(CborInteger.create(s));
        }
        return create;
    }

    public static CborArray createFromJavaObject(long[] jArr) {
        CborArray create = create();
        for (long j : jArr) {
            create.add(CborInteger.create(j));
        }
        return create;
    }

    public static CborArray createFromJavaObject(boolean[] zArr) {
        CborArray create = create();
        for (boolean z : zArr) {
            create.add(z ? CborSimple.TRUE : CborSimple.FALSE);
        }
        return create;
    }

    public static CborArray createFromJavaObject(float[] fArr) {
        CborArray create = create();
        for (float f : fArr) {
            create.add(CborFloat.create(f));
        }
        return create;
    }

    public static CborArray createFromJavaObject(double[] dArr) {
        CborArray create = create();
        for (double d : dArr) {
            create.add(CborFloat.create(d));
        }
        return create;
    }
}
