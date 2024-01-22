package org.bouncycastle.operator.jcajce;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PSSParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.bsi.BSIObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.jcajce.util.AlgorithmParametersUtils;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.MessageDigestUtils;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Integers;
import org.jose4j.keys.AesKey;
/* loaded from: classes13.dex */
public class a {
    public static final Map b;
    public static final Map c;
    public static final Map d;
    public static final Map e;
    public static final Map f;

    /* renamed from: a  reason: collision with root package name */
    public JcaJceHelper f15252a;

    /* renamed from: org.bouncycastle.operator.jcajce.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0911a extends CertificateException {
        private Throwable cause;

        public C0911a(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        HashMap hashMap2 = new HashMap();
        c = hashMap2;
        HashMap hashMap3 = new HashMap();
        d = hashMap3;
        HashMap hashMap4 = new HashMap();
        e = hashMap4;
        HashMap hashMap5 = new HashMap();
        f = hashMap5;
        hashMap.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"), "SHA1WITHRSA");
        hashMap.put(PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224WITHRSA");
        hashMap.put(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256WITHRSA");
        hashMap.put(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384WITHRSA");
        hashMap.put(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512WITHRSA");
        hashMap.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94, "GOST3411WITHGOST3410");
        hashMap.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, "GOST3411WITHECGOST3410");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_256, "GOST3411-2012-256WITHECGOST3410-2012-256");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_512, "GOST3411-2012-512WITHECGOST3410-2012-512");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_SHA1, "SHA1WITHPLAIN-ECDSA");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_SHA224, "SHA224WITHPLAIN-ECDSA");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_SHA256, "SHA256WITHPLAIN-ECDSA");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_SHA384, "SHA384WITHPLAIN-ECDSA");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_SHA512, "SHA512WITHPLAIN-ECDSA");
        hashMap.put(BSIObjectIdentifiers.ecdsa_plain_RIPEMD160, "RIPEMD160WITHPLAIN-ECDSA");
        hashMap.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_1, "SHA1WITHCVC-ECDSA");
        hashMap.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_224, "SHA224WITHCVC-ECDSA");
        hashMap.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_256, "SHA256WITHCVC-ECDSA");
        hashMap.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_384, "SHA384WITHCVC-ECDSA");
        hashMap.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_512, "SHA512WITHCVC-ECDSA");
        hashMap.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"), "MD5WITHRSA");
        hashMap.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.2"), "MD2WITHRSA");
        hashMap.put(new ASN1ObjectIdentifier("1.2.840.10040.4.3"), "SHA1WITHDSA");
        hashMap.put(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1WITHECDSA");
        hashMap.put(X9ObjectIdentifiers.ecdsa_with_SHA224, "SHA224WITHECDSA");
        hashMap.put(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256WITHECDSA");
        hashMap.put(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384WITHECDSA");
        hashMap.put(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512WITHECDSA");
        hashMap.put(OIWObjectIdentifiers.sha1WithRSA, "SHA1WITHRSA");
        hashMap.put(OIWObjectIdentifiers.dsaWithSHA1, "SHA1WITHDSA");
        hashMap.put(NISTObjectIdentifiers.dsa_with_sha224, "SHA224WITHDSA");
        hashMap.put(NISTObjectIdentifiers.dsa_with_sha256, "SHA256WITHDSA");
        hashMap.put(OIWObjectIdentifiers.idSHA1, "SHA1");
        hashMap.put(NISTObjectIdentifiers.id_sha224, "SHA224");
        hashMap.put(NISTObjectIdentifiers.id_sha256, "SHA256");
        hashMap.put(NISTObjectIdentifiers.id_sha384, "SHA384");
        hashMap.put(NISTObjectIdentifiers.id_sha512, "SHA512");
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd128, "RIPEMD128");
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd160, "RIPEMD160");
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd256, "RIPEMD256");
        hashMap2.put(PKCSObjectIdentifiers.rsaEncryption, "RSA/ECB/PKCS1Padding");
        hashMap2.put(CryptoProObjectIdentifiers.gostR3410_2001, "ECGOST3410");
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.id_alg_CMS3DESwrap;
        hashMap3.put(aSN1ObjectIdentifier, "DESEDEWrap");
        hashMap3.put(PKCSObjectIdentifiers.id_alg_CMSRC2wrap, "RC2Wrap");
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_aes128_wrap;
        hashMap3.put(aSN1ObjectIdentifier2, "AESWrap");
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = NISTObjectIdentifiers.id_aes192_wrap;
        hashMap3.put(aSN1ObjectIdentifier3, "AESWrap");
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = NISTObjectIdentifiers.id_aes256_wrap;
        hashMap3.put(aSN1ObjectIdentifier4, "AESWrap");
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = NTTObjectIdentifiers.id_camellia128_wrap;
        hashMap3.put(aSN1ObjectIdentifier5, "CamelliaWrap");
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = NTTObjectIdentifiers.id_camellia192_wrap;
        hashMap3.put(aSN1ObjectIdentifier6, "CamelliaWrap");
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = NTTObjectIdentifiers.id_camellia256_wrap;
        hashMap3.put(aSN1ObjectIdentifier7, "CamelliaWrap");
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap;
        hashMap3.put(aSN1ObjectIdentifier8, "SEEDWrap");
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = PKCSObjectIdentifiers.des_EDE3_CBC;
        hashMap3.put(aSN1ObjectIdentifier9, "DESede");
        hashMap5.put(aSN1ObjectIdentifier, Integers.valueOf(192));
        hashMap5.put(aSN1ObjectIdentifier2, Integers.valueOf(128));
        hashMap5.put(aSN1ObjectIdentifier3, Integers.valueOf(192));
        hashMap5.put(aSN1ObjectIdentifier4, Integers.valueOf(256));
        hashMap5.put(aSN1ObjectIdentifier5, Integers.valueOf(128));
        hashMap5.put(aSN1ObjectIdentifier6, Integers.valueOf(192));
        hashMap5.put(aSN1ObjectIdentifier7, Integers.valueOf(256));
        hashMap5.put(aSN1ObjectIdentifier8, Integers.valueOf(128));
        hashMap5.put(aSN1ObjectIdentifier9, Integers.valueOf(192));
        hashMap4.put(NISTObjectIdentifiers.aes, AesKey.ALGORITHM);
        hashMap4.put(NISTObjectIdentifiers.id_aes128_CBC, AesKey.ALGORITHM);
        hashMap4.put(NISTObjectIdentifiers.id_aes192_CBC, AesKey.ALGORITHM);
        hashMap4.put(NISTObjectIdentifiers.id_aes256_CBC, AesKey.ALGORITHM);
        hashMap4.put(aSN1ObjectIdentifier9, "DESede");
        hashMap4.put(PKCSObjectIdentifiers.RC2_CBC, "RC2");
    }

    public a(JcaJceHelper jcaJceHelper) {
        this.f15252a = jcaJceHelper;
    }

    public static String i(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String digestName = MessageDigestUtils.getDigestName(aSN1ObjectIdentifier);
        int indexOf = digestName.indexOf(45);
        if (indexOf <= 0 || digestName.startsWith("SHA3")) {
            return MessageDigestUtils.getDigestName(aSN1ObjectIdentifier);
        }
        return digestName.substring(0, indexOf) + digestName.substring(indexOf + 1);
    }

    public static String k(AlgorithmIdentifier algorithmIdentifier) {
        ASN1Encodable parameters = algorithmIdentifier.getParameters();
        if (parameters == null || DERNull.INSTANCE.equals(parameters) || !algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS)) {
            Map map = b;
            boolean containsKey = map.containsKey(algorithmIdentifier.getAlgorithm());
            ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
            return containsKey ? (String) map.get(algorithm) : algorithm.getId();
        }
        RSASSAPSSparams rSASSAPSSparams = RSASSAPSSparams.getInstance(parameters);
        return i(rSASSAPSSparams.getHashAlgorithm().getAlgorithm()) + "WITHRSAANDMGF1";
    }

    public X509Certificate a(X509CertificateHolder x509CertificateHolder) throws CertificateException {
        try {
            return (X509Certificate) this.f15252a.createCertificateFactory("X.509").generateCertificate(new ByteArrayInputStream(x509CertificateHolder.getEncoded()));
        } catch (IOException e2) {
            throw new C0911a("cannot get encoded form of certificate: " + e2.getMessage(), e2);
        } catch (NoSuchProviderException e3) {
            throw new C0911a("cannot find factory provider: " + e3.getMessage(), e3);
        }
    }

    public PublicKey b(SubjectPublicKeyInfo subjectPublicKeyInfo) throws OperatorCreationException {
        try {
            return this.f15252a.createKeyFactory(subjectPublicKeyInfo.getAlgorithm().getAlgorithm().getId()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
        } catch (IOException e2) {
            throw new OperatorCreationException("cannot get encoded form of key: " + e2.getMessage(), e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new OperatorCreationException("cannot create key factory: " + e3.getMessage(), e3);
        } catch (NoSuchProviderException e4) {
            throw new OperatorCreationException("cannot find factory provider: " + e4.getMessage(), e4);
        } catch (InvalidKeySpecException e5) {
            throw new OperatorCreationException("cannot create key factory: " + e5.getMessage(), e5);
        }
    }

    public AlgorithmParameters c(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
        if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.rsaEncryption)) {
            return null;
        }
        try {
            AlgorithmParameters createAlgorithmParameters = this.f15252a.createAlgorithmParameters(algorithmIdentifier.getAlgorithm().getId());
            try {
                createAlgorithmParameters.init(algorithmIdentifier.getParameters().toASN1Primitive().getEncoded());
                return createAlgorithmParameters;
            } catch (IOException e2) {
                throw new OperatorCreationException("cannot initialise algorithm parameters: " + e2.getMessage(), e2);
            }
        } catch (NoSuchAlgorithmException unused) {
            return null;
        } catch (NoSuchProviderException e3) {
            throw new OperatorCreationException("cannot create algorithm parameters: " + e3.getMessage(), e3);
        }
    }

    public Cipher d(ASN1ObjectIdentifier aSN1ObjectIdentifier, Map map) throws OperatorCreationException {
        try {
            String str = map.isEmpty() ? null : (String) map.get(aSN1ObjectIdentifier);
            if (str == null) {
                str = (String) c.get(aSN1ObjectIdentifier);
            }
            if (str != null) {
                try {
                    return this.f15252a.createCipher(str);
                } catch (NoSuchAlgorithmException unused) {
                    if (str.equals("RSA/ECB/PKCS1Padding")) {
                        try {
                            return this.f15252a.createCipher("RSA/NONE/PKCS1Padding");
                        } catch (NoSuchAlgorithmException unused2) {
                        }
                    }
                }
            }
            return this.f15252a.createCipher(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new OperatorCreationException("cannot create cipher: " + e2.getMessage(), e2);
        }
    }

    public MessageDigest e(AlgorithmIdentifier algorithmIdentifier) throws GeneralSecurityException {
        try {
            return this.f15252a.createDigest(MessageDigestUtils.getDigestName(algorithmIdentifier.getAlgorithm()));
        } catch (NoSuchAlgorithmException e2) {
            Map map = b;
            if (map.get(algorithmIdentifier.getAlgorithm()) != null) {
                return this.f15252a.createDigest((String) map.get(algorithmIdentifier.getAlgorithm()));
            }
            throw e2;
        }
    }

    public Signature f(AlgorithmIdentifier algorithmIdentifier) {
        try {
            String k = k(algorithmIdentifier);
            String str = "NONE" + k.substring(k.indexOf("WITH"));
            Signature createSignature = this.f15252a.createSignature(str);
            if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS)) {
                AlgorithmParameters createAlgorithmParameters = this.f15252a.createAlgorithmParameters(str);
                AlgorithmParametersUtils.loadParameters(createAlgorithmParameters, algorithmIdentifier.getParameters());
                createSignature.setParameter((PSSParameterSpec) createAlgorithmParameters.getParameterSpec(PSSParameterSpec.class));
            }
            return createSignature;
        } catch (Exception unused) {
            return null;
        }
    }

    public Signature g(AlgorithmIdentifier algorithmIdentifier) throws GeneralSecurityException {
        Signature createSignature;
        try {
            createSignature = this.f15252a.createSignature(k(algorithmIdentifier));
        } catch (NoSuchAlgorithmException e2) {
            Map map = b;
            if (map.get(algorithmIdentifier.getAlgorithm()) == null) {
                throw e2;
            }
            createSignature = this.f15252a.createSignature((String) map.get(algorithmIdentifier.getAlgorithm()));
        }
        if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS)) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(algorithmIdentifier.getParameters());
            if (m(aSN1Sequence)) {
                try {
                    AlgorithmParameters createAlgorithmParameters = this.f15252a.createAlgorithmParameters("PSS");
                    createAlgorithmParameters.init(aSN1Sequence.getEncoded());
                    createSignature.setParameter(createAlgorithmParameters.getParameterSpec(PSSParameterSpec.class));
                } catch (IOException e3) {
                    throw new GeneralSecurityException("unable to process PSS parameters: " + e3.getMessage());
                }
            }
        }
        return createSignature;
    }

    public Cipher h(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws OperatorCreationException {
        try {
            String str = (String) d.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.f15252a.createCipher(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.f15252a.createCipher(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new OperatorCreationException("cannot create cipher: " + e2.getMessage(), e2);
        }
    }

    public String j(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) e.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }

    public String l(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) d.get(aSN1ObjectIdentifier);
    }

    public final boolean m(ASN1Sequence aSN1Sequence) throws GeneralSecurityException {
        if (aSN1Sequence == null || aSN1Sequence.size() == 0) {
            return false;
        }
        RSASSAPSSparams rSASSAPSSparams = RSASSAPSSparams.getInstance(aSN1Sequence);
        if (rSASSAPSSparams.getMaskGenAlgorithm().getAlgorithm().equals(PKCSObjectIdentifiers.id_mgf1) && rSASSAPSSparams.getHashAlgorithm().equals(AlgorithmIdentifier.getInstance(rSASSAPSSparams.getMaskGenAlgorithm().getParameters()))) {
            return rSASSAPSSparams.getSaltLength().intValue() != e(rSASSAPSSparams.getHashAlgorithm()).getDigestLength();
        }
        return true;
    }
}
