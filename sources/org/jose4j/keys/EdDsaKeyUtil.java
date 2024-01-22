package org.jose4j.keys;

import java.security.Key;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.EdECPrivateKey;
import java.security.interfaces.EdECPublicKey;
import java.security.spec.EdECPoint;
import java.security.spec.EdECPrivateKeySpec;
import java.security.spec.EdECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class EdDsaKeyUtil extends OctetKeyPairUtil {
    public static final String ED25519 = "Ed25519";
    public static final String ED448 = "Ed448";

    public EdDsaKeyUtil() {
        this(null, null);
    }

    public static boolean isEdECPrivateKey(Key key) {
        try {
            return key instanceof EdECPrivateKey;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    public static boolean isEdECPublicKey(Key key) {
        try {
            return key instanceof EdECPublicKey;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    @Override // org.jose4j.keys.a
    public String a() {
        return "EDDSA";
    }

    @Override // org.jose4j.keys.OctetKeyPairUtil
    public byte[] rawPrivateKey(PrivateKey privateKey) {
        return (byte[]) ((EdECPrivateKey) privateKey).getBytes().orElse(ByteUtil.EMPTY_BYTES);
    }

    @Override // org.jose4j.keys.OctetKeyPairUtil
    public byte[] rawPublicKey(Key key) {
        EdECPublicKey edECPublicKey = (EdECPublicKey) key;
        EdECPoint point = edECPublicKey.getPoint();
        byte[] reverse = ByteUtil.reverse(point.getY().toByteArray());
        int i = edECPublicKey.getParams().getName().equals("Ed25519") ? 32 : 57;
        if (reverse.length != i) {
            reverse = Arrays.copyOf(reverse, i);
        }
        byte b = point.isXOdd() ? Byte.MIN_VALUE : (byte) 0;
        int length = reverse.length - 1;
        reverse[length] = (byte) (b | reverse[length]);
        return reverse;
    }

    public EdDsaKeyUtil(String str, SecureRandom secureRandom) {
        super(str, secureRandom);
    }

    @Override // org.jose4j.keys.OctetKeyPairUtil
    public EdECPrivateKey privateKey(byte[] bArr, String str) throws JoseException {
        try {
            return getKeyFactory().generatePrivate(new EdECPrivateKeySpec(b(str), bArr));
        } catch (InvalidKeySpecException e) {
            throw new JoseException("Invalid key spec: " + e, e);
        }
    }

    @Override // org.jose4j.keys.OctetKeyPairUtil
    public EdECPublicKey publicKey(byte[] bArr, String str) throws JoseException {
        byte[] bArr2 = (byte[]) bArr.clone();
        byte b = bArr2[bArr2.length - 1];
        int length = bArr2.length - 1;
        bArr2[length] = (byte) (bArr2[length] & Byte.MAX_VALUE);
        try {
            return getKeyFactory().generatePublic(new EdECPublicKeySpec(b(str), new EdECPoint((b & Byte.MIN_VALUE) != 0, BigEndianBigInteger.fromBytes(ByteUtil.reverse(bArr2)))));
        } catch (InvalidKeySpecException e) {
            throw new JoseException("Invalid key spec: " + e, e);
        }
    }
}
