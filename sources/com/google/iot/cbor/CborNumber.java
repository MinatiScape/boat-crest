package com.google.iot.cbor;
/* loaded from: classes10.dex */
public interface CborNumber {
    default byte byteValueExact() {
        long longValue = longValue();
        if (longValue > 127 || longValue < -128) {
            throw new ArithmeticException("Value does not fit in a byte");
        }
        return (byte) longValue;
    }

    double doubleValue();

    float floatValue();

    default int intValueExact() {
        long longValue = longValue();
        if (longValue > 2147483647L || longValue < -2147483648L) {
            throw new ArithmeticException("Value does not fit in a int");
        }
        return (int) longValue;
    }

    long longValue();

    default short shortValueExact() {
        long longValue = longValue();
        if (longValue > 32767 || longValue < -32768) {
            throw new ArithmeticException("Value does not fit in a short");
        }
        return (short) longValue;
    }
}
