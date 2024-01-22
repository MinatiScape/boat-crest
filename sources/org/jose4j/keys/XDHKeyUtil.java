package org.jose4j.keys;

import java.math.BigInteger;
import java.security.Key;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.XECPrivateKey;
import java.security.interfaces.XECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.NamedParameterSpec;
import java.security.spec.XECPrivateKeySpec;
import java.security.spec.XECPublicKeySpec;
import java.util.Arrays;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class XDHKeyUtil extends OctetKeyPairUtil {
    public static final String X25519 = "X25519";
    public static final String X448 = "X448";

    /* renamed from: a  reason: collision with root package name */
    public static final BigInteger f15553a = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819949");
    public static final BigInteger b = new BigInteger("726838724295606890549323807888004534353641360687318060281490199180612328166730772686396383698676545930088884461843637361053498018365439");

    public XDHKeyUtil() {
        this(null, null);
    }

    public static boolean isXECPrivateKey(Key key) {
        try {
            return key instanceof XECPrivateKey;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    public static boolean isXECPublicKey(Key key) {
        try {
            return key instanceof XECPublicKey;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    @Override // org.jose4j.keys.a
    public String a() {
        return "XDH";
    }

    @Override // org.jose4j.keys.OctetKeyPairUtil
    public byte[] rawPrivateKey(PrivateKey privateKey) {
        return (byte[]) ((XECPrivateKey) privateKey).getScalar().orElse(ByteUtil.EMPTY_BYTES);
    }

    @Override // org.jose4j.keys.OctetKeyPairUtil
    public byte[] rawPublicKey(Key key) {
        XECPublicKey xECPublicKey = (XECPublicKey) key;
        BigInteger u = xECPublicKey.getU();
        boolean equals = "X25519".equals(xECPublicKey.getParams().getName());
        byte[] reverse = ByteUtil.reverse(u.mod(equals ? f15553a : b).toByteArray());
        int i = equals ? 32 : 57;
        return reverse.length != i ? Arrays.copyOf(reverse, i) : reverse;
    }

    public XDHKeyUtil(String str, SecureRandom secureRandom) {
        super(str, secureRandom);
    }

    @Override // org.jose4j.keys.OctetKeyPairUtil
    public XECPrivateKey privateKey(byte[] bArr, String str) throws JoseException {
        try {
            return getKeyFactory().generatePrivate(new XECPrivateKeySpec(b(str), bArr));
        } catch (InvalidKeySpecException e) {
            throw new JoseException("Invalid key spec: " + e, e);
        }
    }

    @Override // org.jose4j.keys.OctetKeyPairUtil
    public XECPublicKey publicKey(byte[] bArr, String str) throws JoseException {
        NamedParameterSpec b2 = b(str);
        byte[] reverse = ByteUtil.reverse(bArr);
        int i = ("X25519".equals(str) ? 255 : 448) % 8;
        if (i != 0) {
            reverse[0] = (byte) (((1 << i) - 1) & reverse[0]);
        }
        try {
            return getKeyFactory().generatePublic(new XECPublicKeySpec(b2, new BigInteger(1, reverse)));
        } catch (InvalidKeySpecException e) {
            throw new JoseException("Invalid key spec: " + e, e);
        }
    }
}
