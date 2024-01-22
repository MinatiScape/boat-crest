package com.google.iot.cbor;

import java.util.Arrays;
import java.util.Base64;
/* loaded from: classes10.dex */
public abstract class CborByteString extends CborObject {
    public static CborByteString create(byte[] bArr, int i, int i2, int i3) {
        if (CborTag.isValid(i3)) {
            return new c(bArr, i, i2, i3);
        }
        throw new IllegalArgumentException("Invalid tag value " + i3);
    }

    public final String a() {
        StringBuilder sb = new StringBuilder();
        byte[] byteArrayValue = byteArrayValue();
        int length = byteArrayValue.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", Byte.valueOf(byteArrayValue[i])));
        }
        return sb.toString();
    }

    public abstract byte[] byteArrayValue();

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CborByteString) {
            CborByteString cborByteString = (CborByteString) obj;
            return getTag() == cborByteString.getTag() && Arrays.equals(byteArrayValue(), cborByteString.byteArrayValue());
        }
        return false;
    }

    @Override // com.google.iot.cbor.CborObject
    public int getAdditionalInformation() {
        return CborInteger.a(byteArrayValue().length);
    }

    @Override // com.google.iot.cbor.CborObject
    public int getMajorType() {
        return 2;
    }

    public int hashCode() {
        return (Integer.hashCode(getTag()) * 1337) + Arrays.hashCode(byteArrayValue());
    }

    @Override // com.google.iot.cbor.CborObject
    public boolean isValidJson() {
        int tag = getTag();
        return tag == 22 || tag == 23;
    }

    @Override // com.google.iot.cbor.CborObject
    public String toJsonString() {
        if (getTag() == 23) {
            return "\"" + a() + "\"";
        }
        return "\"" + Base64.getEncoder().encodeToString(byteArrayValue()) + "\"";
    }

    @Override // com.google.iot.cbor.CborObject
    public String toString(int i) {
        return toString();
    }

    @Override // com.google.iot.cbor.CborObject
    public CborByteString copy() {
        byte[] byteArrayValue = byteArrayValue();
        return create(byteArrayValue, 0, byteArrayValue.length, getTag());
    }

    @Override // com.google.iot.cbor.CborObject
    public byte[] toJavaObject() {
        byte[] byteArrayValue = byteArrayValue();
        return Arrays.copyOf(byteArrayValue, byteArrayValue.length);
    }

    @Override // com.google.iot.cbor.CborObject
    public String toString() {
        String str = "h'" + a() + "'";
        int tag = getTag();
        if (tag == -1) {
            return str;
        }
        return tag + "(" + str + ")";
    }

    public static CborByteString create(byte[] bArr, int i, int i2) {
        return create(bArr, i, i2, -1);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c0  */
    @Override // com.google.iot.cbor.CborObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <T> T toJavaObject(java.lang.Class<T> r5) throws com.google.iot.cbor.CborConversionException {
        /*
            r4 = this;
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            int r1 = r4.getTag()
            r2 = 2
            if (r1 == r2) goto L97
            r2 = 3
            if (r1 == r2) goto L80
            switch(r1) {
                case 22: goto L6a;
                case 23: goto L5b;
                case 24: goto L11;
                default: goto Lf;
            }
        Lf:
            goto Lae
        L11:
            java.lang.Class<com.google.iot.cbor.CborObject> r1 = com.google.iot.cbor.CborObject.class
            boolean r1 = r1.isAssignableFrom(r5)
            if (r1 != 0) goto L1f
            boolean r0 = r5.isAssignableFrom(r0)
            if (r0 == 0) goto Lae
        L1f:
            byte[] r0 = r4.byteArrayValue()     // Catch: com.google.iot.cbor.CborParseException -> L54
            com.google.iot.cbor.CborObject r0 = com.google.iot.cbor.CborObject.createFromCborByteArray(r0)     // Catch: com.google.iot.cbor.CborParseException -> L54
            java.lang.Class r1 = r0.getClass()     // Catch: com.google.iot.cbor.CborParseException -> L54
            boolean r1 = r5.isAssignableFrom(r1)     // Catch: com.google.iot.cbor.CborParseException -> L54
            if (r1 == 0) goto L36
            java.lang.Object r5 = r5.cast(r0)     // Catch: com.google.iot.cbor.CborParseException -> L54
            return r5
        L36:
            com.google.iot.cbor.CborConversionException r1 = new com.google.iot.cbor.CborConversionException     // Catch: com.google.iot.cbor.CborParseException -> L54
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: com.google.iot.cbor.CborParseException -> L54
            r2.<init>()     // Catch: com.google.iot.cbor.CborParseException -> L54
            r2.append(r5)     // Catch: com.google.iot.cbor.CborParseException -> L54
            java.lang.String r3 = " is not assignable from "
            r2.append(r3)     // Catch: com.google.iot.cbor.CborParseException -> L54
            java.lang.Class r0 = r0.getClass()     // Catch: com.google.iot.cbor.CborParseException -> L54
            r2.append(r0)     // Catch: com.google.iot.cbor.CborParseException -> L54
            java.lang.String r0 = r2.toString()     // Catch: com.google.iot.cbor.CborParseException -> L54
            r1.<init>(r0)     // Catch: com.google.iot.cbor.CborParseException -> L54
            goto Laf
        L54:
            r0 = move-exception
            com.google.iot.cbor.CborConversionException r1 = new com.google.iot.cbor.CborConversionException
            r1.<init>(r0)
            goto Laf
        L5b:
            boolean r0 = r5.isAssignableFrom(r0)
            if (r0 == 0) goto Lae
            java.lang.String r0 = r4.a()
            java.lang.Object r5 = r5.cast(r0)
            return r5
        L6a:
            boolean r0 = r5.isAssignableFrom(r0)
            if (r0 == 0) goto Lae
            java.util.Base64$Encoder r0 = java.util.Base64.getEncoder()
            byte[] r1 = r4.byteArrayValue()
            java.lang.String r0 = r0.encodeToString(r1)
            r5.cast(r0)
            goto Lae
        L80:
            java.lang.Class<java.math.BigInteger> r0 = java.math.BigInteger.class
            boolean r0 = r5.isAssignableFrom(r0)
            if (r0 == 0) goto Lae
            java.math.BigInteger r0 = new java.math.BigInteger
            r1 = -1
            byte[] r2 = r4.byteArrayValue()
            r0.<init>(r1, r2)
            java.lang.Object r5 = r5.cast(r0)
            return r5
        L97:
            java.lang.Class<java.math.BigInteger> r0 = java.math.BigInteger.class
            boolean r0 = r5.isAssignableFrom(r0)
            if (r0 == 0) goto Lae
            java.math.BigInteger r0 = new java.math.BigInteger
            r1 = 1
            byte[] r2 = r4.byteArrayValue()
            r0.<init>(r1, r2)
            java.lang.Object r5 = r5.cast(r0)
            return r5
        Lae:
            r1 = 0
        Laf:
            java.lang.Class<byte[]> r0 = byte[].class
            boolean r0 = r5.isAssignableFrom(r0)
            if (r0 == 0) goto Lc0
            byte[] r0 = r4.toJavaObject()
            java.lang.Object r5 = r5.cast(r0)
            return r5
        Lc0:
            if (r1 == 0) goto Lc3
            throw r1
        Lc3:
            com.google.iot.cbor.CborConversionException r0 = new com.google.iot.cbor.CborConversionException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r5)
            java.lang.String r5 = " is not assignable from byte string"
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.iot.cbor.CborByteString.toJavaObject(java.lang.Class):java.lang.Object");
    }

    public static CborByteString create(byte[] bArr) {
        return create(bArr, 0, bArr.length);
    }
}
