package com.google.iot.cbor;

import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes10.dex */
public abstract class CborInteger extends CborObject implements CborNumber {
    public static int a(long j) {
        if (j < 0) {
            j = (-j) - 1;
        }
        if (j < 24) {
            return (byte) j;
        }
        if (j <= 255) {
            return 24;
        }
        if (j <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            return 25;
        }
        return j <= 4294967295L ? 26 : 27;
    }

    public static CborInteger create(long j) {
        return create(j, -1);
    }

    @Override // com.google.iot.cbor.CborObject
    public final CborInteger copy() {
        return this;
    }

    @Override // com.google.iot.cbor.CborNumber
    public final double doubleValue() {
        return longValue();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof CborObject) && getTag() == ((CborObject) obj).getTag()) {
            if (obj instanceof CborInteger) {
                return longValue() == ((CborInteger) obj).longValue();
            } else if (obj instanceof CborNumber) {
                CborNumber cborNumber = (CborNumber) obj;
                return longValue() == cborNumber.longValue() && Double.doubleToRawLongBits(doubleValue()) == Double.doubleToRawLongBits(cborNumber.doubleValue());
            } else {
                return false;
            }
        }
        return false;
    }

    @Override // com.google.iot.cbor.CborNumber
    public final float floatValue() {
        return (float) longValue();
    }

    @Override // com.google.iot.cbor.CborObject
    public final int getAdditionalInformation() {
        return a(longValue());
    }

    @Override // com.google.iot.cbor.CborObject
    public final int getMajorType() {
        return longValue() < 0 ? 1 : 0;
    }

    public final int hashCode() {
        return (((getTag() + 1) * 1337) + Double.hashCode(doubleValue())) ^ Long.hashCode(longValue());
    }

    @Override // com.google.iot.cbor.CborObject
    public final boolean isValidJson() {
        return true;
    }

    @Override // com.google.iot.cbor.CborNumber
    public abstract long longValue();

    @Override // com.google.iot.cbor.CborObject
    public final String toJsonString() {
        return Long.toString(longValue());
    }

    @Override // com.google.iot.cbor.CborObject
    public String toString(int i) {
        return toString();
    }

    public static CborInteger create(long j, int i) {
        return new e(j, i);
    }

    @Override // com.google.iot.cbor.CborObject
    public Number toJavaObject() {
        long longValue = longValue();
        if (longValue <= 2147483647L && longValue >= -2147483648L) {
            return Integer.valueOf((int) longValue);
        }
        return Long.valueOf(longValue);
    }

    @Override // com.google.iot.cbor.CborObject
    public String toString() {
        String l = Long.toString(longValue());
        int tag = getTag();
        if (tag == -1) {
            return l;
        }
        return tag + "(" + l + ")";
    }

    @Override // com.google.iot.cbor.CborObject
    public <T> T toJavaObject(Class<T> cls) throws CborConversionException {
        if (!cls.isAssignableFrom(Number.class) && !Object.class.equals(cls)) {
            if (cls.isAssignableFrom(Float.class)) {
                return cls.cast(Float.valueOf(floatValue()));
            }
            if (cls.isAssignableFrom(Double.class)) {
                return cls.cast(Double.valueOf(doubleValue()));
            }
            if (cls.isAssignableFrom(Long.class)) {
                return cls.cast(Long.valueOf(longValue()));
            }
            try {
                if (cls.isAssignableFrom(Integer.class)) {
                    return cls.cast(Integer.valueOf(intValueExact()));
                }
                if (cls.isAssignableFrom(Short.class)) {
                    return cls.cast(Short.valueOf(shortValueExact()));
                }
                throw new CborConversionException(String.format("%s is not assignable from %s", cls, Long.class));
            } catch (ArithmeticException e) {
                throw new CborConversionException(e);
            }
        }
        return cls.cast(toJavaObject());
    }
}
