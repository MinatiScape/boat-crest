package org.bouncycastle.openssl;

import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.pem.PemGenerationException;
import org.bouncycastle.util.io.pem.PemHeader;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemObjectGenerator;
/* loaded from: classes13.dex */
public class MiscPEMGenerator implements PemObjectGenerator {
    public static final ASN1ObjectIdentifier[] c = {X9ObjectIdentifiers.id_dsa, OIWObjectIdentifiers.dsaWithSHA1};
    public static final byte[] d = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, com.crrepa.c.a.E0, com.htsmart.wristband2.a.a.a.U0};

    /* renamed from: a  reason: collision with root package name */
    public final Object f15197a;
    public final PEMEncryptor b;

    public MiscPEMGenerator(Object obj) {
        this.f15197a = obj;
        this.b = null;
    }

    public MiscPEMGenerator(Object obj, PEMEncryptor pEMEncryptor) {
        this.f15197a = obj;
        this.b = pEMEncryptor;
    }

    public final PemObject a(Object obj) throws IOException {
        byte[] encoded;
        String str;
        if (obj instanceof PemObject) {
            return (PemObject) obj;
        }
        if (obj instanceof PemObjectGenerator) {
            return ((PemObjectGenerator) obj).generate();
        }
        if (obj instanceof X509CertificateHolder) {
            encoded = ((X509CertificateHolder) obj).getEncoded();
            str = "CERTIFICATE";
        } else if (obj instanceof X509CRLHolder) {
            encoded = ((X509CRLHolder) obj).getEncoded();
            str = "X509 CRL";
        } else if (obj instanceof X509TrustedCertificateBlock) {
            encoded = ((X509TrustedCertificateBlock) obj).getEncoded();
            str = "TRUSTED CERTIFICATE";
        } else if (obj instanceof PrivateKeyInfo) {
            PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) obj;
            ASN1ObjectIdentifier algorithm = privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm();
            if (algorithm.equals(PKCSObjectIdentifiers.rsaEncryption)) {
                encoded = privateKeyInfo.parsePrivateKey().toASN1Primitive().getEncoded();
                str = "RSA PRIVATE KEY";
            } else {
                ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = c;
                if (algorithm.equals(aSN1ObjectIdentifierArr[0]) || algorithm.equals(aSN1ObjectIdentifierArr[1])) {
                    DSAParameter dSAParameter = DSAParameter.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
                    ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                    aSN1EncodableVector.add(new ASN1Integer(0L));
                    aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getP()));
                    aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getQ()));
                    aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getG()));
                    BigInteger value = ASN1Integer.getInstance(privateKeyInfo.parsePrivateKey()).getValue();
                    aSN1EncodableVector.add(new ASN1Integer(dSAParameter.getG().modPow(value, dSAParameter.getP())));
                    aSN1EncodableVector.add(new ASN1Integer(value));
                    encoded = new DERSequence(aSN1EncodableVector).getEncoded();
                    str = "DSA PRIVATE KEY";
                } else if (!algorithm.equals(X9ObjectIdentifiers.id_ecPublicKey)) {
                    throw new IOException("Cannot identify private key");
                } else {
                    encoded = privateKeyInfo.parsePrivateKey().toASN1Primitive().getEncoded();
                    str = "EC PRIVATE KEY";
                }
            }
        } else if (obj instanceof SubjectPublicKeyInfo) {
            encoded = ((SubjectPublicKeyInfo) obj).getEncoded();
            str = "PUBLIC KEY";
        } else if (obj instanceof X509AttributeCertificateHolder) {
            encoded = ((X509AttributeCertificateHolder) obj).getEncoded();
            str = "ATTRIBUTE CERTIFICATE";
        } else if (obj instanceof PKCS10CertificationRequest) {
            encoded = ((PKCS10CertificationRequest) obj).getEncoded();
            str = "CERTIFICATE REQUEST";
        } else if (obj instanceof PKCS8EncryptedPrivateKeyInfo) {
            encoded = ((PKCS8EncryptedPrivateKeyInfo) obj).getEncoded();
            str = "ENCRYPTED PRIVATE KEY";
        } else if (!(obj instanceof ContentInfo)) {
            throw new PemGenerationException("unknown object passed - can't encode.");
        } else {
            encoded = ((ContentInfo) obj).getEncoded();
            str = "PKCS7";
        }
        PEMEncryptor pEMEncryptor = this.b;
        if (pEMEncryptor != null) {
            String upperCase = Strings.toUpperCase(pEMEncryptor.getAlgorithm());
            if (upperCase.equals("DESEDE")) {
                upperCase = "DES-EDE3-CBC";
            }
            byte[] iv = this.b.getIV();
            byte[] encrypt = this.b.encrypt(encoded);
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(new PemHeader("Proc-Type", "4,ENCRYPTED"));
            arrayList.add(new PemHeader("DEK-Info", upperCase + Constants.SEPARATOR_COMMA + b(iv)));
            return new PemObject(str, arrayList, encrypt);
        }
        return new PemObject(str, encoded);
    }

    public final String b(byte[] bArr) throws IOException {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i != bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            byte[] bArr2 = d;
            cArr[i3] = (char) bArr2[i2 >>> 4];
            cArr[i3 + 1] = (char) bArr2[i2 & 15];
        }
        return new String(cArr);
    }

    @Override // org.bouncycastle.util.io.pem.PemObjectGenerator
    public PemObject generate() throws PemGenerationException {
        try {
            return a(this.f15197a);
        } catch (IOException e) {
            throw new PemGenerationException("encoding exception: " + e.getMessage(), e);
        }
    }
}
