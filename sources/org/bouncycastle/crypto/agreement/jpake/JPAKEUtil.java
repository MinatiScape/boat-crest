package org.bouncycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Strings;
/* loaded from: classes6.dex */
public class JPAKEUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final BigInteger f14624a = BigInteger.valueOf(0);
    public static final BigInteger b = BigInteger.valueOf(1);

    public static BigInteger a(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, String str, Digest digest) {
        digest.reset();
        g(digest, bigInteger);
        g(digest, bigInteger2);
        g(digest, bigInteger3);
        f(digest, str);
        byte[] bArr = new byte[digest.getDigestSize()];
        digest.doFinal(bArr, 0);
        return new BigInteger(bArr);
    }

    public static byte[] b(BigInteger bigInteger, Digest digest) {
        digest.reset();
        e(digest, bigInteger);
        d(digest, "JPAKE_KC");
        byte[] bArr = new byte[digest.getDigestSize()];
        digest.doFinal(bArr, 0);
        return bArr;
    }

    public static byte[] c(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }

    public static BigInteger calculateA(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        return bigInteger3.modPow(bigInteger4, bigInteger);
    }

    public static BigInteger calculateGA(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        return bigInteger2.multiply(bigInteger3).multiply(bigInteger4).mod(bigInteger);
    }

    public static BigInteger calculateGx(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return bigInteger2.modPow(bigInteger3, bigInteger);
    }

    public static BigInteger calculateKeyingMaterial(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6) {
        return bigInteger3.modPow(bigInteger4.multiply(bigInteger5).negate().mod(bigInteger2), bigInteger).multiply(bigInteger6).modPow(bigInteger4, bigInteger);
    }

    public static BigInteger calculateMacTag(String str, String str2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, Digest digest) {
        byte[] b2 = b(bigInteger5, digest);
        HMac hMac = new HMac(digest);
        byte[] bArr = new byte[hMac.getMacSize()];
        hMac.init(new KeyParameter(b2));
        h(hMac, "KC_1_U");
        h(hMac, str);
        h(hMac, str2);
        i(hMac, bigInteger);
        i(hMac, bigInteger2);
        i(hMac, bigInteger3);
        i(hMac, bigInteger4);
        hMac.doFinal(bArr, 0);
        Arrays.fill(b2, (byte) 0);
        return new BigInteger(bArr);
    }

    public static BigInteger calculateS(char[] cArr) {
        return new BigInteger(Strings.toUTF8ByteArray(cArr));
    }

    public static BigInteger calculateX2s(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return bigInteger2.multiply(bigInteger3).mod(bigInteger);
    }

    public static BigInteger[] calculateZeroKnowledgeProof(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, String str, Digest digest, SecureRandom secureRandom) {
        BigInteger createRandomInRange = BigIntegers.createRandomInRange(f14624a, bigInteger2.subtract(b), secureRandom);
        BigInteger modPow = bigInteger3.modPow(createRandomInRange, bigInteger);
        return new BigInteger[]{modPow, createRandomInRange.subtract(bigInteger5.multiply(a(bigInteger3, modPow, bigInteger4, str, digest))).mod(bigInteger2)};
    }

    public static void d(Digest digest, String str) {
        byte[] uTF8ByteArray = Strings.toUTF8ByteArray(str);
        digest.update(uTF8ByteArray, 0, uTF8ByteArray.length);
        Arrays.fill(uTF8ByteArray, (byte) 0);
    }

    public static void e(Digest digest, BigInteger bigInteger) {
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(bigInteger);
        digest.update(asUnsignedByteArray, 0, asUnsignedByteArray.length);
        Arrays.fill(asUnsignedByteArray, (byte) 0);
    }

    public static void f(Digest digest, String str) {
        byte[] uTF8ByteArray = Strings.toUTF8ByteArray(str);
        digest.update(c(uTF8ByteArray.length), 0, 4);
        digest.update(uTF8ByteArray, 0, uTF8ByteArray.length);
        Arrays.fill(uTF8ByteArray, (byte) 0);
    }

    public static void g(Digest digest, BigInteger bigInteger) {
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(bigInteger);
        digest.update(c(asUnsignedByteArray.length), 0, 4);
        digest.update(asUnsignedByteArray, 0, asUnsignedByteArray.length);
        Arrays.fill(asUnsignedByteArray, (byte) 0);
    }

    public static BigInteger generateX1(BigInteger bigInteger, SecureRandom secureRandom) {
        return BigIntegers.createRandomInRange(f14624a, bigInteger.subtract(b), secureRandom);
    }

    public static BigInteger generateX2(BigInteger bigInteger, SecureRandom secureRandom) {
        BigInteger bigInteger2 = b;
        return BigIntegers.createRandomInRange(bigInteger2, bigInteger.subtract(bigInteger2), secureRandom);
    }

    public static void h(Mac mac, String str) {
        byte[] uTF8ByteArray = Strings.toUTF8ByteArray(str);
        mac.update(uTF8ByteArray, 0, uTF8ByteArray.length);
        Arrays.fill(uTF8ByteArray, (byte) 0);
    }

    public static void i(Mac mac, BigInteger bigInteger) {
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(bigInteger);
        mac.update(asUnsignedByteArray, 0, asUnsignedByteArray.length);
        Arrays.fill(asUnsignedByteArray, (byte) 0);
    }

    public static void validateGa(BigInteger bigInteger) throws CryptoException {
        if (bigInteger.equals(b)) {
            throw new CryptoException("ga is equal to 1.  It should not be.  The chances of this happening are on the order of 2^160 for a 160-bit q.  Try again.");
        }
    }

    public static void validateGx4(BigInteger bigInteger) throws CryptoException {
        if (bigInteger.equals(b)) {
            throw new CryptoException("g^x validation failed.  g^x should not be 1.");
        }
    }

    public static void validateMacTag(String str, String str2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, Digest digest, BigInteger bigInteger6) throws CryptoException {
        if (!calculateMacTag(str2, str, bigInteger3, bigInteger4, bigInteger, bigInteger2, bigInteger5, digest).equals(bigInteger6)) {
            throw new CryptoException("Partner MacTag validation failed. Therefore, the password, MAC, or digest algorithm of each participant does not match.");
        }
    }

    public static void validateNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new NullPointerException(str + " must not be null");
    }

    public static void validateParticipantIdsDiffer(String str, String str2) throws CryptoException {
        if (str.equals(str2)) {
            throw new CryptoException("Both participants are using the same participantId (" + str + "). This is not allowed. Each participant must use a unique participantId.");
        }
    }

    public static void validateParticipantIdsEqual(String str, String str2) throws CryptoException {
        if (str.equals(str2)) {
            return;
        }
        throw new CryptoException("Received payload from incorrect partner (" + str2 + "). Expected to receive payload from " + str + ".");
    }

    public static void validateZeroKnowledgeProof(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger[] bigIntegerArr, String str, Digest digest) throws CryptoException {
        BigInteger bigInteger5 = bigIntegerArr[0];
        BigInteger bigInteger6 = bigIntegerArr[1];
        BigInteger a2 = a(bigInteger3, bigInteger5, bigInteger4, str, digest);
        if (bigInteger4.compareTo(f14624a) != 1 || bigInteger4.compareTo(bigInteger) != -1 || bigInteger4.modPow(bigInteger2, bigInteger).compareTo(b) != 0 || bigInteger3.modPow(bigInteger6, bigInteger).multiply(bigInteger4.modPow(a2, bigInteger)).mod(bigInteger).compareTo(bigInteger5) != 0) {
            throw new CryptoException("Zero-knowledge proof validation failed");
        }
    }
}
