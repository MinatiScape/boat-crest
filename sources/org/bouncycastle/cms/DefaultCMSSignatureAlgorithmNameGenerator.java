package org.bouncycastle.cms;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.bsi.BSIObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
/* loaded from: classes12.dex */
public class DefaultCMSSignatureAlgorithmNameGenerator implements CMSSignatureAlgorithmNameGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final Map f14533a;
    public final Map b;

    public DefaultCMSSignatureAlgorithmNameGenerator() {
        HashMap hashMap = new HashMap();
        this.f14533a = hashMap;
        HashMap hashMap2 = new HashMap();
        this.b = hashMap2;
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
        a(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128, "RIPEMD128", "RSA");
        a(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160, "RIPEMD160", "RSA");
        a(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256, "RIPEMD256", "RSA");
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
        a(BSIObjectIdentifiers.ecdsa_plain_SHA1, "SHA1", "PLAIN-ECDSA");
        a(BSIObjectIdentifiers.ecdsa_plain_SHA224, "SHA224", "PLAIN-ECDSA");
        a(BSIObjectIdentifiers.ecdsa_plain_SHA256, "SHA256", "PLAIN-ECDSA");
        a(BSIObjectIdentifiers.ecdsa_plain_SHA384, "SHA384", "PLAIN-ECDSA");
        a(BSIObjectIdentifiers.ecdsa_plain_SHA512, "SHA512", "PLAIN-ECDSA");
        a(BSIObjectIdentifiers.ecdsa_plain_RIPEMD160, "RIPEMD160", "PLAIN-ECDSA");
        hashMap.put(X9ObjectIdentifiers.id_dsa, "DSA");
        hashMap.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        hashMap.put(TeleTrusTObjectIdentifiers.teleTrusTRSAsignatureAlgorithm, "RSA");
        hashMap.put(X509ObjectIdentifiers.id_ea_rsa, "RSA");
        hashMap.put(PKCSObjectIdentifiers.id_RSASSA_PSS, "RSAandMGF1");
        hashMap.put(CryptoProObjectIdentifiers.gostR3410_94, "GOST3410");
        hashMap.put(CryptoProObjectIdentifiers.gostR3410_2001, "ECGOST3410");
        hashMap.put(new ASN1ObjectIdentifier("1.3.6.1.4.1.5849.1.6.2"), "ECGOST3410");
        hashMap.put(new ASN1ObjectIdentifier("1.3.6.1.4.1.5849.1.1.5"), "GOST3410");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256, "ECGOST3410-2012-256");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512, "ECGOST3410-2012-512");
        hashMap.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, "ECGOST3410");
        hashMap.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94, "GOST3410");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_256, "ECGOST3410-2012-256");
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_512, "ECGOST3410-2012-512");
        hashMap.put(GMObjectIdentifiers.sm2sign_with_sm3, "SM2");
        hashMap2.put(PKCSObjectIdentifiers.md2, MessageDigestAlgorithms.MD2);
        hashMap2.put(PKCSObjectIdentifiers.md4, "MD4");
        hashMap2.put(PKCSObjectIdentifiers.md5, MessageDigestAlgorithms.MD5);
        hashMap2.put(OIWObjectIdentifiers.idSHA1, "SHA1");
        hashMap2.put(NISTObjectIdentifiers.id_sha224, "SHA224");
        hashMap2.put(NISTObjectIdentifiers.id_sha256, "SHA256");
        hashMap2.put(NISTObjectIdentifiers.id_sha384, "SHA384");
        hashMap2.put(NISTObjectIdentifiers.id_sha512, "SHA512");
        hashMap2.put(TeleTrusTObjectIdentifiers.ripemd128, "RIPEMD128");
        hashMap2.put(TeleTrusTObjectIdentifiers.ripemd160, "RIPEMD160");
        hashMap2.put(TeleTrusTObjectIdentifiers.ripemd256, "RIPEMD256");
        hashMap2.put(CryptoProObjectIdentifiers.gostR3411, "GOST3411");
        hashMap2.put(new ASN1ObjectIdentifier("1.3.6.1.4.1.5849.1.2.1"), "GOST3411");
        hashMap2.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_256, "GOST3411-2012-256");
        hashMap2.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_512, "GOST3411-2012-512");
        hashMap2.put(GMObjectIdentifiers.sm3, "SM3");
    }

    public final void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str, String str2) {
        this.b.put(aSN1ObjectIdentifier, str);
        this.f14533a.put(aSN1ObjectIdentifier, str2);
    }

    public final String b(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) this.b.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }

    public final String c(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) this.f14533a.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }

    @Override // org.bouncycastle.cms.CMSSignatureAlgorithmNameGenerator
    public String getSignatureName(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        String b = b(algorithmIdentifier2.getAlgorithm());
        if (b.equals(algorithmIdentifier2.getAlgorithm().getId())) {
            return b(algorithmIdentifier.getAlgorithm()) + "with" + c(algorithmIdentifier2.getAlgorithm());
        }
        return b + "with" + c(algorithmIdentifier2.getAlgorithm());
    }

    public void setSigningDigestAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.b.put(aSN1ObjectIdentifier, str);
    }

    public void setSigningEncryptionAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.f14533a.put(aSN1ObjectIdentifier, str);
    }
}
