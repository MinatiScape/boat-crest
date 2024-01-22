package com.google.iot.cbor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
/* loaded from: classes10.dex */
public final class CborSimple extends CborObject {

    /* renamed from: a  reason: collision with root package name */
    public int f11522a;
    public int b;
    public static final Map<Integer, CborSimple> c = new ConcurrentHashMap();
    public static final CborSimple TRUE = create(21);
    public static final CborSimple FALSE = create(20);
    public static final CborSimple NULL = create(22);
    public static final CborSimple UNDEFINED = create(23);

    public CborSimple(int i, int i2) {
        if (i < 0 || i > 255) {
            throw new IllegalArgumentException("Invalid simple value: out of range: " + i);
        } else if (i >= 24 && i <= 31) {
            throw new IllegalArgumentException("Reserved simple value " + i);
        } else if (CborTag.isValid(i2)) {
            this.f11522a = i;
            this.b = i2;
        } else {
            throw new IllegalArgumentException("Invalid tag value " + i2);
        }
    }

    public static /* synthetic */ CborSimple b(int i, Integer num) {
        return new CborSimple(i, -1);
    }

    public static CborSimple create(int i, int i2) {
        return i2 == -1 ? create(i) : new CborSimple(i, i2);
    }

    @Override // com.google.iot.cbor.CborObject
    public CborSimple copy() {
        return this;
    }

    public boolean equals(Object obj) {
        int i = this.b;
        if (i == -1) {
            return this == obj;
        } else if (obj instanceof CborSimple) {
            CborSimple cborSimple = (CborSimple) obj;
            return this.f11522a == cborSimple.f11522a && i == cborSimple.b;
        } else {
            return false;
        }
    }

    @Override // com.google.iot.cbor.CborObject
    public final int getAdditionalInformation() {
        int value = getValue();
        if (value < 24) {
            return value;
        }
        return 24;
    }

    @Override // com.google.iot.cbor.CborObject
    public final int getMajorType() {
        return 7;
    }

    @Override // com.google.iot.cbor.CborObject
    public int getTag() {
        return this.b;
    }

    public int getValue() {
        return this.f11522a;
    }

    public int hashCode() {
        return (this.b * 257) + this.f11522a;
    }

    @Override // com.google.iot.cbor.CborObject
    public final boolean isValidJson() {
        switch (getValue()) {
            case 20:
            case 21:
            case 22:
                return true;
            default:
                return false;
        }
    }

    @Override // com.google.iot.cbor.CborObject
    public Object toJavaObject() {
        int value = getValue();
        if (value != 20) {
            if (value != 21) {
                return null;
            }
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override // com.google.iot.cbor.CborObject
    public final String toJsonString() {
        int value = getValue();
        switch (value) {
            case 20:
                return "false";
            case 21:
                return "true";
            case 22:
                return "null";
            case 23:
                return "\"undefined\"";
            default:
                return "\"simple(" + value + ")\"";
        }
    }

    @Override // com.google.iot.cbor.CborObject
    public String toString(int i) {
        return toString();
    }

    public static CborSimple create(final int i) {
        return c.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: com.google.iot.cbor.h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                CborSimple b;
                b = CborSimple.b(i, (Integer) obj);
                return b;
            }
        });
    }

    @Override // com.google.iot.cbor.CborObject
    public final String toString() {
        String str;
        switch (this.f11522a) {
            case 20:
                str = "false";
                break;
            case 21:
                str = "true";
                break;
            case 22:
                str = "null";
                break;
            case 23:
                str = "undefined";
                break;
            default:
                str = "simple(" + this.f11522a + ")";
                break;
        }
        if (this.b == -1) {
            return str;
        }
        return this.b + "(" + str + ")";
    }

    @Override // com.google.iot.cbor.CborObject
    public <T> T toJavaObject(Class<T> cls) throws CborConversionException {
        if (this == NULL || this == UNDEFINED) {
            return null;
        }
        if (cls.isAssignableFrom(Boolean.class)) {
            int value = getValue();
            if (value != 20) {
                if (value == 21) {
                    return cls.cast(Boolean.TRUE);
                }
                throw new CborConversionException(this + " is not a boolean");
            }
            return cls.cast(Boolean.FALSE);
        } else if (Object.class.equals(cls)) {
            return null;
        } else {
            throw new CborConversionException(String.format("%s cannot be converted to a %s", this, cls));
        }
    }
}
