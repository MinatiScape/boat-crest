package org.jose4j.jws;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECKey;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.keys.BigEndianBigInteger;
import org.jose4j.keys.EllipticCurves;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class EcdsaUsingShaAlgorithm extends BaseSignatureAlgorithm {
    public final String g;
    public final int h;

    /* loaded from: classes13.dex */
    public static class EcdsaP256UsingSha256 extends EcdsaUsingShaAlgorithm {
        public EcdsaP256UsingSha256() {
            super(AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256, "SHA256withECDSA", EllipticCurves.P_256, 64);
        }
    }

    /* loaded from: classes13.dex */
    public static class EcdsaP384UsingSha384 extends EcdsaUsingShaAlgorithm {
        public EcdsaP384UsingSha384() {
            super(AlgorithmIdentifiers.ECDSA_USING_P384_CURVE_AND_SHA384, "SHA384withECDSA", EllipticCurves.P_384, 96);
        }
    }

    /* loaded from: classes13.dex */
    public static class EcdsaP521UsingSha512 extends EcdsaUsingShaAlgorithm {
        public EcdsaP521UsingSha512() {
            super(AlgorithmIdentifiers.ECDSA_USING_P521_CURVE_AND_SHA512, "SHA512withECDSA", EllipticCurves.P_521, 132);
        }
    }

    /* loaded from: classes13.dex */
    public static class EcdsaSECP256K1UsingSha256 extends EcdsaUsingShaAlgorithm {
        public Logger i;

        public EcdsaSECP256K1UsingSha256() {
            super(AlgorithmIdentifiers.ECDSA_USING_SECP256K1_CURVE_AND_SHA256, "SHA256withECDSA", EllipticCurves.SECP_256K1, 64);
            this.i = LoggerFactory.getLogger(getClass());
        }

        @Override // org.jose4j.jws.BaseSignatureAlgorithm, org.jose4j.jwa.Algorithm
        public boolean isAvailable() {
            if (super.isAvailable()) {
                try {
                    return sign(prepareForSign(PublicJsonWebKey.Factory.newPublicJwk("{\"kty\":\"EC\",\"x\":\"gi0g9DzM2SvjVV7iD_upIU0urmZRjpoIc4Efu8563y8\",\"y\":\"Y5K6GofrdlWNLlfT8-AEyJyVZ3yJJcGgkGroHQCAhmk\",\"crv\":\"secp256k1\",\"d\":\"Vd99BKh6pxt3mXSDJzHuVrCq52xBXAKVahbuFb6dqBc\"}").getPrivateKey(), new ProviderContext()), new byte[]{2, 6}) != null;
                } catch (JoseException e) {
                    Logger logger = this.i;
                    logger.debug(getAlgorithmIdentifier() + " is not available due to " + ExceptionHelp.toStringWithCauses(e));
                }
            }
            return false;
        }
    }

    public EcdsaUsingShaAlgorithm(String str, String str2, String str3, int i) {
        super(str, str2, "EC");
        this.g = str3;
        this.h = i;
    }

    public static byte[] convertConcatenatedToDer(byte[] bArr) throws IOException {
        int i;
        byte[] bArr2;
        int length = bArr.length / 2;
        int i2 = length;
        while (true) {
            i = 1;
            if (i2 <= 1 || bArr[length - i2] != 0) {
                break;
            }
            i2--;
        }
        int i3 = length - i2;
        int i4 = bArr[i3] < 0 ? i2 + 1 : i2;
        int i5 = length;
        while (i5 > 1 && bArr[(length * 2) - i5] == 0) {
            i5--;
        }
        int i6 = (length * 2) - i5;
        int i7 = bArr[i6] < 0 ? i5 + 1 : i5;
        int i8 = i4 + 2 + 2 + i7;
        if (i8 <= 255) {
            if (i8 < 128) {
                bArr2 = new byte[i4 + 4 + 2 + i7];
            } else {
                bArr2 = new byte[i4 + 5 + 2 + i7];
                bArr2[1] = -127;
                i = 2;
            }
            bArr2[0] = 48;
            int i9 = i + 1;
            bArr2[i] = (byte) i8;
            int i10 = i9 + 1;
            bArr2[i9] = 2;
            bArr2[i10] = (byte) i4;
            int i11 = i10 + 1 + i4;
            System.arraycopy(bArr, i3, bArr2, i11 - i2, i2);
            int i12 = i11 + 1;
            bArr2[i11] = 2;
            bArr2[i12] = (byte) i7;
            System.arraycopy(bArr, i6, bArr2, ((i12 + 1) + i7) - i5, i5);
            return bArr2;
        }
        throw new IOException("Invalid format of ECDSA signature");
    }

    public static byte[] convertDerToConcatenated(byte[] bArr, int i) throws IOException {
        int i2;
        if (bArr.length >= 8 && bArr[0] == 48) {
            if (bArr[1] > 0) {
                i2 = 2;
            } else if (bArr[1] != -127) {
                throw new IOException("Invalid format of ECDSA signature");
            } else {
                i2 = 3;
            }
            int i3 = bArr[i2 + 1];
            int i4 = i3;
            while (i4 > 0 && bArr[((i2 + 2) + i3) - i4] == 0) {
                i4--;
            }
            int i5 = i2 + 2 + i3;
            int i6 = bArr[i5 + 1];
            int i7 = i6;
            while (i7 > 0 && bArr[((i5 + 2) + i6) - i7] == 0) {
                i7--;
            }
            int max = Math.max(Math.max(i4, i7), i / 2);
            int i8 = i2 - 1;
            if ((bArr[i8] & 255) == bArr.length - i2 && (bArr[i8] & 255) == i3 + 2 + 2 + i6 && bArr[i2] == 2 && bArr[i5] == 2) {
                int i9 = max * 2;
                byte[] bArr2 = new byte[i9];
                System.arraycopy(bArr, i5 - i4, bArr2, max - i4, i4);
                System.arraycopy(bArr, ((i5 + 2) + i6) - i7, bArr2, i9 - i7, i7);
                return bArr2;
            }
            throw new IOException("Invalid format of ECDSA signature");
        }
        throw new IOException("Invalid format of ECDSA signature");
    }

    public final void f(Key key) throws InvalidKeyException {
        if (key instanceof ECKey) {
            String name = EllipticCurves.getName(((ECKey) key).getParams().getCurve());
            if (getCurveName().equals(name)) {
                return;
            }
            throw new InvalidKeyException(getAlgorithmIdentifier() + MqttTopic.TOPIC_LEVEL_SEPARATOR + getJavaAlgorithm() + " expects a key using " + getCurveName() + " but was " + name);
        }
    }

    public String getCurveName() {
        return this.g;
    }

    @Override // org.jose4j.jws.BaseSignatureAlgorithm, org.jose4j.jws.JsonWebSignatureAlgorithm
    public byte[] sign(CryptoPrimitive cryptoPrimitive, byte[] bArr) throws JoseException {
        try {
            return convertDerToConcatenated(super.sign(cryptoPrimitive, bArr), this.h);
        } catch (IOException e) {
            throw new JoseException("Unable to convert DER encoding to R and S as a concatenated byte array.", e);
        }
    }

    @Override // org.jose4j.jws.BaseSignatureAlgorithm
    public void validatePrivateKey(PrivateKey privateKey) throws InvalidKeyException {
        f(privateKey);
    }

    @Override // org.jose4j.jws.BaseSignatureAlgorithm
    public void validatePublicKey(PublicKey publicKey) throws InvalidKeyException {
        f(publicKey);
    }

    @Override // org.jose4j.jws.BaseSignatureAlgorithm, org.jose4j.jws.JsonWebSignatureAlgorithm
    public boolean verifySignature(byte[] bArr, Key key, byte[] bArr2, ProviderContext providerContext) throws JoseException {
        if (bArr.length > this.h) {
            return false;
        }
        BigInteger fromBytes = BigEndianBigInteger.fromBytes(ByteUtil.leftHalf(bArr));
        BigInteger fromBytes2 = BigEndianBigInteger.fromBytes(ByteUtil.rightHalf(bArr));
        BigInteger order = EllipticCurves.getSpec(this.g).getOrder();
        BigInteger mod = fromBytes.mod(order);
        BigInteger bigInteger = BigInteger.ZERO;
        if (mod.equals(bigInteger) || fromBytes2.mod(order).equals(bigInteger)) {
            return false;
        }
        try {
            return super.verifySignature(convertConcatenatedToDer(bArr), key, bArr2, providerContext);
        } catch (IOException e) {
            throw new JoseException("Unable to convert R and S as a concatenated byte array to DER encoding.", e);
        }
    }
}
