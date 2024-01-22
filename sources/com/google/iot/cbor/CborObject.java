package com.google.iot.cbor;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Map;
/* loaded from: classes10.dex */
public abstract class CborObject {
    public static CborObject createFromCborByteArray(byte[] bArr, int i, int i2) throws CborParseException {
        if (i >= 0) {
            if (i2 >= 1) {
                if (bArr.length >= i) {
                    if (bArr.length >= i + i2) {
                        try {
                            CborReader createFromByteArray = CborReader.createFromByteArray(bArr, i, 1);
                            CborObject readDataItem = createFromByteArray.readDataItem();
                            long j = i2;
                            if (createFromByteArray.bytesParsed() <= j) {
                                if (createFromByteArray.bytesParsed() >= j) {
                                    return readDataItem;
                                }
                                throw new CborParseException("extra data at end of data item (parsed only " + createFromByteArray.bytesParsed() + " of " + i2 + " bytes)");
                            }
                            throw new CborParseException("data item is truncated");
                        } catch (IOException e) {
                            throw new AssertionError(e);
                        }
                    }
                    throw new IndexOutOfBoundsException("offset+length is larger than byte array");
                }
                throw new IndexOutOfBoundsException("offset is larger than byte array");
            }
            throw new IllegalArgumentException("length must be greater than 1");
        }
        throw new IllegalArgumentException("offset cannot be negative");
    }

    public static CborObject createFromJavaObject(Object obj) throws CborConversionException {
        int i;
        if (obj == null) {
            return CborSimple.NULL;
        }
        if (obj instanceof CborObject) {
            byte[] cborByteArray = ((CborObject) obj).toCborByteArray();
            return CborByteString.create(cborByteArray, 0, cborByteArray.length, 24);
        } else if (obj instanceof Float) {
            return CborFloat.create(((Float) obj).floatValue());
        } else {
            if (obj instanceof Double) {
                return CborFloat.create(((Double) obj).doubleValue());
            }
            if (obj instanceof Integer) {
                return CborInteger.create(((Integer) obj).intValue());
            }
            if (obj instanceof Long) {
                return CborInteger.create(((Long) obj).longValue());
            }
            if (obj instanceof Short) {
                return CborInteger.create(((Short) obj).shortValue());
            }
            if (obj instanceof String) {
                return CborTextString.create((String) obj);
            }
            if (obj instanceof byte[]) {
                return CborByteString.create((byte[]) obj);
            }
            if (obj instanceof Boolean) {
                if (((Boolean) obj).booleanValue()) {
                    return CborSimple.TRUE;
                }
                return CborSimple.FALSE;
            } else if (obj instanceof Map) {
                return CborMap.createFromJavaObject((Map<?, ?>) obj);
            } else {
                if (obj instanceof Iterable) {
                    return CborArray.createFromJavaObject((Iterable<?>) obj);
                }
                if (obj instanceof URI) {
                    return CborTextString.create(((URI) obj).toASCIIString(), 32);
                }
                if (obj instanceof BigInteger) {
                    BigInteger bigInteger = (BigInteger) obj;
                    if (bigInteger.signum() < 0) {
                        i = 3;
                        bigInteger = bigInteger.negate();
                    } else {
                        i = 2;
                    }
                    byte[] byteArray = bigInteger.toByteArray();
                    if (byteArray.length > 0 && byteArray[0] == 0) {
                        return CborByteString.create(byteArray, 1, byteArray.length - 1, i);
                    }
                    return CborByteString.create(byteArray, 0, byteArray.length, i);
                } else if (obj instanceof int[]) {
                    return CborArray.createFromJavaObject((int[]) obj);
                } else {
                    if (obj instanceof short[]) {
                        return CborArray.createFromJavaObject((short[]) obj);
                    }
                    if (obj instanceof long[]) {
                        return CborArray.createFromJavaObject((long[]) obj);
                    }
                    if (obj instanceof boolean[]) {
                        return CborArray.createFromJavaObject((boolean[]) obj);
                    }
                    if (obj instanceof float[]) {
                        return CborArray.createFromJavaObject((float[]) obj);
                    }
                    if (obj instanceof double[]) {
                        return CborArray.createFromJavaObject((double[]) obj);
                    }
                    if (obj instanceof Object[]) {
                        return CborArray.createFromJavaObject((Object[]) obj);
                    }
                    throw new CborConversionException("Unable to convert java type \"" + obj.getClass().getCanonicalName() + "\" to CborObject");
                }
            }
        }
    }

    public abstract CborObject copy();

    public abstract int getAdditionalInformation();

    public abstract int getMajorType();

    public int getTag() {
        return -1;
    }

    public abstract boolean isValidJson();

    public final byte[] toCborByteArray() {
        ByteBuffer allocate = ByteBuffer.allocate(CborWriter.length(this));
        try {
            CborWriter.createFromByteBuffer(allocate).writeDataItem(this);
            return allocate.array();
        } catch (IOException e) {
            throw new CborRuntimeException(e);
        }
    }

    public abstract Object toJavaObject();

    public abstract <T> T toJavaObject(Class<T> cls) throws CborConversionException;

    public abstract String toJsonString();

    public String toString() {
        return toString(-1);
    }

    public abstract String toString(int i);

    public static CborObject createFromCborByteArray(byte[] bArr) throws CborParseException {
        return createFromCborByteArray(bArr, 0, bArr.length);
    }
}
