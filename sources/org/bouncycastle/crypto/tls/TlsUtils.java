package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import okhttp3.internal.ws.WebSocketProtocol;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Shorts;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes13.dex */
public class TlsUtils {
    public static final byte[] EMPTY_BYTES = new byte[0];
    public static final short[] EMPTY_SHORTS = new short[0];
    public static final int[] EMPTY_INTS = new int[0];
    public static final long[] EMPTY_LONGS = new long[0];
    public static final Integer EXT_signature_algorithms = Integers.valueOf(13);

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f14860a = {67, com.htsmart.wristband2.a.a.a.a1, com.htsmart.wristband2.a.a.a.c1, 84};
    public static final byte[] b = {83, 82, 86, 82};
    public static final byte[][] c = h();

    public static byte[] PRF(TlsContext tlsContext, byte[] bArr, String str, byte[] bArr2, int i) {
        if (tlsContext.getServerVersion().isSSL()) {
            throw new IllegalStateException("No PRF available for SSLv3 session");
        }
        byte[] byteArray = Strings.toByteArray(str);
        byte[] g = g(byteArray, bArr2);
        int prfAlgorithm = tlsContext.getSecurityParameters().getPrfAlgorithm();
        if (prfAlgorithm == 0) {
            return a(bArr, byteArray, g, i);
        }
        byte[] bArr3 = new byte[i];
        j(createPRFHash(prfAlgorithm), bArr, g, bArr3);
        return bArr3;
    }

    public static byte[] PRF_legacy(byte[] bArr, String str, byte[] bArr2, int i) {
        byte[] byteArray = Strings.toByteArray(str);
        return a(bArr, byteArray, g(byteArray, bArr2), i);
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int length = (bArr.length + 1) / 2;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr, 0, bArr4, 0, length);
        System.arraycopy(bArr, bArr.length - length, bArr5, 0, length);
        byte[] bArr6 = new byte[i];
        byte[] bArr7 = new byte[i];
        j(createHash((short) 1), bArr4, bArr3, bArr6);
        j(createHash((short) 2), bArr5, bArr3, bArr7);
        for (int i2 = 0; i2 < i; i2++) {
            bArr6[i2] = (byte) (bArr6[i2] ^ bArr7[i2]);
        }
        return bArr6;
    }

    public static void addSignatureAlgorithmsExtension(Hashtable hashtable, Vector vector) throws IOException {
        hashtable.put(EXT_signature_algorithms, createSignatureAlgorithmsExtension(vector));
    }

    public static byte[] b(TlsContext tlsContext, int i) {
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] masterSecret = securityParameters.getMasterSecret();
        byte[] g = g(securityParameters.getServerRandom(), securityParameters.getClientRandom());
        return isSSL(tlsContext) ? c(masterSecret, g, i) : PRF(tlsContext, masterSecret, "key expansion", g, i);
    }

    public static byte[] c(byte[] bArr, byte[] bArr2, int i) {
        Digest createHash = createHash((short) 1);
        Digest createHash2 = createHash((short) 2);
        int digestSize = createHash.getDigestSize();
        int digestSize2 = createHash2.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        byte[] bArr4 = new byte[i + digestSize];
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            byte[] bArr5 = c[i3];
            createHash2.update(bArr5, 0, bArr5.length);
            createHash2.update(bArr, 0, bArr.length);
            createHash2.update(bArr2, 0, bArr2.length);
            createHash2.doFinal(bArr3, 0);
            createHash.update(bArr, 0, bArr.length);
            createHash.update(bArr3, 0, digestSize2);
            createHash.doFinal(bArr4, i2);
            i2 += digestSize;
            i3++;
        }
        return Arrays.copyOfRange(bArr4, 0, i);
    }

    public static void checkUint16(int i) throws IOException {
        if (!isValidUint16(i)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint16(long j) throws IOException {
        if (!isValidUint16(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint24(int i) throws IOException {
        if (!isValidUint24(i)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint24(long j) throws IOException {
        if (!isValidUint24(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint32(long j) throws IOException {
        if (!isValidUint32(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint48(long j) throws IOException {
        if (!isValidUint48(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint64(long j) throws IOException {
        if (!isValidUint64(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint8(int i) throws IOException {
        if (!isValidUint8(i)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint8(long j) throws IOException {
        if (!isValidUint8(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint8(short s) throws IOException {
        if (!isValidUint8(s)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static Digest cloneHash(short s, Digest digest) {
        switch (s) {
            case 1:
                return new MD5Digest((MD5Digest) digest);
            case 2:
                return new SHA1Digest((SHA1Digest) digest);
            case 3:
                return new SHA224Digest((SHA224Digest) digest);
            case 4:
                return new SHA256Digest((SHA256Digest) digest);
            case 5:
                return new SHA384Digest((SHA384Digest) digest);
            case 6:
                return new SHA512Digest((SHA512Digest) digest);
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Digest clonePRFHash(int i, Digest digest) {
        return i != 0 ? cloneHash(getHashAlgorithmForPRFAlgorithm(i), digest) : new b((b) digest);
    }

    public static Digest createHash(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        return signatureAndHashAlgorithm == null ? new b() : createHash(signatureAndHashAlgorithm.getHash());
    }

    public static Digest createHash(short s) {
        switch (s) {
            case 1:
                return new MD5Digest();
            case 2:
                return new SHA1Digest();
            case 3:
                return new SHA224Digest();
            case 4:
                return new SHA256Digest();
            case 5:
                return new SHA384Digest();
            case 6:
                return new SHA512Digest();
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Digest createPRFHash(int i) {
        return i != 0 ? createHash(getHashAlgorithmForPRFAlgorithm(i)) : new b();
    }

    public static byte[] createSignatureAlgorithmsExtension(Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encodeSupportedSignatureAlgorithms(vector, false, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static TlsSigner createTlsSigner(short s) {
        if (s != 1) {
            if (s != 2) {
                if (s == 64) {
                    return new TlsECDSASigner();
                }
                throw new IllegalArgumentException("'clientCertificateType' is not a type with signing capability");
            }
            return new TlsDSSSigner();
        }
        return new TlsRSASigner();
    }

    public static byte[] d(TlsContext tlsContext, byte[] bArr) {
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] sessionHash = securityParameters.o ? securityParameters.getSessionHash() : g(securityParameters.getClientRandom(), securityParameters.getServerRandom());
        if (isSSL(tlsContext)) {
            return e(bArr, sessionHash);
        }
        return PRF(tlsContext, bArr, securityParameters.o ? ExporterLabel.extended_master_secret : "master secret", sessionHash, 48);
    }

    public static byte[] e(byte[] bArr, byte[] bArr2) {
        Digest createHash = createHash((short) 1);
        Digest createHash2 = createHash((short) 2);
        int digestSize = createHash.getDigestSize();
        int digestSize2 = createHash2.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        byte[] bArr4 = new byte[digestSize * 3];
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            byte[] bArr5 = c[i2];
            createHash2.update(bArr5, 0, bArr5.length);
            createHash2.update(bArr, 0, bArr.length);
            createHash2.update(bArr2, 0, bArr2.length);
            createHash2.doFinal(bArr3, 0);
            createHash.update(bArr, 0, bArr.length);
            createHash.update(bArr3, 0, digestSize2);
            createHash.doFinal(bArr4, i);
            i += digestSize;
        }
        return bArr4;
    }

    public static byte[] encodeOpaque8(byte[] bArr) throws IOException {
        checkUint8(bArr.length);
        return Arrays.prepend(bArr, (byte) bArr.length);
    }

    public static void encodeSupportedSignatureAlgorithms(Vector vector, boolean z, OutputStream outputStream) throws IOException {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        int size = vector.size() * 2;
        checkUint16(size);
        writeUint16(size, outputStream);
        for (int i = 0; i < vector.size(); i++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i);
            if (!z && signatureAndHashAlgorithm.getSignature() == 0) {
                throw new IllegalArgumentException("SignatureAlgorithm.anonymous MUST NOT appear in the signature_algorithms extension");
            }
            signatureAndHashAlgorithm.encode(outputStream);
        }
    }

    public static byte[] encodeUint16ArrayWithUint16Length(int[] iArr) throws IOException {
        byte[] bArr = new byte[(iArr.length * 2) + 2];
        writeUint16ArrayWithUint16Length(iArr, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint8ArrayWithUint8Length(short[] sArr) throws IOException {
        byte[] bArr = new byte[sArr.length + 1];
        writeUint8ArrayWithUint8Length(sArr, bArr, 0);
        return bArr;
    }

    public static byte[] f(TlsContext tlsContext, String str, byte[] bArr) {
        if (isSSL(tlsContext)) {
            return bArr;
        }
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        return PRF(tlsContext, securityParameters.getMasterSecret(), str, bArr, securityParameters.getVerifyDataLength());
    }

    public static byte[] g(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static Vector getAllSignatureAlgorithms() {
        Vector vector = new Vector(4);
        vector.addElement(Shorts.valueOf((short) 0));
        vector.addElement(Shorts.valueOf((short) 1));
        vector.addElement(Shorts.valueOf((short) 2));
        vector.addElement(Shorts.valueOf((short) 3));
        return vector;
    }

    public static int getCipherType(int i) throws IOException {
        int encryptionAlgorithm = getEncryptionAlgorithm(i);
        if (encryptionAlgorithm == 103 || encryptionAlgorithm == 104) {
            return 2;
        }
        switch (encryptionAlgorithm) {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 12:
            case 13:
            case 14:
                return 1;
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return 2;
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    public static Vector getDefaultDSSSignatureAlgorithms() {
        return m(new SignatureAndHashAlgorithm((short) 2, (short) 2));
    }

    public static Vector getDefaultECDSASignatureAlgorithms() {
        return m(new SignatureAndHashAlgorithm((short) 2, (short) 3));
    }

    public static Vector getDefaultRSASignatureAlgorithms() {
        return m(new SignatureAndHashAlgorithm((short) 2, (short) 1));
    }

    public static Vector getDefaultSupportedSignatureAlgorithms() {
        short[] sArr = {2, 3, 4, 5, 6};
        short[] sArr2 = {1, 2, 3};
        Vector vector = new Vector();
        for (int i = 0; i < 3; i++) {
            for (int i2 = 0; i2 < 5; i2++) {
                vector.addElement(new SignatureAndHashAlgorithm(sArr[i2], sArr2[i]));
            }
        }
        return vector;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x004b A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getEncryptionAlgorithm(int r3) throws java.io.IOException {
        /*
            r0 = 1
            r1 = 0
            if (r3 == r0) goto L61
            r0 = 2
            if (r3 == r0) goto L61
            r2 = 4
            if (r3 == r2) goto L60
            r2 = 5
            if (r3 == r2) goto L60
            switch(r3) {
                case 10: goto L5e;
                case 13: goto L5e;
                case 16: goto L5e;
                case 19: goto L5e;
                case 22: goto L5e;
                case 24: goto L60;
                case 27: goto L5e;
                case 132: goto L5b;
                case 133: goto L5b;
                case 134: goto L5b;
                case 135: goto L5b;
                case 136: goto L5b;
                case 137: goto L5b;
                case 138: goto L60;
                case 139: goto L5e;
                case 140: goto L58;
                case 141: goto L55;
                case 142: goto L60;
                case 143: goto L5e;
                case 144: goto L58;
                case 145: goto L55;
                case 146: goto L60;
                case 147: goto L5e;
                case 148: goto L58;
                case 149: goto L55;
                case 150: goto L52;
                case 151: goto L52;
                case 152: goto L52;
                case 153: goto L52;
                case 154: goto L52;
                case 155: goto L52;
                case 156: goto L4f;
                case 157: goto L4c;
                case 158: goto L4f;
                case 159: goto L4c;
                case 160: goto L4f;
                case 161: goto L4c;
                case 162: goto L4f;
                case 163: goto L4c;
                case 164: goto L4f;
                case 165: goto L4c;
                case 166: goto L4f;
                case 167: goto L4c;
                case 168: goto L4f;
                case 169: goto L4c;
                case 170: goto L4f;
                case 171: goto L4c;
                case 172: goto L4f;
                case 173: goto L4c;
                case 174: goto L58;
                case 175: goto L55;
                case 176: goto L4b;
                case 177: goto L4b;
                case 178: goto L58;
                case 179: goto L55;
                case 180: goto L4b;
                case 181: goto L4b;
                case 182: goto L58;
                case 183: goto L55;
                case 184: goto L4b;
                case 185: goto L4b;
                case 186: goto L48;
                case 187: goto L48;
                case 188: goto L48;
                case 189: goto L48;
                case 190: goto L48;
                case 191: goto L48;
                case 192: goto L5b;
                case 193: goto L5b;
                case 194: goto L5b;
                case 195: goto L5b;
                case 196: goto L5b;
                case 197: goto L5b;
                default: goto L10;
            }
        L10:
            switch(r3) {
                case 44: goto L61;
                case 45: goto L61;
                case 46: goto L61;
                case 47: goto L58;
                case 48: goto L58;
                case 49: goto L58;
                case 50: goto L58;
                case 51: goto L58;
                case 52: goto L58;
                case 53: goto L55;
                case 54: goto L55;
                case 55: goto L55;
                case 56: goto L55;
                case 57: goto L55;
                case 58: goto L55;
                case 59: goto L4b;
                case 60: goto L58;
                case 61: goto L55;
                case 62: goto L58;
                case 63: goto L58;
                case 64: goto L58;
                case 65: goto L48;
                case 66: goto L48;
                case 67: goto L48;
                case 68: goto L48;
                case 69: goto L48;
                case 70: goto L48;
                default: goto L13;
            }
        L13:
            switch(r3) {
                case 103: goto L58;
                case 104: goto L55;
                case 105: goto L55;
                case 106: goto L55;
                case 107: goto L55;
                case 108: goto L58;
                case 109: goto L55;
                default: goto L16;
            }
        L16:
            switch(r3) {
                case 49153: goto L61;
                case 49154: goto L60;
                case 49155: goto L5e;
                case 49156: goto L58;
                case 49157: goto L55;
                case 49158: goto L61;
                case 49159: goto L60;
                case 49160: goto L5e;
                case 49161: goto L58;
                case 49162: goto L55;
                case 49163: goto L61;
                case 49164: goto L60;
                case 49165: goto L5e;
                case 49166: goto L58;
                case 49167: goto L55;
                case 49168: goto L61;
                case 49169: goto L60;
                case 49170: goto L5e;
                case 49171: goto L58;
                case 49172: goto L55;
                case 49173: goto L61;
                case 49174: goto L60;
                case 49175: goto L5e;
                case 49176: goto L58;
                case 49177: goto L55;
                case 49178: goto L5e;
                case 49179: goto L5e;
                case 49180: goto L5e;
                case 49181: goto L58;
                case 49182: goto L58;
                case 49183: goto L58;
                case 49184: goto L55;
                case 49185: goto L55;
                case 49186: goto L55;
                case 49187: goto L58;
                case 49188: goto L55;
                case 49189: goto L58;
                case 49190: goto L55;
                case 49191: goto L58;
                case 49192: goto L55;
                case 49193: goto L58;
                case 49194: goto L55;
                case 49195: goto L4f;
                case 49196: goto L4c;
                case 49197: goto L4f;
                case 49198: goto L4c;
                case 49199: goto L4f;
                case 49200: goto L4c;
                case 49201: goto L4f;
                case 49202: goto L4c;
                case 49203: goto L60;
                case 49204: goto L5e;
                case 49205: goto L58;
                case 49206: goto L55;
                case 49207: goto L58;
                case 49208: goto L55;
                case 49209: goto L61;
                case 49210: goto L4b;
                case 49211: goto L4b;
                default: goto L19;
            }
        L19:
            switch(r3) {
                case 49266: goto L48;
                case 49267: goto L5b;
                case 49268: goto L48;
                case 49269: goto L5b;
                case 49270: goto L48;
                case 49271: goto L5b;
                case 49272: goto L48;
                case 49273: goto L5b;
                case 49274: goto L45;
                case 49275: goto L42;
                case 49276: goto L45;
                case 49277: goto L42;
                case 49278: goto L45;
                case 49279: goto L42;
                case 49280: goto L45;
                case 49281: goto L42;
                case 49282: goto L45;
                case 49283: goto L42;
                case 49284: goto L45;
                case 49285: goto L42;
                case 49286: goto L45;
                case 49287: goto L42;
                case 49288: goto L45;
                case 49289: goto L42;
                case 49290: goto L45;
                case 49291: goto L42;
                case 49292: goto L45;
                case 49293: goto L42;
                case 49294: goto L45;
                case 49295: goto L42;
                case 49296: goto L45;
                case 49297: goto L42;
                case 49298: goto L45;
                case 49299: goto L42;
                case 49300: goto L48;
                case 49301: goto L5b;
                case 49302: goto L48;
                case 49303: goto L5b;
                case 49304: goto L48;
                case 49305: goto L5b;
                case 49306: goto L48;
                case 49307: goto L5b;
                case 49308: goto L3f;
                case 49309: goto L3c;
                case 49310: goto L3f;
                case 49311: goto L3c;
                case 49312: goto L39;
                case 49313: goto L36;
                case 49314: goto L39;
                case 49315: goto L36;
                case 49316: goto L3f;
                case 49317: goto L3c;
                case 49318: goto L3f;
                case 49319: goto L3c;
                case 49320: goto L39;
                case 49321: goto L36;
                case 49322: goto L39;
                case 49323: goto L36;
                case 49324: goto L3f;
                case 49325: goto L3c;
                case 49326: goto L39;
                case 49327: goto L36;
                default: goto L1c;
            }
        L1c:
            switch(r3) {
                case 52392: goto L33;
                case 52393: goto L33;
                case 52394: goto L33;
                case 52395: goto L33;
                case 52396: goto L33;
                case 52397: goto L33;
                case 52398: goto L33;
                default: goto L1f;
            }
        L1f:
            switch(r3) {
                case 65280: goto L30;
                case 65281: goto L2d;
                case 65282: goto L30;
                case 65283: goto L2d;
                case 65284: goto L30;
                case 65285: goto L2d;
                default: goto L22;
            }
        L22:
            switch(r3) {
                case 65296: goto L30;
                case 65297: goto L2d;
                case 65298: goto L30;
                case 65299: goto L2d;
                case 65300: goto L30;
                case 65301: goto L2d;
                default: goto L25;
            }
        L25:
            org.bouncycastle.crypto.tls.TlsFatalAlert r3 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r0 = 80
            r3.<init>(r0)
            throw r3
        L2d:
            r3 = 104(0x68, float:1.46E-43)
            return r3
        L30:
            r3 = 103(0x67, float:1.44E-43)
            return r3
        L33:
            r3 = 21
            return r3
        L36:
            r3 = 18
            return r3
        L39:
            r3 = 16
            return r3
        L3c:
            r3 = 17
            return r3
        L3f:
            r3 = 15
            return r3
        L42:
            r3 = 20
            return r3
        L45:
            r3 = 19
            return r3
        L48:
            r3 = 12
            return r3
        L4b:
            return r1
        L4c:
            r3 = 11
            return r3
        L4f:
            r3 = 10
            return r3
        L52:
            r3 = 14
            return r3
        L55:
            r3 = 9
            return r3
        L58:
            r3 = 8
            return r3
        L5b:
            r3 = 13
            return r3
        L5e:
            r3 = 7
            return r3
        L60:
            return r0
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.TlsUtils.getEncryptionAlgorithm(int):int");
    }

    public static byte[] getExtensionData(Hashtable hashtable, Integer num) {
        if (hashtable == null) {
            return null;
        }
        return (byte[]) hashtable.get(num);
    }

    public static short getHashAlgorithmForPRFAlgorithm(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    return (short) 5;
                }
                throw new IllegalArgumentException("unknown PRFAlgorithm");
            }
            return (short) 4;
        }
        throw new IllegalArgumentException("legacy PRF not a valid algorithm");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:817)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:730)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:155)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0053 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getKeyExchangeAlgorithm(int r2) throws java.io.IOException {
        /*
            r0 = 1
            if (r2 == r0) goto L5b
            r1 = 2
            if (r2 == r1) goto L5b
            r1 = 4
            if (r2 == r1) goto L5b
            r1 = 5
            if (r2 == r1) goto L5b
            switch(r2) {
                case 10: goto L5b;
                case 13: goto L59;
                case 16: goto L56;
                case 19: goto L54;
                case 22: goto L53;
                case 24: goto L50;
                case 27: goto L50;
                case 132: goto L5b;
                case 133: goto L59;
                case 134: goto L56;
                case 135: goto L54;
                case 136: goto L53;
                case 137: goto L50;
                case 138: goto L4d;
                case 139: goto L4d;
                case 140: goto L4d;
                case 141: goto L4d;
                case 142: goto L4a;
                case 143: goto L4a;
                case 144: goto L4a;
                case 145: goto L4a;
                case 146: goto L47;
                case 147: goto L47;
                case 148: goto L47;
                case 149: goto L47;
                case 150: goto L5b;
                case 151: goto L59;
                case 152: goto L56;
                case 153: goto L54;
                case 154: goto L53;
                case 155: goto L50;
                case 156: goto L5b;
                case 157: goto L5b;
                case 158: goto L53;
                case 159: goto L53;
                case 160: goto L56;
                case 161: goto L56;
                case 162: goto L54;
                case 163: goto L54;
                case 164: goto L59;
                case 165: goto L59;
                case 166: goto L50;
                case 167: goto L50;
                case 168: goto L4d;
                case 169: goto L4d;
                case 170: goto L4a;
                case 171: goto L4a;
                case 172: goto L47;
                case 173: goto L47;
                case 174: goto L4d;
                case 175: goto L4d;
                case 176: goto L4d;
                case 177: goto L4d;
                case 178: goto L4a;
                case 179: goto L4a;
                case 180: goto L4a;
                case 181: goto L4a;
                case 182: goto L47;
                case 183: goto L47;
                case 184: goto L47;
                case 185: goto L47;
                case 186: goto L5b;
                case 187: goto L59;
                case 188: goto L56;
                case 189: goto L54;
                case 190: goto L53;
                case 191: goto L50;
                case 192: goto L5b;
                case 193: goto L59;
                case 194: goto L56;
                case 195: goto L54;
                case 196: goto L53;
                case 197: goto L50;
                default: goto Lf;
            }
        Lf:
            switch(r2) {
                case 44: goto L4d;
                case 45: goto L4a;
                case 46: goto L47;
                case 47: goto L5b;
                case 48: goto L59;
                case 49: goto L56;
                case 50: goto L54;
                case 51: goto L53;
                case 52: goto L50;
                case 53: goto L5b;
                case 54: goto L59;
                case 55: goto L56;
                case 56: goto L54;
                case 57: goto L53;
                case 58: goto L50;
                case 59: goto L5b;
                case 60: goto L5b;
                case 61: goto L5b;
                case 62: goto L59;
                case 63: goto L56;
                case 64: goto L54;
                case 65: goto L5b;
                case 66: goto L59;
                case 67: goto L56;
                case 68: goto L54;
                case 69: goto L53;
                case 70: goto L50;
                default: goto L12;
            }
        L12:
            switch(r2) {
                case 103: goto L53;
                case 104: goto L59;
                case 105: goto L56;
                case 106: goto L54;
                case 107: goto L53;
                case 108: goto L50;
                case 109: goto L50;
                default: goto L15;
            }
        L15:
            switch(r2) {
                case 49153: goto L44;
                case 49154: goto L44;
                case 49155: goto L44;
                case 49156: goto L44;
                case 49157: goto L44;
                case 49158: goto L41;
                case 49159: goto L41;
                case 49160: goto L41;
                case 49161: goto L41;
                case 49162: goto L41;
                case 49163: goto L3e;
                case 49164: goto L3e;
                case 49165: goto L3e;
                case 49166: goto L3e;
                case 49167: goto L3e;
                case 49168: goto L3b;
                case 49169: goto L3b;
                case 49170: goto L3b;
                case 49171: goto L3b;
                case 49172: goto L3b;
                case 49173: goto L38;
                case 49174: goto L38;
                case 49175: goto L38;
                case 49176: goto L38;
                case 49177: goto L38;
                case 49178: goto L35;
                case 49179: goto L32;
                case 49180: goto L2f;
                case 49181: goto L35;
                case 49182: goto L32;
                case 49183: goto L2f;
                case 49184: goto L35;
                case 49185: goto L32;
                case 49186: goto L2f;
                case 49187: goto L41;
                case 49188: goto L41;
                case 49189: goto L44;
                case 49190: goto L44;
                case 49191: goto L3b;
                case 49192: goto L3b;
                case 49193: goto L3e;
                case 49194: goto L3e;
                case 49195: goto L41;
                case 49196: goto L41;
                case 49197: goto L44;
                case 49198: goto L44;
                case 49199: goto L3b;
                case 49200: goto L3b;
                case 49201: goto L3e;
                case 49202: goto L3e;
                case 49203: goto L2c;
                case 49204: goto L2c;
                case 49205: goto L2c;
                case 49206: goto L2c;
                case 49207: goto L2c;
                case 49208: goto L2c;
                case 49209: goto L2c;
                case 49210: goto L2c;
                case 49211: goto L2c;
                default: goto L18;
            }
        L18:
            switch(r2) {
                case 49266: goto L41;
                case 49267: goto L41;
                case 49268: goto L44;
                case 49269: goto L44;
                case 49270: goto L3b;
                case 49271: goto L3b;
                case 49272: goto L3e;
                case 49273: goto L3e;
                case 49274: goto L5b;
                case 49275: goto L5b;
                case 49276: goto L53;
                case 49277: goto L53;
                case 49278: goto L56;
                case 49279: goto L56;
                case 49280: goto L54;
                case 49281: goto L54;
                case 49282: goto L59;
                case 49283: goto L59;
                case 49284: goto L50;
                case 49285: goto L50;
                case 49286: goto L41;
                case 49287: goto L41;
                case 49288: goto L44;
                case 49289: goto L44;
                case 49290: goto L3b;
                case 49291: goto L3b;
                case 49292: goto L3e;
                case 49293: goto L3e;
                case 49294: goto L4d;
                case 49295: goto L4d;
                case 49296: goto L4a;
                case 49297: goto L4a;
                case 49298: goto L47;
                case 49299: goto L47;
                case 49300: goto L4d;
                case 49301: goto L4d;
                case 49302: goto L4a;
                case 49303: goto L4a;
                case 49304: goto L47;
                case 49305: goto L47;
                case 49306: goto L2c;
                case 49307: goto L2c;
                case 49308: goto L5b;
                case 49309: goto L5b;
                case 49310: goto L53;
                case 49311: goto L53;
                case 49312: goto L5b;
                case 49313: goto L5b;
                case 49314: goto L53;
                case 49315: goto L53;
                case 49316: goto L4d;
                case 49317: goto L4d;
                case 49318: goto L4a;
                case 49319: goto L4a;
                case 49320: goto L4d;
                case 49321: goto L4d;
                case 49322: goto L4a;
                case 49323: goto L4a;
                case 49324: goto L41;
                case 49325: goto L41;
                case 49326: goto L41;
                case 49327: goto L41;
                default: goto L1b;
            }
        L1b:
            switch(r2) {
                case 52392: goto L3b;
                case 52393: goto L41;
                case 52394: goto L53;
                case 52395: goto L4d;
                case 52396: goto L2c;
                case 52397: goto L4a;
                case 52398: goto L47;
                default: goto L1e;
            }
        L1e:
            switch(r2) {
                case 65280: goto L53;
                case 65281: goto L53;
                case 65282: goto L3b;
                case 65283: goto L3b;
                case 65284: goto L41;
                case 65285: goto L41;
                default: goto L21;
            }
        L21:
            switch(r2) {
                case 65296: goto L4d;
                case 65297: goto L4d;
                case 65298: goto L4a;
                case 65299: goto L4a;
                case 65300: goto L2c;
                case 65301: goto L2c;
                default: goto L24;
            }
        L24:
            org.bouncycastle.crypto.tls.TlsFatalAlert r2 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r0 = 80
            r2.<init>(r0)
            throw r2
        L2c:
            r2 = 24
            return r2
        L2f:
            r2 = 22
            return r2
        L32:
            r2 = 23
            return r2
        L35:
            r2 = 21
            return r2
        L38:
            r2 = 20
            return r2
        L3b:
            r2 = 19
            return r2
        L3e:
            r2 = 18
            return r2
        L41:
            r2 = 17
            return r2
        L44:
            r2 = 16
            return r2
        L47:
            r2 = 15
            return r2
        L4a:
            r2 = 14
            return r2
        L4d:
            r2 = 13
            return r2
        L50:
            r2 = 11
            return r2
        L53:
            return r1
        L54:
            r2 = 3
            return r2
        L56:
            r2 = 9
            return r2
        L59:
            r2 = 7
            return r2
        L5b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.TlsUtils.getKeyExchangeAlgorithm(int):int");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:817)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:730)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:155)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:730)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:155)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:730)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:155)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:730)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:155)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARN: Removed duplicated region for block: B:20:0x002c A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getMACAlgorithm(int r4) throws java.io.IOException {
        /*
            r0 = 1
            if (r4 == r0) goto L32
            r1 = 2
            if (r4 == r1) goto L31
            r2 = 4
            if (r4 == r2) goto L32
            r3 = 5
            if (r4 == r3) goto L31
            switch(r4) {
                case 10: goto L31;
                case 13: goto L31;
                case 16: goto L31;
                case 19: goto L31;
                case 22: goto L31;
                case 24: goto L32;
                case 27: goto L31;
                case 132: goto L31;
                case 133: goto L31;
                case 134: goto L31;
                case 135: goto L31;
                case 136: goto L31;
                case 137: goto L31;
                case 138: goto L31;
                case 139: goto L31;
                case 140: goto L31;
                case 141: goto L31;
                case 142: goto L31;
                case 143: goto L31;
                case 144: goto L31;
                case 145: goto L31;
                case 146: goto L31;
                case 147: goto L31;
                case 148: goto L31;
                case 149: goto L31;
                case 150: goto L31;
                case 151: goto L31;
                case 152: goto L31;
                case 153: goto L31;
                case 154: goto L31;
                case 155: goto L31;
                case 156: goto L2f;
                case 157: goto L2f;
                case 158: goto L2f;
                case 159: goto L2f;
                case 160: goto L2f;
                case 161: goto L2f;
                case 162: goto L2f;
                case 163: goto L2f;
                case 164: goto L2f;
                case 165: goto L2f;
                case 166: goto L2f;
                case 167: goto L2f;
                case 168: goto L2f;
                case 169: goto L2f;
                case 170: goto L2f;
                case 171: goto L2f;
                case 172: goto L2f;
                case 173: goto L2f;
                case 174: goto L2d;
                case 175: goto L2c;
                case 176: goto L2d;
                case 177: goto L2c;
                case 178: goto L2d;
                case 179: goto L2c;
                case 180: goto L2d;
                case 181: goto L2c;
                case 182: goto L2d;
                case 183: goto L2c;
                case 184: goto L2d;
                case 185: goto L2c;
                case 186: goto L2d;
                case 187: goto L2d;
                case 188: goto L2d;
                case 189: goto L2d;
                case 190: goto L2d;
                case 191: goto L2d;
                case 192: goto L2d;
                case 193: goto L2d;
                case 194: goto L2d;
                case 195: goto L2d;
                case 196: goto L2d;
                case 197: goto L2d;
                default: goto Lf;
            }
        Lf:
            switch(r4) {
                case 44: goto L31;
                case 45: goto L31;
                case 46: goto L31;
                case 47: goto L31;
                case 48: goto L31;
                case 49: goto L31;
                case 50: goto L31;
                case 51: goto L31;
                case 52: goto L31;
                case 53: goto L31;
                case 54: goto L31;
                case 55: goto L31;
                case 56: goto L31;
                case 57: goto L31;
                case 58: goto L31;
                case 59: goto L2d;
                case 60: goto L2d;
                case 61: goto L2d;
                case 62: goto L2d;
                case 63: goto L2d;
                case 64: goto L2d;
                case 65: goto L31;
                case 66: goto L31;
                case 67: goto L31;
                case 68: goto L31;
                case 69: goto L31;
                case 70: goto L31;
                default: goto L12;
            }
        L12:
            switch(r4) {
                case 103: goto L2d;
                case 104: goto L2d;
                case 105: goto L2d;
                case 106: goto L2d;
                case 107: goto L2d;
                case 108: goto L2d;
                case 109: goto L2d;
                default: goto L15;
            }
        L15:
            switch(r4) {
                case 49153: goto L31;
                case 49154: goto L31;
                case 49155: goto L31;
                case 49156: goto L31;
                case 49157: goto L31;
                case 49158: goto L31;
                case 49159: goto L31;
                case 49160: goto L31;
                case 49161: goto L31;
                case 49162: goto L31;
                case 49163: goto L31;
                case 49164: goto L31;
                case 49165: goto L31;
                case 49166: goto L31;
                case 49167: goto L31;
                case 49168: goto L31;
                case 49169: goto L31;
                case 49170: goto L31;
                case 49171: goto L31;
                case 49172: goto L31;
                case 49173: goto L31;
                case 49174: goto L31;
                case 49175: goto L31;
                case 49176: goto L31;
                case 49177: goto L31;
                case 49178: goto L31;
                case 49179: goto L31;
                case 49180: goto L31;
                case 49181: goto L31;
                case 49182: goto L31;
                case 49183: goto L31;
                case 49184: goto L31;
                case 49185: goto L31;
                case 49186: goto L31;
                case 49187: goto L2d;
                case 49188: goto L2c;
                case 49189: goto L2d;
                case 49190: goto L2c;
                case 49191: goto L2d;
                case 49192: goto L2c;
                case 49193: goto L2d;
                case 49194: goto L2c;
                case 49195: goto L2f;
                case 49196: goto L2f;
                case 49197: goto L2f;
                case 49198: goto L2f;
                case 49199: goto L2f;
                case 49200: goto L2f;
                case 49201: goto L2f;
                case 49202: goto L2f;
                case 49203: goto L31;
                case 49204: goto L31;
                case 49205: goto L31;
                case 49206: goto L31;
                case 49207: goto L2d;
                case 49208: goto L2c;
                case 49209: goto L31;
                case 49210: goto L2d;
                case 49211: goto L2c;
                default: goto L18;
            }
        L18:
            switch(r4) {
                case 49266: goto L2d;
                case 49267: goto L2c;
                case 49268: goto L2d;
                case 49269: goto L2c;
                case 49270: goto L2d;
                case 49271: goto L2c;
                case 49272: goto L2d;
                case 49273: goto L2c;
                case 49274: goto L2f;
                case 49275: goto L2f;
                case 49276: goto L2f;
                case 49277: goto L2f;
                case 49278: goto L2f;
                case 49279: goto L2f;
                case 49280: goto L2f;
                case 49281: goto L2f;
                case 49282: goto L2f;
                case 49283: goto L2f;
                case 49284: goto L2f;
                case 49285: goto L2f;
                case 49286: goto L2f;
                case 49287: goto L2f;
                case 49288: goto L2f;
                case 49289: goto L2f;
                case 49290: goto L2f;
                case 49291: goto L2f;
                case 49292: goto L2f;
                case 49293: goto L2f;
                case 49294: goto L2f;
                case 49295: goto L2f;
                case 49296: goto L2f;
                case 49297: goto L2f;
                case 49298: goto L2f;
                case 49299: goto L2f;
                case 49300: goto L2d;
                case 49301: goto L2c;
                case 49302: goto L2d;
                case 49303: goto L2c;
                case 49304: goto L2d;
                case 49305: goto L2c;
                case 49306: goto L2d;
                case 49307: goto L2c;
                case 49308: goto L2f;
                case 49309: goto L2f;
                case 49310: goto L2f;
                case 49311: goto L2f;
                case 49312: goto L2f;
                case 49313: goto L2f;
                case 49314: goto L2f;
                case 49315: goto L2f;
                case 49316: goto L2f;
                case 49317: goto L2f;
                case 49318: goto L2f;
                case 49319: goto L2f;
                case 49320: goto L2f;
                case 49321: goto L2f;
                case 49322: goto L2f;
                case 49323: goto L2f;
                case 49324: goto L2f;
                case 49325: goto L2f;
                case 49326: goto L2f;
                case 49327: goto L2f;
                default: goto L1b;
            }
        L1b:
            switch(r4) {
                case 52392: goto L2f;
                case 52393: goto L2f;
                case 52394: goto L2f;
                case 52395: goto L2f;
                case 52396: goto L2f;
                case 52397: goto L2f;
                case 52398: goto L2f;
                default: goto L1e;
            }
        L1e:
            switch(r4) {
                case 65280: goto L2f;
                case 65281: goto L2f;
                case 65282: goto L2f;
                case 65283: goto L2f;
                case 65284: goto L2f;
                case 65285: goto L2f;
                default: goto L21;
            }
        L21:
            switch(r4) {
                case 65296: goto L2f;
                case 65297: goto L2f;
                case 65298: goto L2f;
                case 65299: goto L2f;
                case 65300: goto L2f;
                case 65301: goto L2f;
                default: goto L24;
            }
        L24:
            org.bouncycastle.crypto.tls.TlsFatalAlert r4 = new org.bouncycastle.crypto.tls.TlsFatalAlert
            r0 = 80
            r4.<init>(r0)
            throw r4
        L2c:
            return r2
        L2d:
            r4 = 3
            return r4
        L2f:
            r4 = 0
            return r4
        L31:
            return r1
        L32:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.TlsUtils.getMACAlgorithm(int):int");
    }

    public static ProtocolVersion getMinimumVersion(int i) {
        switch (i) {
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
                break;
            default:
                switch (i) {
                    case 103:
                    case 104:
                    case 105:
                    case 106:
                    case 107:
                    case 108:
                    case 109:
                        break;
                    default:
                        switch (i) {
                            case 156:
                            case 157:
                            case 158:
                            case 159:
                            case 160:
                            case 161:
                            case 162:
                            case 163:
                            case 164:
                            case 165:
                            case 166:
                            case 167:
                            case 168:
                            case 169:
                            case 170:
                            case 171:
                            case 172:
                            case 173:
                                break;
                            default:
                                switch (i) {
                                    case 186:
                                    case 187:
                                    case 188:
                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 189 */:
                                    case 190:
                                    case 191:
                                    case 192:
                                    case 193:
                                    case 194:
                                    case 195:
                                    case 196:
                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /* 197 */:
                                        break;
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
                                                break;
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
                                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
                                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
                                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
                                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
                                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
                                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
                                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
                                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
                                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
                                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
                                                            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
                                                            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
                                                                    case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
                                                                    case CipherSuite.DRAFT_TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
                                                                        break;
                                                                    default:
                                                                        switch (i) {
                                                                            case 65280:
                                                                            case 65281:
                                                                            case 65282:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB /* 65283 */:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB /* 65284 */:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB /* 65285 */:
                                                                                break;
                                                                            default:
                                                                                switch (i) {
                                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_AES_128_OCB /* 65296 */:
                                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_AES_256_OCB /* 65297 */:
                                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_128_OCB /* 65298 */:
                                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_256_OCB /* 65299 */:
                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB /* 65300 */:
                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB /* 65301 */:
                                                                                        break;
                                                                                    default:
                                                                                        return ProtocolVersion.SSLv3;
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
        return ProtocolVersion.TLSv12;
    }

    public static ASN1ObjectIdentifier getOIDForHashAlgorithm(short s) {
        switch (s) {
            case 1:
                return PKCSObjectIdentifiers.md5;
            case 2:
                return X509ObjectIdentifiers.id_SHA1;
            case 3:
                return NISTObjectIdentifiers.id_sha224;
            case 4:
                return NISTObjectIdentifiers.id_sha256;
            case 5:
                return NISTObjectIdentifiers.id_sha384;
            case 6:
                return NISTObjectIdentifiers.id_sha512;
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Vector getSignatureAlgorithmsExtension(Hashtable hashtable) throws IOException {
        byte[] extensionData = getExtensionData(hashtable, EXT_signature_algorithms);
        if (extensionData == null) {
            return null;
        }
        return readSignatureAlgorithmsExtension(extensionData);
    }

    public static SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(TlsContext tlsContext, TlsSignerCredentials tlsSignerCredentials) throws IOException {
        if (isTLSv12(tlsContext)) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = tlsSignerCredentials.getSignatureAndHashAlgorithm();
            if (signatureAndHashAlgorithm != null) {
                return signatureAndHashAlgorithm;
            }
            throw new TlsFatalAlert((short) 80);
        }
        return null;
    }

    public static Vector getUsableSignatureAlgorithms(Vector vector) {
        if (vector == null) {
            return getAllSignatureAlgorithms();
        }
        Vector vector2 = new Vector(4);
        vector2.addElement(Shorts.valueOf((short) 0));
        for (int i = 0; i < vector.size(); i++) {
            Short valueOf = Shorts.valueOf(((SignatureAndHashAlgorithm) vector.elementAt(i)).getSignature());
            if (!vector2.contains(valueOf)) {
                vector2.addElement(valueOf);
            }
        }
        return vector2;
    }

    public static byte[][] h() {
        byte[][] bArr = new byte[10];
        int i = 0;
        while (i < 10) {
            int i2 = i + 1;
            byte[] bArr2 = new byte[i2];
            Arrays.fill(bArr2, (byte) (i + 65));
            bArr[i] = bArr2;
            i = i2;
        }
        return bArr;
    }

    public static boolean hasExpectedEmptyExtensionData(Hashtable hashtable, Integer num, short s) throws IOException {
        byte[] extensionData = getExtensionData(hashtable, num);
        if (extensionData == null) {
            return false;
        }
        if (extensionData.length == 0) {
            return true;
        }
        throw new TlsFatalAlert(s);
    }

    public static boolean hasSigningCapability(short s) {
        return s == 1 || s == 2 || s == 64;
    }

    public static short i(Certificate certificate, Certificate certificate2) throws IOException {
        if (certificate.isEmpty()) {
            return (short) -1;
        }
        org.bouncycastle.asn1.x509.Certificate certificateAt = certificate.getCertificateAt(0);
        try {
            AsymmetricKeyParameter createKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
            if (createKey.isPrivate()) {
                throw new TlsFatalAlert((short) 80);
            }
            if (createKey instanceof RSAKeyParameters) {
                l(certificateAt, 128);
                return (short) 1;
            } else if (createKey instanceof DSAPublicKeyParameters) {
                l(certificateAt, 128);
                return (short) 2;
            } else if (createKey instanceof ECPublicKeyParameters) {
                l(certificateAt, 128);
                return (short) 64;
            } else {
                throw new TlsFatalAlert((short) 43);
            }
        } catch (Exception e) {
            throw new TlsFatalAlert((short) 43, e);
        }
    }

    public static TlsSession importSession(byte[] bArr, SessionParameters sessionParameters) {
        return new q(bArr, sessionParameters);
    }

    public static boolean isAEADCipherSuite(int i) throws IOException {
        return 2 == getCipherType(i);
    }

    public static boolean isBlockCipherSuite(int i) throws IOException {
        return 1 == getCipherType(i);
    }

    public static boolean isSSL(TlsContext tlsContext) {
        return tlsContext.getServerVersion().isSSL();
    }

    public static boolean isSignatureAlgorithmsExtensionAllowed(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isStreamCipherSuite(int i) throws IOException {
        return getCipherType(i) == 0;
    }

    public static boolean isTLSv11(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv11.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv11(TlsContext tlsContext) {
        return isTLSv11(tlsContext.getServerVersion());
    }

    public static boolean isTLSv12(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv12(TlsContext tlsContext) {
        return isTLSv12(tlsContext.getServerVersion());
    }

    public static boolean isValidCipherSuiteForSignatureAlgorithms(int i, Vector vector) {
        short s;
        Short valueOf;
        try {
            int keyExchangeAlgorithm = getKeyExchangeAlgorithm(i);
            if (keyExchangeAlgorithm != 3 && keyExchangeAlgorithm != 4) {
                if (keyExchangeAlgorithm != 5 && keyExchangeAlgorithm != 6) {
                    if (keyExchangeAlgorithm != 11 && keyExchangeAlgorithm != 12) {
                        if (keyExchangeAlgorithm == 17) {
                            valueOf = Shorts.valueOf((short) 3);
                            return vector.contains(valueOf);
                        } else if (keyExchangeAlgorithm != 19) {
                            if (keyExchangeAlgorithm != 20) {
                                if (keyExchangeAlgorithm != 22) {
                                    if (keyExchangeAlgorithm != 23) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    s = 0;
                    valueOf = Shorts.valueOf(s);
                    return vector.contains(valueOf);
                }
                valueOf = Shorts.valueOf((short) 1);
                return vector.contains(valueOf);
            }
            s = 2;
            valueOf = Shorts.valueOf(s);
            return vector.contains(valueOf);
        } catch (IOException unused) {
            return true;
        }
    }

    public static boolean isValidCipherSuiteForVersion(int i, ProtocolVersion protocolVersion) {
        return getMinimumVersion(i).isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isValidUint16(int i) {
        return (65535 & i) == i;
    }

    public static boolean isValidUint16(long j) {
        return (WebSocketProtocol.PAYLOAD_SHORT_MAX & j) == j;
    }

    public static boolean isValidUint24(int i) {
        return (16777215 & i) == i;
    }

    public static boolean isValidUint24(long j) {
        return (16777215 & j) == j;
    }

    public static boolean isValidUint32(long j) {
        return (4294967295L & j) == j;
    }

    public static boolean isValidUint48(long j) {
        return (281474976710655L & j) == j;
    }

    public static boolean isValidUint64(long j) {
        return true;
    }

    public static boolean isValidUint8(int i) {
        return (i & 255) == i;
    }

    public static boolean isValidUint8(long j) {
        return (255 & j) == j;
    }

    public static boolean isValidUint8(short s) {
        return (s & 255) == s;
    }

    public static void j(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        HMac hMac = new HMac(digest);
        hMac.init(new KeyParameter(bArr));
        int digestSize = digest.getDigestSize();
        int length = ((bArr3.length + digestSize) - 1) / digestSize;
        int macSize = hMac.getMacSize();
        byte[] bArr4 = new byte[macSize];
        byte[] bArr5 = new byte[hMac.getMacSize()];
        byte[] bArr6 = bArr2;
        int i = 0;
        while (i < length) {
            hMac.update(bArr6, 0, bArr6.length);
            hMac.doFinal(bArr4, 0);
            hMac.update(bArr4, 0, macSize);
            hMac.update(bArr2, 0, bArr2.length);
            hMac.doFinal(bArr5, 0);
            int i2 = digestSize * i;
            System.arraycopy(bArr5, 0, bArr3, i2, Math.min(digestSize, bArr3.length - i2));
            i++;
            bArr6 = bArr4;
        }
    }

    public static void k(TlsHandshakeHash tlsHandshakeHash, Vector vector) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); i++) {
                short hash = ((SignatureAndHashAlgorithm) vector.elementAt(i)).getHash();
                if (!HashAlgorithm.isPrivate(hash)) {
                    tlsHandshakeHash.trackHashAlgorithm(hash);
                }
            }
        }
    }

    public static void l(org.bouncycastle.asn1.x509.Certificate certificate, int i) throws IOException {
        KeyUsage fromExtensions;
        Extensions extensions = certificate.getTBSCertificate().getExtensions();
        if (extensions != null && (fromExtensions = KeyUsage.fromExtensions(extensions)) != null && (fromExtensions.getBytes()[0] & 255 & i) != i) {
            throw new TlsFatalAlert((short) 46);
        }
    }

    public static Vector m(Object obj) {
        Vector vector = new Vector(1);
        vector.addElement(obj);
        return vector;
    }

    public static Vector parseSupportedSignatureAlgorithms(boolean z, InputStream inputStream) throws IOException {
        int readUint16 = readUint16(inputStream);
        if (readUint16 < 2 || (readUint16 & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        int i = readUint16 / 2;
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 < i; i2++) {
            SignatureAndHashAlgorithm parse = SignatureAndHashAlgorithm.parse(inputStream);
            if (!z && parse.getSignature() == 0) {
                throw new TlsFatalAlert((short) 47);
            }
            vector.addElement(parse);
        }
        return vector;
    }

    public static ASN1Primitive readASN1Object(byte[] bArr) throws IOException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        ASN1Primitive readObject = aSN1InputStream.readObject();
        if (readObject != null) {
            if (aSN1InputStream.readObject() == null) {
                return readObject;
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readAllOrNothing(int i, InputStream inputStream) throws IOException {
        if (i < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i];
        int readFully = Streams.readFully(inputStream, bArr);
        if (readFully == 0) {
            return null;
        }
        if (readFully == i) {
            return bArr;
        }
        throw new EOFException();
    }

    public static ASN1Primitive readDERObject(byte[] bArr) throws IOException {
        ASN1Primitive readASN1Object = readASN1Object(bArr);
        if (Arrays.areEqual(readASN1Object.getEncoded(ASN1Encoding.DER), bArr)) {
            return readASN1Object;
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static void readFully(byte[] bArr, InputStream inputStream) throws IOException {
        int length = bArr.length;
        if (length > 0 && length != Streams.readFully(inputStream, bArr)) {
            throw new EOFException();
        }
    }

    public static byte[] readFully(int i, InputStream inputStream) throws IOException {
        if (i < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i];
        if (i == Streams.readFully(inputStream, bArr)) {
            return bArr;
        }
        throw new EOFException();
    }

    public static byte[] readOpaque16(InputStream inputStream) throws IOException {
        return readFully(readUint16(inputStream), inputStream);
    }

    public static byte[] readOpaque24(InputStream inputStream) throws IOException {
        return readFully(readUint24(inputStream), inputStream);
    }

    public static byte[] readOpaque8(InputStream inputStream) throws IOException {
        return readFully(readUint8(inputStream), inputStream);
    }

    public static Vector readSignatureAlgorithmsExtension(byte[] bArr) throws IOException {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            Vector parseSupportedSignatureAlgorithms = parseSupportedSignatureAlgorithms(false, byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            return parseSupportedSignatureAlgorithms;
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }

    public static int readUint16(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return read2 | (read << 8);
        }
        throw new EOFException();
    }

    public static int readUint16(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    public static int[] readUint16Array(int i, InputStream inputStream) throws IOException {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readUint16(inputStream);
        }
        return iArr;
    }

    public static int readUint24(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        if (read3 >= 0) {
            return read3 | (read << 16) | (read2 << 8);
        }
        throw new EOFException();
    }

    public static int readUint24(byte[] bArr, int i) {
        int i2 = i + 1;
        return (bArr[i2 + 1] & 255) | ((bArr[i] & 255) << 16) | ((bArr[i2] & 255) << 8);
    }

    public static long readUint32(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        int read4 = inputStream.read();
        if (read4 >= 0) {
            return (read4 | (read << 24) | (read2 << 16) | (read3 << 8)) & 4294967295L;
        }
        throw new EOFException();
    }

    public static long readUint32(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return ((bArr[i3 + 1] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8)) & 4294967295L;
    }

    public static long readUint48(InputStream inputStream) throws IOException {
        return ((readUint24(inputStream) & 4294967295L) << 24) | (4294967295L & readUint24(inputStream));
    }

    public static long readUint48(byte[] bArr, int i) {
        int readUint24 = readUint24(bArr, i);
        return (readUint24(bArr, i + 3) & 4294967295L) | ((readUint24 & 4294967295L) << 24);
    }

    public static short readUint8(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read >= 0) {
            return (short) read;
        }
        throw new EOFException();
    }

    public static short readUint8(byte[] bArr, int i) {
        return (short) (bArr[i] & 255);
    }

    public static short[] readUint8Array(int i, InputStream inputStream) throws IOException {
        short[] sArr = new short[i];
        for (int i2 = 0; i2 < i; i2++) {
            sArr[i2] = readUint8(inputStream);
        }
        return sArr;
    }

    public static ProtocolVersion readVersion(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return ProtocolVersion.get(read, read2);
        }
        throw new EOFException();
    }

    public static ProtocolVersion readVersion(byte[] bArr, int i) throws IOException {
        return ProtocolVersion.get(bArr[i] & 255, bArr[i + 1] & 255);
    }

    public static int readVersionRaw(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return read2 | (read << 8);
        }
        throw new EOFException();
    }

    public static int readVersionRaw(byte[] bArr, int i) throws IOException {
        return bArr[i + 1] | (bArr[i] << 8);
    }

    public static void verifySupportedSignatureAlgorithm(Vector vector, SignatureAndHashAlgorithm signatureAndHashAlgorithm) throws IOException {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        if (signatureAndHashAlgorithm == null) {
            throw new IllegalArgumentException("'signatureAlgorithm' cannot be null");
        }
        if (signatureAndHashAlgorithm.getSignature() != 0) {
            for (int i = 0; i < vector.size(); i++) {
                SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = (SignatureAndHashAlgorithm) vector.elementAt(i);
                if (signatureAndHashAlgorithm2.getHash() == signatureAndHashAlgorithm.getHash() && signatureAndHashAlgorithm2.getSignature() == signatureAndHashAlgorithm.getSignature()) {
                    return;
                }
            }
        }
        throw new TlsFatalAlert((short) 47);
    }

    public static void writeGMTUnixTime(byte[] bArr, int i) {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        bArr[i] = (byte) (currentTimeMillis >>> 24);
        bArr[i + 1] = (byte) (currentTimeMillis >>> 16);
        bArr[i + 2] = (byte) (currentTimeMillis >>> 8);
        bArr[i + 3] = (byte) currentTimeMillis;
    }

    public static void writeOpaque16(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint16(bArr.length);
        writeUint16(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque24(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint24(bArr.length);
        writeUint24(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque8(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint8(bArr.length);
        writeUint8(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeUint16(int i, OutputStream outputStream) throws IOException {
        outputStream.write(i >>> 8);
        outputStream.write(i);
    }

    public static void writeUint16(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
    }

    public static void writeUint16Array(int[] iArr, OutputStream outputStream) throws IOException {
        for (int i : iArr) {
            writeUint16(i, outputStream);
        }
    }

    public static void writeUint16Array(int[] iArr, byte[] bArr, int i) throws IOException {
        for (int i2 : iArr) {
            writeUint16(i2, bArr, i);
            i += 2;
        }
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, OutputStream outputStream) throws IOException {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, outputStream);
        writeUint16Array(iArr, outputStream);
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, byte[] bArr, int i) throws IOException {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, bArr, i);
        writeUint16Array(iArr, bArr, i + 2);
    }

    public static void writeUint24(int i, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (i >>> 16));
        outputStream.write((byte) (i >>> 8));
        outputStream.write((byte) i);
    }

    public static void writeUint24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) i;
    }

    public static void writeUint32(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) j);
    }

    public static void writeUint32(long j, byte[] bArr, int i) {
        bArr[i] = (byte) (j >>> 24);
        bArr[i + 1] = (byte) (j >>> 16);
        bArr[i + 2] = (byte) (j >>> 8);
        bArr[i + 3] = (byte) j;
    }

    public static void writeUint48(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) j);
    }

    public static void writeUint48(long j, byte[] bArr, int i) {
        bArr[i] = (byte) (j >>> 40);
        bArr[i + 1] = (byte) (j >>> 32);
        bArr[i + 2] = (byte) (j >>> 24);
        bArr[i + 3] = (byte) (j >>> 16);
        bArr[i + 4] = (byte) (j >>> 8);
        bArr[i + 5] = (byte) j;
    }

    public static void writeUint64(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j >>> 56));
        outputStream.write((byte) (j >>> 48));
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) j);
    }

    public static void writeUint64(long j, byte[] bArr, int i) {
        bArr[i] = (byte) (j >>> 56);
        bArr[i + 1] = (byte) (j >>> 48);
        bArr[i + 2] = (byte) (j >>> 40);
        bArr[i + 3] = (byte) (j >>> 32);
        bArr[i + 4] = (byte) (j >>> 24);
        bArr[i + 5] = (byte) (j >>> 16);
        bArr[i + 6] = (byte) (j >>> 8);
        bArr[i + 7] = (byte) j;
    }

    public static void writeUint8(int i, OutputStream outputStream) throws IOException {
        outputStream.write(i);
    }

    public static void writeUint8(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
    }

    public static void writeUint8(short s, OutputStream outputStream) throws IOException {
        outputStream.write(s);
    }

    public static void writeUint8(short s, byte[] bArr, int i) {
        bArr[i] = (byte) s;
    }

    public static void writeUint8Array(short[] sArr, OutputStream outputStream) throws IOException {
        for (short s : sArr) {
            writeUint8(s, outputStream);
        }
    }

    public static void writeUint8Array(short[] sArr, byte[] bArr, int i) throws IOException {
        for (short s : sArr) {
            writeUint8(s, bArr, i);
            i++;
        }
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, OutputStream outputStream) throws IOException {
        checkUint8(sArr.length);
        writeUint8(sArr.length, outputStream);
        writeUint8Array(sArr, outputStream);
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, byte[] bArr, int i) throws IOException {
        checkUint8(sArr.length);
        writeUint8(sArr.length, bArr, i);
        writeUint8Array(sArr, bArr, i + 1);
    }

    public static void writeVersion(ProtocolVersion protocolVersion, OutputStream outputStream) throws IOException {
        outputStream.write(protocolVersion.getMajorVersion());
        outputStream.write(protocolVersion.getMinorVersion());
    }

    public static void writeVersion(ProtocolVersion protocolVersion, byte[] bArr, int i) {
        bArr[i] = (byte) protocolVersion.getMajorVersion();
        bArr[i + 1] = (byte) protocolVersion.getMinorVersion();
    }
}
