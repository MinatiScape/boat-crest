package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.field.PolynomialExtensionField;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Integers;
import org.jose4j.keys.EllipticCurves;
/* loaded from: classes13.dex */
public class TlsECCUtils {
    public static final Integer EXT_elliptic_curves = Integers.valueOf(10);
    public static final Integer EXT_ec_point_formats = Integers.valueOf(11);

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f14858a = {"sect163k1", "sect163r1", "sect163r2", "sect193r1", "sect193r2", "sect233k1", "sect233r1", "sect239k1", "sect283k1", "sect283r1", "sect409k1", "sect409r1", "sect571k1", "sect571r1", "secp160k1", "secp160r1", "secp160r2", "secp192k1", "secp192r1", "secp224k1", "secp224r1", EllipticCurves.SECP_256K1, "secp256r1", "secp384r1", "secp521r1", "brainpoolP256r1", "brainpoolP384r1", "brainpoolP512r1"};

    public static void a(int[] iArr, int i) throws IOException {
        if (iArr != null && !Arrays.contains(iArr, i)) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    public static void addSupportedEllipticCurvesExtension(Hashtable hashtable, int[] iArr) throws IOException {
        hashtable.put(EXT_elliptic_curves, createSupportedEllipticCurvesExtension(iArr));
    }

    public static void addSupportedPointFormatsExtension(Hashtable hashtable, short[] sArr) throws IOException {
        hashtable.put(EXT_ec_point_formats, createSupportedPointFormatsExtension(sArr));
    }

    public static boolean areOnSameCurve(ECDomainParameters eCDomainParameters, ECDomainParameters eCDomainParameters2) {
        return eCDomainParameters != null && eCDomainParameters.equals(eCDomainParameters2);
    }

    public static ECPrivateKeyParameters b(SecureRandom secureRandom, int[] iArr, short[] sArr, OutputStream outputStream) throws IOException {
        int i;
        if (iArr != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= iArr.length) {
                    i = -1;
                    break;
                }
                i = iArr[i2];
                if (NamedCurve.isValid(i) && isSupportedNamedCurve(i)) {
                    break;
                }
                i2++;
            }
        } else {
            i = 23;
        }
        ECDomainParameters eCDomainParameters = null;
        if (i >= 0) {
            eCDomainParameters = getParametersForNamedCurve(i);
        } else if (Arrays.contains(iArr, 65281)) {
            eCDomainParameters = getParametersForNamedCurve(23);
        } else if (Arrays.contains(iArr, 65282)) {
            eCDomainParameters = getParametersForNamedCurve(10);
        }
        if (eCDomainParameters != null) {
            if (i < 0) {
                writeExplicitECParameters(sArr, eCDomainParameters, outputStream);
            } else {
                writeNamedECParameters(i, outputStream);
            }
            return generateEphemeralClientKeyExchange(secureRandom, sArr, eCDomainParameters, outputStream);
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static byte[] calculateECDHBasicAgreement(ECPublicKeyParameters eCPublicKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters) {
        ECDHBasicAgreement eCDHBasicAgreement = new ECDHBasicAgreement();
        eCDHBasicAgreement.init(eCPrivateKeyParameters);
        return BigIntegers.asUnsignedByteArray(eCDHBasicAgreement.getFieldSize(), eCDHBasicAgreement.calculateAgreement(eCPublicKeyParameters));
    }

    public static boolean containsECCCipherSuites(int[] iArr) {
        for (int i : iArr) {
            if (isECCCipherSuite(i)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] createSupportedEllipticCurvesExtension(int[] iArr) throws IOException {
        if (iArr == null || iArr.length < 1) {
            throw new TlsFatalAlert((short) 80);
        }
        return TlsUtils.encodeUint16ArrayWithUint16Length(iArr);
    }

    public static byte[] createSupportedPointFormatsExtension(short[] sArr) throws IOException {
        if (sArr == null || !Arrays.contains(sArr, (short) 0)) {
            sArr = Arrays.append(sArr, (short) 0);
        }
        return TlsUtils.encodeUint8ArrayWithUint8Length(sArr);
    }

    public static BigInteger deserializeECFieldElement(int i, byte[] bArr) throws IOException {
        if (bArr.length == (i + 7) / 8) {
            return new BigInteger(1, bArr);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static ECPoint deserializeECPoint(short[] sArr, ECCurve eCCurve, byte[] bArr) throws IOException {
        if (bArr != null) {
            short s = 1;
            if (bArr.length >= 1) {
                byte b = bArr[0];
                if (b == 2 || b == 3) {
                    if (ECAlgorithms.isF2mCurve(eCCurve)) {
                        s = 2;
                    } else if (!ECAlgorithms.isFpCurve(eCCurve)) {
                        throw new TlsFatalAlert((short) 47);
                    }
                } else if (b != 4) {
                    throw new TlsFatalAlert((short) 47);
                } else {
                    s = 0;
                }
                if (s == 0 || (sArr != null && Arrays.contains(sArr, s))) {
                    return eCCurve.decodePoint(bArr);
                }
                throw new TlsFatalAlert((short) 47);
            }
        }
        throw new TlsFatalAlert((short) 47);
    }

    public static ECPublicKeyParameters deserializeECPublicKey(short[] sArr, ECDomainParameters eCDomainParameters, byte[] bArr) throws IOException {
        try {
            return new ECPublicKeyParameters(deserializeECPoint(sArr, eCDomainParameters.getCurve(), bArr), eCDomainParameters);
        } catch (RuntimeException e) {
            throw new TlsFatalAlert((short) 47, e);
        }
    }

    public static AsymmetricCipherKeyPair generateECKeyPair(SecureRandom secureRandom, ECDomainParameters eCDomainParameters) {
        ECKeyPairGenerator eCKeyPairGenerator = new ECKeyPairGenerator();
        eCKeyPairGenerator.init(new ECKeyGenerationParameters(eCDomainParameters, secureRandom));
        return eCKeyPairGenerator.generateKeyPair();
    }

    public static ECPrivateKeyParameters generateEphemeralClientKeyExchange(SecureRandom secureRandom, short[] sArr, ECDomainParameters eCDomainParameters, OutputStream outputStream) throws IOException {
        AsymmetricCipherKeyPair generateECKeyPair = generateECKeyPair(secureRandom, eCDomainParameters);
        writeECPoint(sArr, ((ECPublicKeyParameters) generateECKeyPair.getPublic()).getQ(), outputStream);
        return (ECPrivateKeyParameters) generateECKeyPair.getPrivate();
    }

    public static String getNameOfNamedCurve(int i) {
        if (isSupportedNamedCurve(i)) {
            return f14858a[i - 1];
        }
        return null;
    }

    public static ECDomainParameters getParametersForNamedCurve(int i) {
        String nameOfNamedCurve = getNameOfNamedCurve(i);
        if (nameOfNamedCurve == null) {
            return null;
        }
        X9ECParameters byName = CustomNamedCurves.getByName(nameOfNamedCurve);
        if (byName == null && (byName = ECNamedCurveTable.getByName(nameOfNamedCurve)) == null) {
            return null;
        }
        return new ECDomainParameters(byName.getCurve(), byName.getG(), byName.getN(), byName.getH(), byName.getSeed());
    }

    public static int[] getSupportedEllipticCurvesExtension(Hashtable hashtable) throws IOException {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_elliptic_curves);
        if (extensionData == null) {
            return null;
        }
        return readSupportedEllipticCurvesExtension(extensionData);
    }

    public static short[] getSupportedPointFormatsExtension(Hashtable hashtable) throws IOException {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_ec_point_formats);
        if (extensionData == null) {
            return null;
        }
        return readSupportedPointFormatsExtension(extensionData);
    }

    public static boolean hasAnySupportedNamedCurves() {
        return f14858a.length > 0;
    }

    public static boolean isCompressionPreferred(short[] sArr, short s) {
        short s2;
        if (sArr == null) {
            return false;
        }
        for (int i = 0; i < sArr.length && (s2 = sArr[i]) != 0; i++) {
            if (s2 == s) {
                return true;
            }
        }
        return false;
    }

    public static boolean isECCCipherSuite(int i) {
        if (i != 52396) {
            switch (i) {
                case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA /* 49153 */:
                case CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA /* 49154 */:
                case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49155 */:
                case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA /* 49156 */:
                case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA /* 49157 */:
                case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA /* 49158 */:
                case CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA /* 49159 */:
                case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49160 */:
                case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA /* 49161 */:
                case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA /* 49162 */:
                case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA /* 49163 */:
                case CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA /* 49164 */:
                case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA /* 49165 */:
                case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA /* 49166 */:
                case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA /* 49167 */:
                case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA /* 49168 */:
                case CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA /* 49169 */:
                case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA /* 49170 */:
                case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA /* 49171 */:
                case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA /* 49172 */:
                case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA /* 49173 */:
                case CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA /* 49174 */:
                case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA /* 49175 */:
                case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA /* 49176 */:
                case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA /* 49177 */:
                    return true;
                default:
                    switch (i) {
                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
                        case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
                        case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
                        case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
                        case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
                        case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
                        case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
                        case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
                        case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
                        case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
                        case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
                        case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
                        case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
                        case CipherSuite.TLS_ECDHE_PSK_WITH_RC4_128_SHA /* 49203 */:
                        case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA /* 49204 */:
                        case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA /* 49205 */:
                        case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA /* 49206 */:
                        case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 /* 49207 */:
                        case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /* 49208 */:
                        case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA /* 49209 */:
                        case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256 /* 49210 */:
                        case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /* 49211 */:
                            return true;
                        default:
                            switch (i) {
                                case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
                                case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
                                case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
                                case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
                                case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
                                case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
                                case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
                                case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
                                    return true;
                                default:
                                    switch (i) {
                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
                                        case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
                                        case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
                                        case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
                                        case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
                                        case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
                                        case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
                                            return true;
                                        default:
                                            switch (i) {
                                                case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49306 */:
                                                case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49307 */:
                                                    return true;
                                                default:
                                                    switch (i) {
                                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
                                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
                                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
                                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
                                                            return true;
                                                        default:
                                                            switch (i) {
                                                                case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
                                                                case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
                                                                    return true;
                                                                default:
                                                                    switch (i) {
                                                                        case 65282:
                                                                        case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB /* 65283 */:
                                                                        case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB /* 65284 */:
                                                                        case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB /* 65285 */:
                                                                            return true;
                                                                        default:
                                                                            switch (i) {
                                                                                case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB /* 65300 */:
                                                                                case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB /* 65301 */:
                                                                                    return true;
                                                                                default:
                                                                                    return false;
                                                                            }
                                                                    }
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
        return true;
    }

    public static boolean isSupportedNamedCurve(int i) {
        return i > 0 && i <= f14858a.length;
    }

    public static int readECExponent(int i, InputStream inputStream) throws IOException {
        int intValue;
        BigInteger readECParameter = readECParameter(inputStream);
        if (readECParameter.bitLength() >= 32 || (intValue = readECParameter.intValue()) <= 0 || intValue >= i) {
            throw new TlsFatalAlert((short) 47);
        }
        return intValue;
    }

    public static BigInteger readECFieldElement(int i, InputStream inputStream) throws IOException {
        return deserializeECFieldElement(i, TlsUtils.readOpaque8(inputStream));
    }

    public static BigInteger readECParameter(InputStream inputStream) throws IOException {
        return new BigInteger(1, TlsUtils.readOpaque8(inputStream));
    }

    public static ECDomainParameters readECParameters(int[] iArr, short[] sArr, InputStream inputStream) throws IOException {
        int i;
        int i2;
        BigInteger bigInteger;
        ECCurve.F2m f2m;
        try {
            short readUint8 = TlsUtils.readUint8(inputStream);
            if (readUint8 == 1) {
                a(iArr, 65281);
                BigInteger readECParameter = readECParameter(inputStream);
                BigInteger readECFieldElement = readECFieldElement(readECParameter.bitLength(), inputStream);
                BigInteger readECFieldElement2 = readECFieldElement(readECParameter.bitLength(), inputStream);
                byte[] readOpaque8 = TlsUtils.readOpaque8(inputStream);
                BigInteger readECParameter2 = readECParameter(inputStream);
                BigInteger readECParameter3 = readECParameter(inputStream);
                ECCurve.Fp fp = new ECCurve.Fp(readECParameter, readECFieldElement, readECFieldElement2, readECParameter2, readECParameter3);
                return new ECDomainParameters(fp, deserializeECPoint(sArr, fp, readOpaque8), readECParameter2, readECParameter3);
            } else if (readUint8 != 2) {
                if (readUint8 == 3) {
                    int readUint16 = TlsUtils.readUint16(inputStream);
                    if (NamedCurve.refersToASpecificNamedCurve(readUint16)) {
                        a(iArr, readUint16);
                        return getParametersForNamedCurve(readUint16);
                    }
                    throw new TlsFatalAlert((short) 47);
                }
                throw new TlsFatalAlert((short) 47);
            } else {
                a(iArr, 65282);
                int readUint162 = TlsUtils.readUint16(inputStream);
                short readUint82 = TlsUtils.readUint8(inputStream);
                if (ECBasisType.isValid(readUint82)) {
                    int readECExponent = readECExponent(readUint162, inputStream);
                    if (readUint82 == 2) {
                        i = readECExponent(readUint162, inputStream);
                        i2 = readECExponent(readUint162, inputStream);
                    } else {
                        i = -1;
                        i2 = -1;
                    }
                    BigInteger readECFieldElement3 = readECFieldElement(readUint162, inputStream);
                    BigInteger readECFieldElement4 = readECFieldElement(readUint162, inputStream);
                    byte[] readOpaque82 = TlsUtils.readOpaque8(inputStream);
                    BigInteger readECParameter4 = readECParameter(inputStream);
                    BigInteger readECParameter5 = readECParameter(inputStream);
                    if (readUint82 == 2) {
                        bigInteger = readECParameter4;
                        f2m = new ECCurve.F2m(readUint162, readECExponent, i, i2, readECFieldElement3, readECFieldElement4, readECParameter4, readECParameter5);
                    } else {
                        bigInteger = readECParameter4;
                        f2m = new ECCurve.F2m(readUint162, readECExponent, readECFieldElement3, readECFieldElement4, bigInteger, readECParameter5);
                    }
                    return new ECDomainParameters(f2m, deserializeECPoint(sArr, f2m, readOpaque82), bigInteger, readECParameter5);
                }
                throw new TlsFatalAlert((short) 47);
            }
        } catch (RuntimeException e) {
            throw new TlsFatalAlert((short) 47, e);
        }
    }

    public static int[] readSupportedEllipticCurvesExtension(byte[] bArr) throws IOException {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
            if (readUint16 < 2 || (readUint16 & 1) != 0) {
                throw new TlsFatalAlert((short) 50);
            }
            int[] readUint16Array = TlsUtils.readUint16Array(readUint16 / 2, byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            return readUint16Array;
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }

    public static short[] readSupportedPointFormatsExtension(byte[] bArr) throws IOException {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
            if (readUint8 >= 1) {
                short[] readUint8Array = TlsUtils.readUint8Array(readUint8, byteArrayInputStream);
                TlsProtocol.assertEmpty(byteArrayInputStream);
                if (Arrays.contains(readUint8Array, (short) 0)) {
                    return readUint8Array;
                }
                throw new TlsFatalAlert((short) 47);
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }

    public static byte[] serializeECFieldElement(int i, BigInteger bigInteger) throws IOException {
        return BigIntegers.asUnsignedByteArray((i + 7) / 8, bigInteger);
    }

    public static byte[] serializeECPoint(short[] sArr, ECPoint eCPoint) throws IOException {
        boolean z;
        short s;
        ECCurve curve = eCPoint.getCurve();
        if (ECAlgorithms.isFpCurve(curve)) {
            s = 1;
        } else if (!ECAlgorithms.isF2mCurve(curve)) {
            z = false;
            return eCPoint.getEncoded(z);
        } else {
            s = 2;
        }
        z = isCompressionPreferred(sArr, s);
        return eCPoint.getEncoded(z);
    }

    public static byte[] serializeECPublicKey(short[] sArr, ECPublicKeyParameters eCPublicKeyParameters) throws IOException {
        return serializeECPoint(sArr, eCPublicKeyParameters.getQ());
    }

    public static ECPublicKeyParameters validateECPublicKey(ECPublicKeyParameters eCPublicKeyParameters) throws IOException {
        return eCPublicKeyParameters;
    }

    public static void writeECExponent(int i, OutputStream outputStream) throws IOException {
        writeECParameter(BigInteger.valueOf(i), outputStream);
    }

    public static void writeECFieldElement(int i, BigInteger bigInteger, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(serializeECFieldElement(i, bigInteger), outputStream);
    }

    public static void writeECFieldElement(ECFieldElement eCFieldElement, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(eCFieldElement.getEncoded(), outputStream);
    }

    public static void writeECParameter(BigInteger bigInteger, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(BigIntegers.asUnsignedByteArray(bigInteger), outputStream);
    }

    public static void writeECPoint(short[] sArr, ECPoint eCPoint, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(serializeECPoint(sArr, eCPoint), outputStream);
    }

    public static void writeExplicitECParameters(short[] sArr, ECDomainParameters eCDomainParameters, OutputStream outputStream) throws IOException {
        int i;
        ECCurve curve = eCDomainParameters.getCurve();
        if (ECAlgorithms.isFpCurve(curve)) {
            TlsUtils.writeUint8((short) 1, outputStream);
            writeECParameter(curve.getField().getCharacteristic(), outputStream);
        } else if (!ECAlgorithms.isF2mCurve(curve)) {
            throw new IllegalArgumentException("'ecParameters' not a known curve type");
        } else {
            int[] exponentsPresent = ((PolynomialExtensionField) curve.getField()).getMinimalPolynomial().getExponentsPresent();
            TlsUtils.writeUint8((short) 2, outputStream);
            int i2 = exponentsPresent[exponentsPresent.length - 1];
            TlsUtils.checkUint16(i2);
            TlsUtils.writeUint16(i2, outputStream);
            if (exponentsPresent.length == 3) {
                TlsUtils.writeUint8((short) 1, outputStream);
                i = exponentsPresent[1];
            } else if (exponentsPresent.length != 5) {
                throw new IllegalArgumentException("Only trinomial and pentomial curves are supported");
            } else {
                TlsUtils.writeUint8((short) 2, outputStream);
                writeECExponent(exponentsPresent[1], outputStream);
                writeECExponent(exponentsPresent[2], outputStream);
                i = exponentsPresent[3];
            }
            writeECExponent(i, outputStream);
        }
        writeECFieldElement(curve.getA(), outputStream);
        writeECFieldElement(curve.getB(), outputStream);
        TlsUtils.writeOpaque8(serializeECPoint(sArr, eCDomainParameters.getG()), outputStream);
        writeECParameter(eCDomainParameters.getN(), outputStream);
        writeECParameter(eCDomainParameters.getH(), outputStream);
    }

    public static void writeNamedECParameters(int i, OutputStream outputStream) throws IOException {
        if (!NamedCurve.refersToASpecificNamedCurve(i)) {
            throw new TlsFatalAlert((short) 80);
        }
        TlsUtils.writeUint8((short) 3, outputStream);
        TlsUtils.checkUint16(i);
        TlsUtils.writeUint16(i, outputStream);
    }
}
