package org.bouncycastle.operator;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.bsi.BSIObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.pqc.jcajce.spec.SPHINCS256KeyGenParameterSpec;
/* loaded from: classes13.dex */
public class DefaultDigestAlgorithmIdentifierFinder implements DigestAlgorithmIdentifierFinder {

    /* renamed from: a  reason: collision with root package name */
    public static Map f15223a = new HashMap();
    public static Map b = new HashMap();

    static {
        Map map = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = OIWObjectIdentifiers.md4WithRSAEncryption;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = PKCSObjectIdentifiers.md4;
        map.put(aSN1ObjectIdentifier, aSN1ObjectIdentifier2);
        f15223a.put(OIWObjectIdentifiers.md4WithRSA, aSN1ObjectIdentifier2);
        Map map2 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = OIWObjectIdentifiers.sha1WithRSA;
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = OIWObjectIdentifiers.idSHA1;
        map2.put(aSN1ObjectIdentifier3, aSN1ObjectIdentifier4);
        Map map3 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = PKCSObjectIdentifiers.sha224WithRSAEncryption;
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = NISTObjectIdentifiers.id_sha224;
        map3.put(aSN1ObjectIdentifier5, aSN1ObjectIdentifier6);
        Map map4 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = PKCSObjectIdentifiers.sha256WithRSAEncryption;
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = NISTObjectIdentifiers.id_sha256;
        map4.put(aSN1ObjectIdentifier7, aSN1ObjectIdentifier8);
        Map map5 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = PKCSObjectIdentifiers.sha384WithRSAEncryption;
        ASN1ObjectIdentifier aSN1ObjectIdentifier10 = NISTObjectIdentifiers.id_sha384;
        map5.put(aSN1ObjectIdentifier9, aSN1ObjectIdentifier10);
        Map map6 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier11 = PKCSObjectIdentifiers.sha512WithRSAEncryption;
        ASN1ObjectIdentifier aSN1ObjectIdentifier12 = NISTObjectIdentifiers.id_sha512;
        map6.put(aSN1ObjectIdentifier11, aSN1ObjectIdentifier12);
        Map map7 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier13 = PKCSObjectIdentifiers.md2WithRSAEncryption;
        ASN1ObjectIdentifier aSN1ObjectIdentifier14 = PKCSObjectIdentifiers.md2;
        map7.put(aSN1ObjectIdentifier13, aSN1ObjectIdentifier14);
        f15223a.put(PKCSObjectIdentifiers.md4WithRSAEncryption, aSN1ObjectIdentifier2);
        Map map8 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier15 = PKCSObjectIdentifiers.md5WithRSAEncryption;
        ASN1ObjectIdentifier aSN1ObjectIdentifier16 = PKCSObjectIdentifiers.md5;
        map8.put(aSN1ObjectIdentifier15, aSN1ObjectIdentifier16);
        f15223a.put(PKCSObjectIdentifiers.sha1WithRSAEncryption, aSN1ObjectIdentifier4);
        f15223a.put(X9ObjectIdentifiers.ecdsa_with_SHA1, aSN1ObjectIdentifier4);
        f15223a.put(X9ObjectIdentifiers.ecdsa_with_SHA224, aSN1ObjectIdentifier6);
        f15223a.put(X9ObjectIdentifiers.ecdsa_with_SHA256, aSN1ObjectIdentifier8);
        f15223a.put(X9ObjectIdentifiers.ecdsa_with_SHA384, aSN1ObjectIdentifier10);
        f15223a.put(X9ObjectIdentifiers.ecdsa_with_SHA512, aSN1ObjectIdentifier12);
        f15223a.put(X9ObjectIdentifiers.id_dsa_with_sha1, aSN1ObjectIdentifier4);
        f15223a.put(BSIObjectIdentifiers.ecdsa_plain_SHA1, aSN1ObjectIdentifier4);
        f15223a.put(BSIObjectIdentifiers.ecdsa_plain_SHA224, aSN1ObjectIdentifier6);
        f15223a.put(BSIObjectIdentifiers.ecdsa_plain_SHA256, aSN1ObjectIdentifier8);
        f15223a.put(BSIObjectIdentifiers.ecdsa_plain_SHA384, aSN1ObjectIdentifier10);
        f15223a.put(BSIObjectIdentifiers.ecdsa_plain_SHA512, aSN1ObjectIdentifier12);
        Map map9 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier17 = BSIObjectIdentifiers.ecdsa_plain_RIPEMD160;
        ASN1ObjectIdentifier aSN1ObjectIdentifier18 = TeleTrusTObjectIdentifiers.ripemd160;
        map9.put(aSN1ObjectIdentifier17, aSN1ObjectIdentifier18);
        f15223a.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_1, aSN1ObjectIdentifier4);
        f15223a.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_224, aSN1ObjectIdentifier6);
        f15223a.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_256, aSN1ObjectIdentifier8);
        f15223a.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_384, aSN1ObjectIdentifier10);
        f15223a.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_512, aSN1ObjectIdentifier12);
        f15223a.put(NISTObjectIdentifiers.dsa_with_sha224, aSN1ObjectIdentifier6);
        f15223a.put(NISTObjectIdentifiers.dsa_with_sha256, aSN1ObjectIdentifier8);
        f15223a.put(NISTObjectIdentifiers.dsa_with_sha384, aSN1ObjectIdentifier10);
        f15223a.put(NISTObjectIdentifiers.dsa_with_sha512, aSN1ObjectIdentifier12);
        Map map10 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier19 = NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_224;
        ASN1ObjectIdentifier aSN1ObjectIdentifier20 = NISTObjectIdentifiers.id_sha3_224;
        map10.put(aSN1ObjectIdentifier19, aSN1ObjectIdentifier20);
        Map map11 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier21 = NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_256;
        ASN1ObjectIdentifier aSN1ObjectIdentifier22 = NISTObjectIdentifiers.id_sha3_256;
        map11.put(aSN1ObjectIdentifier21, aSN1ObjectIdentifier22);
        Map map12 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier23 = NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_384;
        ASN1ObjectIdentifier aSN1ObjectIdentifier24 = NISTObjectIdentifiers.id_sha3_384;
        map12.put(aSN1ObjectIdentifier23, aSN1ObjectIdentifier24);
        Map map13 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier25 = NISTObjectIdentifiers.id_rsassa_pkcs1_v1_5_with_sha3_512;
        ASN1ObjectIdentifier aSN1ObjectIdentifier26 = NISTObjectIdentifiers.id_sha3_512;
        map13.put(aSN1ObjectIdentifier25, aSN1ObjectIdentifier26);
        f15223a.put(NISTObjectIdentifiers.id_dsa_with_sha3_224, aSN1ObjectIdentifier20);
        f15223a.put(NISTObjectIdentifiers.id_dsa_with_sha3_256, aSN1ObjectIdentifier22);
        f15223a.put(NISTObjectIdentifiers.id_dsa_with_sha3_384, aSN1ObjectIdentifier24);
        f15223a.put(NISTObjectIdentifiers.id_dsa_with_sha3_512, aSN1ObjectIdentifier26);
        f15223a.put(NISTObjectIdentifiers.id_ecdsa_with_sha3_224, aSN1ObjectIdentifier20);
        f15223a.put(NISTObjectIdentifiers.id_ecdsa_with_sha3_256, aSN1ObjectIdentifier22);
        f15223a.put(NISTObjectIdentifiers.id_ecdsa_with_sha3_384, aSN1ObjectIdentifier24);
        f15223a.put(NISTObjectIdentifiers.id_ecdsa_with_sha3_512, aSN1ObjectIdentifier26);
        Map map14 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier27 = TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128;
        ASN1ObjectIdentifier aSN1ObjectIdentifier28 = TeleTrusTObjectIdentifiers.ripemd128;
        map14.put(aSN1ObjectIdentifier27, aSN1ObjectIdentifier28);
        f15223a.put(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160, aSN1ObjectIdentifier18);
        Map map15 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier29 = TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256;
        ASN1ObjectIdentifier aSN1ObjectIdentifier30 = TeleTrusTObjectIdentifiers.ripemd256;
        map15.put(aSN1ObjectIdentifier29, aSN1ObjectIdentifier30);
        Map map16 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier31 = CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94;
        ASN1ObjectIdentifier aSN1ObjectIdentifier32 = CryptoProObjectIdentifiers.gostR3411;
        map16.put(aSN1ObjectIdentifier31, aSN1ObjectIdentifier32);
        f15223a.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, aSN1ObjectIdentifier32);
        Map map17 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier33 = RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_256;
        ASN1ObjectIdentifier aSN1ObjectIdentifier34 = RosstandartObjectIdentifiers.id_tc26_gost_3411_12_256;
        map17.put(aSN1ObjectIdentifier33, aSN1ObjectIdentifier34);
        Map map18 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier35 = RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_512;
        ASN1ObjectIdentifier aSN1ObjectIdentifier36 = RosstandartObjectIdentifiers.id_tc26_gost_3411_12_512;
        map18.put(aSN1ObjectIdentifier35, aSN1ObjectIdentifier36);
        f15223a.put(BCObjectIdentifiers.sphincs256_with_SHA3_512, aSN1ObjectIdentifier26);
        f15223a.put(BCObjectIdentifiers.sphincs256_with_SHA512, aSN1ObjectIdentifier12);
        Map map19 = f15223a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier37 = GMObjectIdentifiers.sm2sign_with_sm3;
        ASN1ObjectIdentifier aSN1ObjectIdentifier38 = GMObjectIdentifiers.sm3;
        map19.put(aSN1ObjectIdentifier37, aSN1ObjectIdentifier38);
        b.put("SHA-1", aSN1ObjectIdentifier4);
        b.put("SHA-224", aSN1ObjectIdentifier6);
        b.put("SHA-256", aSN1ObjectIdentifier8);
        b.put("SHA-384", aSN1ObjectIdentifier10);
        b.put("SHA-512", aSN1ObjectIdentifier12);
        Map map20 = b;
        ASN1ObjectIdentifier aSN1ObjectIdentifier39 = NISTObjectIdentifiers.id_sha512_224;
        map20.put("SHA-512-224", aSN1ObjectIdentifier39);
        Map map21 = b;
        ASN1ObjectIdentifier aSN1ObjectIdentifier40 = NISTObjectIdentifiers.id_sha512_256;
        map21.put("SHA-512-256", aSN1ObjectIdentifier40);
        b.put("SHA1", aSN1ObjectIdentifier4);
        b.put("SHA224", aSN1ObjectIdentifier6);
        b.put("SHA256", aSN1ObjectIdentifier8);
        b.put("SHA384", aSN1ObjectIdentifier10);
        b.put("SHA512", aSN1ObjectIdentifier12);
        b.put("SHA512-224", aSN1ObjectIdentifier39);
        b.put(SPHINCS256KeyGenParameterSpec.SHA512_256, aSN1ObjectIdentifier40);
        b.put(MessageDigestAlgorithms.SHA3_224, aSN1ObjectIdentifier20);
        b.put("SHA3-256", aSN1ObjectIdentifier22);
        b.put(MessageDigestAlgorithms.SHA3_384, aSN1ObjectIdentifier24);
        b.put(MessageDigestAlgorithms.SHA3_512, aSN1ObjectIdentifier26);
        b.put("SHAKE-128", NISTObjectIdentifiers.id_shake128);
        b.put("SHAKE-256", NISTObjectIdentifiers.id_shake256);
        b.put("GOST3411", aSN1ObjectIdentifier32);
        b.put("GOST3411-2012-256", aSN1ObjectIdentifier34);
        b.put("GOST3411-2012-512", aSN1ObjectIdentifier36);
        b.put(MessageDigestAlgorithms.MD2, aSN1ObjectIdentifier14);
        b.put("MD4", aSN1ObjectIdentifier2);
        b.put(MessageDigestAlgorithms.MD5, aSN1ObjectIdentifier16);
        b.put("RIPEMD128", aSN1ObjectIdentifier28);
        b.put("RIPEMD160", aSN1ObjectIdentifier18);
        b.put("RIPEMD256", aSN1ObjectIdentifier30);
        b.put("SM3", aSN1ObjectIdentifier38);
    }

    @Override // org.bouncycastle.operator.DigestAlgorithmIdentifierFinder
    public AlgorithmIdentifier find(String str) {
        return new AlgorithmIdentifier((ASN1ObjectIdentifier) b.get(str), DERNull.INSTANCE);
    }

    @Override // org.bouncycastle.operator.DigestAlgorithmIdentifierFinder
    public AlgorithmIdentifier find(AlgorithmIdentifier algorithmIdentifier) {
        return algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS) ? RSASSAPSSparams.getInstance(algorithmIdentifier.getParameters()).getHashAlgorithm() : new AlgorithmIdentifier((ASN1ObjectIdentifier) f15223a.get(algorithmIdentifier.getAlgorithm()), DERNull.INSTANCE);
    }
}
