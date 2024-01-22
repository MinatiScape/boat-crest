package org.bouncycastle.jcajce.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.gnu.GNUObjectIdentifiers;
import org.bouncycastle.asn1.iso.ISOIECObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
/* loaded from: classes13.dex */
public class MessageDigestUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Map<ASN1ObjectIdentifier, String> f15081a;

    static {
        HashMap hashMap = new HashMap();
        f15081a = hashMap;
        hashMap.put(PKCSObjectIdentifiers.md2, MessageDigestAlgorithms.MD2);
        f15081a.put(PKCSObjectIdentifiers.md4, "MD4");
        f15081a.put(PKCSObjectIdentifiers.md5, MessageDigestAlgorithms.MD5);
        f15081a.put(OIWObjectIdentifiers.idSHA1, "SHA-1");
        f15081a.put(NISTObjectIdentifiers.id_sha224, "SHA-224");
        f15081a.put(NISTObjectIdentifiers.id_sha256, "SHA-256");
        f15081a.put(NISTObjectIdentifiers.id_sha384, "SHA-384");
        f15081a.put(NISTObjectIdentifiers.id_sha512, "SHA-512");
        f15081a.put(TeleTrusTObjectIdentifiers.ripemd128, "RIPEMD-128");
        f15081a.put(TeleTrusTObjectIdentifiers.ripemd160, "RIPEMD-160");
        f15081a.put(TeleTrusTObjectIdentifiers.ripemd256, "RIPEMD-128");
        f15081a.put(ISOIECObjectIdentifiers.ripemd128, "RIPEMD-128");
        f15081a.put(ISOIECObjectIdentifiers.ripemd160, "RIPEMD-160");
        f15081a.put(CryptoProObjectIdentifiers.gostR3411, "GOST3411");
        f15081a.put(GNUObjectIdentifiers.Tiger_192, "Tiger");
        f15081a.put(ISOIECObjectIdentifiers.whirlpool, "Whirlpool");
        f15081a.put(NISTObjectIdentifiers.id_sha3_224, MessageDigestAlgorithms.SHA3_224);
        f15081a.put(NISTObjectIdentifiers.id_sha3_256, "SHA3-256");
        f15081a.put(NISTObjectIdentifiers.id_sha3_384, MessageDigestAlgorithms.SHA3_384);
        f15081a.put(NISTObjectIdentifiers.id_sha3_512, MessageDigestAlgorithms.SHA3_512);
        f15081a.put(GMObjectIdentifiers.sm3, "SM3");
    }

    public static String getDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = f15081a.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }
}
