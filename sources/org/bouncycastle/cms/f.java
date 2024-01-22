package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.OtherRevocationInfoFormat;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AttributeCertificate;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Store;
/* loaded from: classes12.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public static final f f14565a = new f();
    public static final Map b;
    public static final Map c;
    public static final Map d;

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        HashMap hashMap2 = new HashMap();
        c = hashMap2;
        HashMap hashMap3 = new HashMap();
        d = hashMap3;
        a(NISTObjectIdentifiers.dsa_with_sha224, "SHA224", "DSA");
        a(NISTObjectIdentifiers.dsa_with_sha256, "SHA256", "DSA");
        a(NISTObjectIdentifiers.dsa_with_sha384, "SHA384", "DSA");
        a(NISTObjectIdentifiers.dsa_with_sha512, "SHA512", "DSA");
        a(OIWObjectIdentifiers.dsaWithSHA1, "SHA1", "DSA");
        a(OIWObjectIdentifiers.md4WithRSA, "MD4", "RSA");
        a(OIWObjectIdentifiers.md4WithRSAEncryption, "MD4", "RSA");
        a(OIWObjectIdentifiers.md5WithRSA, MessageDigestAlgorithms.MD5, "RSA");
        a(OIWObjectIdentifiers.sha1WithRSA, "SHA1", "RSA");
        a(PKCSObjectIdentifiers.md2WithRSAEncryption, MessageDigestAlgorithms.MD2, "RSA");
        a(PKCSObjectIdentifiers.md4WithRSAEncryption, "MD4", "RSA");
        a(PKCSObjectIdentifiers.md5WithRSAEncryption, MessageDigestAlgorithms.MD5, "RSA");
        a(PKCSObjectIdentifiers.sha1WithRSAEncryption, "SHA1", "RSA");
        a(PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224", "RSA");
        a(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256", "RSA");
        a(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384", "RSA");
        a(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512", "RSA");
        a(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1", "ECDSA");
        a(X9ObjectIdentifiers.ecdsa_with_SHA224, "SHA224", "ECDSA");
        a(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256", "ECDSA");
        a(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384", "ECDSA");
        a(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512", "ECDSA");
        a(X9ObjectIdentifiers.id_dsa_with_sha1, "SHA1", "DSA");
        a(EACObjectIdentifiers.id_TA_ECDSA_SHA_1, "SHA1", "ECDSA");
        a(EACObjectIdentifiers.id_TA_ECDSA_SHA_224, "SHA224", "ECDSA");
        a(EACObjectIdentifiers.id_TA_ECDSA_SHA_256, "SHA256", "ECDSA");
        a(EACObjectIdentifiers.id_TA_ECDSA_SHA_384, "SHA384", "ECDSA");
        a(EACObjectIdentifiers.id_TA_ECDSA_SHA_512, "SHA512", "ECDSA");
        a(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1, "SHA1", "RSA");
        a(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256, "SHA256", "RSA");
        a(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_1, "SHA1", "RSAandMGF1");
        a(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_256, "SHA256", "RSAandMGF1");
        hashMap.put(X9ObjectIdentifiers.id_dsa.getId(), "DSA");
        hashMap.put(PKCSObjectIdentifiers.rsaEncryption.getId(), "RSA");
        hashMap.put(TeleTrusTObjectIdentifiers.teleTrusTRSAsignatureAlgorithm, "RSA");
        hashMap.put(X509ObjectIdentifiers.id_ea_rsa.getId(), "RSA");
        hashMap.put(CMSSignedGenerator.ENCRYPTION_RSA_PSS, "RSAandMGF1");
        hashMap.put(CryptoProObjectIdentifiers.gostR3410_94.getId(), "GOST3410");
        hashMap.put(CryptoProObjectIdentifiers.gostR3410_2001.getId(), "ECGOST3410");
        hashMap.put("1.3.6.1.4.1.5849.1.6.2", "ECGOST3410");
        hashMap.put("1.3.6.1.4.1.5849.1.1.5", "GOST3410");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256, "ECGOST3410-2012-256");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512, "ECGOST3410-2012-512");
        hashMap.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001.getId(), "ECGOST3410");
        hashMap.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94.getId(), "GOST3410");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_256, "ECGOST3410-2012-256");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_512, "ECGOST3410-2012-512");
        hashMap2.put(PKCSObjectIdentifiers.md2.getId(), MessageDigestAlgorithms.MD2);
        hashMap2.put(PKCSObjectIdentifiers.md4.getId(), "MD4");
        hashMap2.put(PKCSObjectIdentifiers.md5.getId(), MessageDigestAlgorithms.MD5);
        hashMap2.put(OIWObjectIdentifiers.idSHA1.getId(), "SHA1");
        hashMap2.put(NISTObjectIdentifiers.id_sha224.getId(), "SHA224");
        hashMap2.put(NISTObjectIdentifiers.id_sha256.getId(), "SHA256");
        hashMap2.put(NISTObjectIdentifiers.id_sha384.getId(), "SHA384");
        hashMap2.put(NISTObjectIdentifiers.id_sha512.getId(), "SHA512");
        hashMap2.put(TeleTrusTObjectIdentifiers.ripemd128.getId(), "RIPEMD128");
        hashMap2.put(TeleTrusTObjectIdentifiers.ripemd160.getId(), "RIPEMD160");
        hashMap2.put(TeleTrusTObjectIdentifiers.ripemd256.getId(), "RIPEMD256");
        hashMap2.put(CryptoProObjectIdentifiers.gostR3411.getId(), "GOST3411");
        hashMap2.put("1.3.6.1.4.1.5849.1.2.1", "GOST3411");
        hashMap2.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_256, "GOST3411-2012-256");
        hashMap2.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_512, "GOST3411-2012-512");
        hashMap3.put("SHA1", new String[]{"SHA-1"});
        hashMap3.put("SHA224", new String[]{"SHA-224"});
        hashMap3.put("SHA256", new String[]{"SHA-256"});
        hashMap3.put("SHA384", new String[]{"SHA-384"});
        hashMap3.put("SHA512", new String[]{"SHA-512"});
    }

    public static void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str, String str2) {
        c.put(aSN1ObjectIdentifier.getId(), str);
        b.put(aSN1ObjectIdentifier.getId(), str2);
    }

    public AlgorithmIdentifier b(AlgorithmIdentifier algorithmIdentifier) {
        return algorithmIdentifier.getParameters() == null ? new AlgorithmIdentifier(algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE) : algorithmIdentifier;
    }

    public Store c(ASN1Set aSN1Set) {
        if (aSN1Set != null) {
            ArrayList arrayList = new ArrayList(aSN1Set.size());
            Enumeration objects = aSN1Set.getObjects();
            while (objects.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) objects.nextElement()).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1TaggedObject) {
                    arrayList.add(new X509AttributeCertificateHolder(AttributeCertificate.getInstance(((ASN1TaggedObject) aSN1Primitive).getObject())));
                }
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    public Store d(ASN1Set aSN1Set) {
        if (aSN1Set != null) {
            ArrayList arrayList = new ArrayList(aSN1Set.size());
            Enumeration objects = aSN1Set.getObjects();
            while (objects.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) objects.nextElement()).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Sequence) {
                    arrayList.add(new X509CRLHolder(CertificateList.getInstance(aSN1Primitive)));
                }
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    public Store e(ASN1Set aSN1Set) {
        if (aSN1Set != null) {
            ArrayList arrayList = new ArrayList(aSN1Set.size());
            Enumeration objects = aSN1Set.getObjects();
            while (objects.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) objects.nextElement()).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Sequence) {
                    arrayList.add(new X509CertificateHolder(Certificate.getInstance(aSN1Primitive)));
                }
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    public String f(String str) {
        String str2 = (String) b.get(str);
        return str2 != null ? str2 : str;
    }

    public Store g(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Set aSN1Set) {
        if (aSN1Set != null) {
            ArrayList arrayList = new ArrayList(aSN1Set.size());
            Enumeration objects = aSN1Set.getObjects();
            while (objects.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) objects.nextElement()).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1TaggedObject) {
                    ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Primitive);
                    if (aSN1TaggedObject.getTagNo() == 1) {
                        OtherRevocationInfoFormat otherRevocationInfoFormat = OtherRevocationInfoFormat.getInstance(aSN1TaggedObject, false);
                        if (aSN1ObjectIdentifier.equals(otherRevocationInfoFormat.getInfoFormat())) {
                            arrayList.add(otherRevocationInfoFormat.getInfo());
                        }
                    }
                }
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    public void h(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        c.put(aSN1ObjectIdentifier.getId(), str);
    }

    public void i(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        b.put(aSN1ObjectIdentifier.getId(), str);
    }
}
