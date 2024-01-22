package com.google.crypto.tink.hybrid.subtle;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
/* loaded from: classes10.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f10850a = new byte[0];

    public static int a(BigInteger bigInteger) {
        return (bigInteger.bitLength() + 7) / 8;
    }

    public static byte[] b(BigInteger bigInteger, int i) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == i) {
            return byteArray;
        }
        byte[] bArr = new byte[i];
        if (byteArray.length == i + 1) {
            if (byteArray[0] == 0) {
                System.arraycopy(byteArray, 1, bArr, 0, i);
            } else {
                throw new IllegalArgumentException("Value is one-byte longer than the expected size, but its first byte is not 0");
            }
        } else if (byteArray.length < i) {
            System.arraycopy(byteArray, 0, bArr, i - byteArray.length, byteArray.length);
        } else {
            throw new IllegalArgumentException(String.format("Value has invalid length, must be of length at most (%d + 1), but got %d", Integer.valueOf(i), Integer.valueOf(byteArray.length)));
        }
        return bArr;
    }

    public static byte[] c(BigInteger bigInteger) {
        int a2 = a(bigInteger);
        SecureRandom secureRandom = new SecureRandom();
        while (true) {
            BigInteger bigInteger2 = new BigInteger(bigInteger.bitLength(), secureRandom);
            if (bigInteger2.signum() > 0 && bigInteger2.compareTo(bigInteger) < 0) {
                return b(bigInteger2, a2);
            }
        }
    }

    public static void d(BigInteger bigInteger) throws GeneralSecurityException {
        if (bigInteger.bitLength() < 2048) {
            throw new GeneralSecurityException(String.format("RSA key must be of at least size %d bits, but got %d", 2048, Integer.valueOf(bigInteger.bitLength())));
        }
    }
}
