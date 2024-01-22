package com.google.iot.cbor;
/* loaded from: classes10.dex */
public abstract class CborFloat extends CborObject implements CborNumber {
    public static final int TYPE_DOUBLE = 27;
    public static final int TYPE_FLOAT = 26;
    public static final int TYPE_HALF = 25;

    public static CborFloat create(float f) {
        return create(f, -1);
    }

    public static CborFloat createHalf(float f) {
        return createHalf(f, -1);
    }

    @Override // com.google.iot.cbor.CborObject
    public final CborFloat copy() {
        return this;
    }

    @Override // com.google.iot.cbor.CborNumber
    public abstract double doubleValue();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof CborObject) && getTag() == ((CborObject) obj).getTag()) {
            if (obj instanceof CborFloat) {
                return Double.doubleToRawLongBits(doubleValue()) == Double.doubleToRawLongBits(((CborFloat) obj).doubleValue());
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
    public abstract float floatValue();

    @Override // com.google.iot.cbor.CborObject
    public final int getMajorType() {
        return 7;
    }

    public final int hashCode() {
        return (((getTag() + 1) * 1337) + Double.hashCode(doubleValue())) ^ Long.hashCode(longValue());
    }

    @Override // com.google.iot.cbor.CborObject
    public final boolean isValidJson() {
        return Double.isFinite(doubleValue());
    }

    @Override // com.google.iot.cbor.CborNumber
    public final long longValue() {
        return (long) doubleValue();
    }

    @Override // com.google.iot.cbor.CborObject
    public final String toJsonString() {
        double doubleValue = doubleValue();
        if (isValidJson()) {
            int additionalInformation = getAdditionalInformation();
            if (additionalInformation != 25 && additionalInformation != 26) {
                return Double.toString(doubleValue);
            }
            return Float.toString((float) doubleValue);
        }
        return "null";
    }

    @Override // com.google.iot.cbor.CborObject
    public String toString(int i) {
        return toString();
    }

    public static CborFloat create(float f, int i) {
        return new d(f, i);
    }

    public static CborFloat createHalf(float f, int i) {
        return d.createHalf(f, i);
    }

    @Override // com.google.iot.cbor.CborObject
    public Number toJavaObject() {
        if (getAdditionalInformation() == 27) {
            return Double.valueOf(doubleValue());
        }
        return Float.valueOf(floatValue());
    }

    @Override // com.google.iot.cbor.CborObject
    public String toString() {
        int additionalInformation = getAdditionalInformation();
        String d = additionalInformation == 27 ? Double.toString(doubleValue()) : Float.toString(floatValue());
        StringBuilder sb = new StringBuilder();
        sb.append(d);
        sb.append("_");
        sb.append(additionalInformation - 24);
        String sb2 = sb.toString();
        int tag = getTag();
        if (tag == -1) {
            return sb2;
        }
        return tag + "(" + sb2 + ")";
    }

    public static CborFloat create(double d) {
        return create(d, -1);
    }

    public static CborFloat create(double d, int i) {
        return new d(d, i);
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
                throw new CborConversionException(String.format("%s is not assignable from %s", cls, Double.class));
            } catch (ArithmeticException e) {
                throw new CborConversionException(e);
            }
        }
        return cls.cast(toJavaObject());
    }
}
