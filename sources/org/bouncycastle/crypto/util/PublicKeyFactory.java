package org.bouncycastle.crypto.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.bouncycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.bouncycastle.asn1.oiw.ElGamalParameter;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.DHParameter;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.ua.DSTU4145BinaryField;
import org.bouncycastle.asn1.ua.DSTU4145ECBinary;
import org.bouncycastle.asn1.ua.DSTU4145NamedCurves;
import org.bouncycastle.asn1.ua.DSTU4145Params;
import org.bouncycastle.asn1.ua.DSTU4145PointEncoder;
import org.bouncycastle.asn1.ua.UAObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.x9.DHPublicKey;
import org.bouncycastle.asn1.x9.DomainParameters;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.ValidationParams;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ECPoint;
import org.bouncycastle.asn1.x9.X9IntegerConverter;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.params.DHValidationParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECNamedDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ElGamalParameters;
import org.bouncycastle.crypto.params.ElGamalPublicKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.math.ec.ECCurve;
/* loaded from: classes13.dex */
public class PublicKeyFactory {

    /* renamed from: a  reason: collision with root package name */
    public static Map f14882a;

    /* loaded from: classes13.dex */
    public static class b extends k {
        public b() {
            super();
        }

        @Override // org.bouncycastle.crypto.util.PublicKeyFactory.k
        public AsymmetricKeyParameter a(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            DHParameter dHParameter = DHParameter.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
            ASN1Integer aSN1Integer = (ASN1Integer) subjectPublicKeyInfo.parsePublicKey();
            BigInteger l = dHParameter.getL();
            return new DHPublicKeyParameters(aSN1Integer.getValue(), new DHParameters(dHParameter.getP(), dHParameter.getG(), null, l == null ? 0 : l.intValue()));
        }
    }

    /* loaded from: classes13.dex */
    public static class c extends k {
        public c() {
            super();
        }

        @Override // org.bouncycastle.crypto.util.PublicKeyFactory.k
        public AsymmetricKeyParameter a(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            BigInteger y = DHPublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey()).getY();
            DomainParameters domainParameters = DomainParameters.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
            BigInteger p = domainParameters.getP();
            BigInteger g = domainParameters.getG();
            BigInteger q = domainParameters.getQ();
            BigInteger j = domainParameters.getJ() != null ? domainParameters.getJ() : null;
            ValidationParams validationParams = domainParameters.getValidationParams();
            return new DHPublicKeyParameters(y, new DHParameters(p, g, q, j, validationParams != null ? new DHValidationParameters(validationParams.getSeed(), validationParams.getPgenCounter().intValue()) : null));
        }
    }

    /* loaded from: classes13.dex */
    public static class d extends k {
        public d() {
            super();
        }

        @Override // org.bouncycastle.crypto.util.PublicKeyFactory.k
        public AsymmetricKeyParameter a(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            DSAParameters dSAParameters;
            ASN1Integer aSN1Integer = (ASN1Integer) subjectPublicKeyInfo.parsePublicKey();
            ASN1Encodable parameters = subjectPublicKeyInfo.getAlgorithm().getParameters();
            if (parameters != null) {
                DSAParameter dSAParameter = DSAParameter.getInstance(parameters.toASN1Primitive());
                dSAParameters = new DSAParameters(dSAParameter.getP(), dSAParameter.getQ(), dSAParameter.getG());
            } else {
                dSAParameters = null;
            }
            return new DSAPublicKeyParameters(aSN1Integer.getValue(), dSAParameters);
        }
    }

    /* loaded from: classes13.dex */
    public static class e extends k {
        public e() {
            super();
        }

        @Override // org.bouncycastle.crypto.util.PublicKeyFactory.k
        public AsymmetricKeyParameter a(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            ECDomainParameters eCDomainParameters;
            try {
                byte[] octets = ((ASN1OctetString) ASN1Primitive.fromByteArray(subjectPublicKeyInfo.getPublicKeyData().getBytes())).getOctets();
                ASN1ObjectIdentifier algorithm = subjectPublicKeyInfo.getAlgorithm().getAlgorithm();
                ASN1ObjectIdentifier aSN1ObjectIdentifier = UAObjectIdentifiers.dstu4145le;
                if (algorithm.equals(aSN1ObjectIdentifier)) {
                    b(octets);
                }
                DSTU4145Params dSTU4145Params = DSTU4145Params.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
                if (dSTU4145Params.isNamedCurve()) {
                    eCDomainParameters = DSTU4145NamedCurves.getByOID(dSTU4145Params.getNamedCurve());
                } else {
                    DSTU4145ECBinary eCBinary = dSTU4145Params.getECBinary();
                    byte[] b = eCBinary.getB();
                    if (subjectPublicKeyInfo.getAlgorithm().getAlgorithm().equals(aSN1ObjectIdentifier)) {
                        b(b);
                    }
                    DSTU4145BinaryField field = eCBinary.getField();
                    ECCurve.F2m f2m = new ECCurve.F2m(field.getM(), field.getK1(), field.getK2(), field.getK3(), eCBinary.getA(), new BigInteger(1, b));
                    byte[] g = eCBinary.getG();
                    if (subjectPublicKeyInfo.getAlgorithm().getAlgorithm().equals(aSN1ObjectIdentifier)) {
                        b(g);
                    }
                    eCDomainParameters = new ECDomainParameters(f2m, DSTU4145PointEncoder.decodePoint(f2m, g), eCBinary.getN());
                }
                return new ECPublicKeyParameters(DSTU4145PointEncoder.decodePoint(eCDomainParameters.getCurve(), octets), eCDomainParameters);
            } catch (IOException unused) {
                throw new IllegalArgumentException("error recovering public key");
            }
        }

        public final void b(byte[] bArr) {
            for (int i = 0; i < bArr.length / 2; i++) {
                byte b = bArr[i];
                bArr[i] = bArr[(bArr.length - 1) - i];
                bArr[(bArr.length - 1) - i] = b;
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class f extends k {
        public f() {
            super();
        }

        @Override // org.bouncycastle.crypto.util.PublicKeyFactory.k
        public AsymmetricKeyParameter a(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) {
            ECDomainParameters eCDomainParameters;
            X962Parameters x962Parameters = X962Parameters.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
            if (x962Parameters.isNamedCurve()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) x962Parameters.getParameters();
                X9ECParameters byOID = CustomNamedCurves.getByOID(aSN1ObjectIdentifier);
                if (byOID == null) {
                    byOID = ECNamedCurveTable.getByOID(aSN1ObjectIdentifier);
                }
                eCDomainParameters = new ECNamedDomainParameters(aSN1ObjectIdentifier, byOID.getCurve(), byOID.getG(), byOID.getN(), byOID.getH(), byOID.getSeed());
            } else if (x962Parameters.isImplicitlyCA()) {
                eCDomainParameters = (ECDomainParameters) obj;
            } else {
                X9ECParameters x9ECParameters = X9ECParameters.getInstance(x962Parameters.getParameters());
                eCDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
            }
            byte[] bytes = subjectPublicKeyInfo.getPublicKeyData().getBytes();
            ASN1OctetString dEROctetString = new DEROctetString(bytes);
            if (bytes[0] == 4 && bytes[1] == bytes.length - 2 && ((bytes[2] == 2 || bytes[2] == 3) && new X9IntegerConverter().getByteLength(eCDomainParameters.getCurve()) >= bytes.length - 3)) {
                try {
                    dEROctetString = (ASN1OctetString) ASN1Primitive.fromByteArray(bytes);
                } catch (IOException unused) {
                    throw new IllegalArgumentException("error recovering public key");
                }
            }
            return new ECPublicKeyParameters(new X9ECPoint(eCDomainParameters.getCurve(), dEROctetString).getPoint(), eCDomainParameters);
        }
    }

    /* loaded from: classes13.dex */
    public static class g extends k {
        public g() {
            super();
        }

        @Override // org.bouncycastle.crypto.util.PublicKeyFactory.k
        public AsymmetricKeyParameter a(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            ElGamalParameter elGamalParameter = ElGamalParameter.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
            return new ElGamalPublicKeyParameters(((ASN1Integer) subjectPublicKeyInfo.parsePublicKey()).getValue(), new ElGamalParameters(elGamalParameter.getP(), elGamalParameter.getG()));
        }
    }

    /* loaded from: classes13.dex */
    public static class h extends k {
        public h() {
            super();
        }

        @Override // org.bouncycastle.crypto.util.PublicKeyFactory.k
        public AsymmetricKeyParameter a(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) {
            try {
                byte[] octets = ((ASN1OctetString) ASN1Primitive.fromByteArray(subjectPublicKeyInfo.getPublicKeyData().getBytes())).getOctets();
                byte[] bArr = new byte[65];
                bArr[0] = 4;
                for (int i = 1; i <= 32; i++) {
                    bArr[i] = octets[32 - i];
                    bArr[i + 32] = octets[64 - i];
                }
                boolean z = subjectPublicKeyInfo.getAlgorithm().getParameters() instanceof ASN1ObjectIdentifier;
                ASN1Encodable parameters = subjectPublicKeyInfo.getAlgorithm().getParameters();
                ECDomainParameters byOID = ECGOST3410NamedCurves.getByOID(z ? ASN1ObjectIdentifier.getInstance(parameters) : GOST3410PublicKeyAlgParameters.getInstance(parameters).getPublicKeyParamSet());
                return new ECPublicKeyParameters(byOID.getCurve().decodePoint(bArr), byOID);
            } catch (IOException unused) {
                throw new IllegalArgumentException("error recovering public key");
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class i extends k {
        public i() {
            super();
        }

        @Override // org.bouncycastle.crypto.util.PublicKeyFactory.k
        public AsymmetricKeyParameter a(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) {
            ASN1ObjectIdentifier algorithm = subjectPublicKeyInfo.getAlgorithm().getAlgorithm();
            try {
                byte[] octets = ((ASN1OctetString) ASN1Primitive.fromByteArray(subjectPublicKeyInfo.getPublicKeyData().getBytes())).getOctets();
                int i = algorithm.equals(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512) ? 64 : 32;
                int i2 = i * 2;
                byte[] bArr = new byte[i2 + 1];
                bArr[0] = 4;
                for (int i3 = 1; i3 <= i; i3++) {
                    bArr[i3] = octets[i - i3];
                    bArr[i3 + i] = octets[i2 - i3];
                }
                ECDomainParameters byOID = ECGOST3410NamedCurves.getByOID(GOST3410PublicKeyAlgParameters.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters()).getPublicKeyParamSet());
                return new ECPublicKeyParameters(byOID.getCurve().decodePoint(bArr), byOID);
            } catch (IOException unused) {
                throw new IllegalArgumentException("error recovering public key");
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class j extends k {
        public j() {
            super();
        }

        @Override // org.bouncycastle.crypto.util.PublicKeyFactory.k
        public AsymmetricKeyParameter a(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
            RSAPublicKey rSAPublicKey = RSAPublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
            return new RSAKeyParameters(false, rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent());
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class k {
        public k() {
        }

        public abstract AsymmetricKeyParameter a(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException;
    }

    static {
        HashMap hashMap = new HashMap();
        f14882a = hashMap;
        hashMap.put(PKCSObjectIdentifiers.rsaEncryption, new j());
        f14882a.put(X509ObjectIdentifiers.id_ea_rsa, new j());
        f14882a.put(X9ObjectIdentifiers.dhpublicnumber, new c());
        f14882a.put(PKCSObjectIdentifiers.dhKeyAgreement, new b());
        f14882a.put(X9ObjectIdentifiers.id_dsa, new d());
        f14882a.put(OIWObjectIdentifiers.dsaWithSHA1, new d());
        f14882a.put(OIWObjectIdentifiers.elGamalAlgorithm, new g());
        f14882a.put(X9ObjectIdentifiers.id_ecPublicKey, new f());
        f14882a.put(CryptoProObjectIdentifiers.gostR3410_2001, new h());
        f14882a.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256, new i());
        f14882a.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512, new i());
        f14882a.put(UAObjectIdentifiers.dstu4145be, new e());
        f14882a.put(UAObjectIdentifiers.dstu4145le, new e());
    }

    public static AsymmetricKeyParameter createKey(InputStream inputStream) throws IOException {
        return createKey(SubjectPublicKeyInfo.getInstance(new ASN1InputStream(inputStream).readObject()));
    }

    public static AsymmetricKeyParameter createKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        return createKey(subjectPublicKeyInfo, null);
    }

    public static AsymmetricKeyParameter createKey(SubjectPublicKeyInfo subjectPublicKeyInfo, Object obj) throws IOException {
        AlgorithmIdentifier algorithm = subjectPublicKeyInfo.getAlgorithm();
        k kVar = (k) f14882a.get(algorithm.getAlgorithm());
        if (kVar != null) {
            return kVar.a(subjectPublicKeyInfo, obj);
        }
        throw new IOException("algorithm identifier in key not recognised: " + algorithm.getAlgorithm());
    }

    public static AsymmetricKeyParameter createKey(byte[] bArr) throws IOException {
        return createKey(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(bArr)));
    }
}
