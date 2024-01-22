package org.bouncycastle.jcajce.provider.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class DigestFactory {

    /* renamed from: a  reason: collision with root package name */
    public static Set f15067a = new HashSet();
    public static Set b = new HashSet();
    public static Set c = new HashSet();
    public static Set d = new HashSet();
    public static Set e = new HashSet();
    public static Set f = new HashSet();
    public static Set g = new HashSet();
    public static Set h = new HashSet();
    public static Set i = new HashSet();
    public static Set j = new HashSet();
    public static Set k = new HashSet();
    public static Set l = new HashSet();
    public static Map m = new HashMap();

    static {
        f15067a.add(MessageDigestAlgorithms.MD5);
        Set set = f15067a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.md5;
        set.add(aSN1ObjectIdentifier.getId());
        b.add("SHA1");
        b.add("SHA-1");
        Set set2 = b;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = OIWObjectIdentifiers.idSHA1;
        set2.add(aSN1ObjectIdentifier2.getId());
        c.add("SHA224");
        c.add("SHA-224");
        Set set3 = c;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = NISTObjectIdentifiers.id_sha224;
        set3.add(aSN1ObjectIdentifier3.getId());
        d.add("SHA256");
        d.add("SHA-256");
        Set set4 = d;
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = NISTObjectIdentifiers.id_sha256;
        set4.add(aSN1ObjectIdentifier4.getId());
        e.add("SHA384");
        e.add("SHA-384");
        Set set5 = e;
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = NISTObjectIdentifiers.id_sha384;
        set5.add(aSN1ObjectIdentifier5.getId());
        f.add("SHA512");
        f.add("SHA-512");
        Set set6 = f;
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = NISTObjectIdentifiers.id_sha512;
        set6.add(aSN1ObjectIdentifier6.getId());
        g.add("SHA512(224)");
        g.add("SHA-512(224)");
        Set set7 = g;
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = NISTObjectIdentifiers.id_sha512_224;
        set7.add(aSN1ObjectIdentifier7.getId());
        h.add("SHA512(256)");
        h.add("SHA-512(256)");
        Set set8 = h;
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = NISTObjectIdentifiers.id_sha512_256;
        set8.add(aSN1ObjectIdentifier8.getId());
        i.add(MessageDigestAlgorithms.SHA3_224);
        Set set9 = i;
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = NISTObjectIdentifiers.id_sha3_224;
        set9.add(aSN1ObjectIdentifier9.getId());
        j.add("SHA3-256");
        Set set10 = j;
        ASN1ObjectIdentifier aSN1ObjectIdentifier10 = NISTObjectIdentifiers.id_sha3_256;
        set10.add(aSN1ObjectIdentifier10.getId());
        k.add(MessageDigestAlgorithms.SHA3_384);
        Set set11 = k;
        ASN1ObjectIdentifier aSN1ObjectIdentifier11 = NISTObjectIdentifiers.id_sha3_384;
        set11.add(aSN1ObjectIdentifier11.getId());
        l.add(MessageDigestAlgorithms.SHA3_512);
        Set set12 = l;
        ASN1ObjectIdentifier aSN1ObjectIdentifier12 = NISTObjectIdentifiers.id_sha3_512;
        set12.add(aSN1ObjectIdentifier12.getId());
        m.put(MessageDigestAlgorithms.MD5, aSN1ObjectIdentifier);
        m.put(aSN1ObjectIdentifier.getId(), aSN1ObjectIdentifier);
        m.put("SHA1", aSN1ObjectIdentifier2);
        m.put("SHA-1", aSN1ObjectIdentifier2);
        m.put(aSN1ObjectIdentifier2.getId(), aSN1ObjectIdentifier2);
        m.put("SHA224", aSN1ObjectIdentifier3);
        m.put("SHA-224", aSN1ObjectIdentifier3);
        m.put(aSN1ObjectIdentifier3.getId(), aSN1ObjectIdentifier3);
        m.put("SHA256", aSN1ObjectIdentifier4);
        m.put("SHA-256", aSN1ObjectIdentifier4);
        m.put(aSN1ObjectIdentifier4.getId(), aSN1ObjectIdentifier4);
        m.put("SHA384", aSN1ObjectIdentifier5);
        m.put("SHA-384", aSN1ObjectIdentifier5);
        m.put(aSN1ObjectIdentifier5.getId(), aSN1ObjectIdentifier5);
        m.put("SHA512", aSN1ObjectIdentifier6);
        m.put("SHA-512", aSN1ObjectIdentifier6);
        m.put(aSN1ObjectIdentifier6.getId(), aSN1ObjectIdentifier6);
        m.put("SHA512(224)", aSN1ObjectIdentifier7);
        m.put("SHA-512(224)", aSN1ObjectIdentifier7);
        m.put(aSN1ObjectIdentifier7.getId(), aSN1ObjectIdentifier7);
        m.put("SHA512(256)", aSN1ObjectIdentifier8);
        m.put("SHA-512(256)", aSN1ObjectIdentifier8);
        m.put(aSN1ObjectIdentifier8.getId(), aSN1ObjectIdentifier8);
        m.put(MessageDigestAlgorithms.SHA3_224, aSN1ObjectIdentifier9);
        m.put(aSN1ObjectIdentifier9.getId(), aSN1ObjectIdentifier9);
        m.put("SHA3-256", aSN1ObjectIdentifier10);
        m.put(aSN1ObjectIdentifier10.getId(), aSN1ObjectIdentifier10);
        m.put(MessageDigestAlgorithms.SHA3_384, aSN1ObjectIdentifier11);
        m.put(aSN1ObjectIdentifier11.getId(), aSN1ObjectIdentifier11);
        m.put(MessageDigestAlgorithms.SHA3_512, aSN1ObjectIdentifier12);
        m.put(aSN1ObjectIdentifier12.getId(), aSN1ObjectIdentifier12);
    }

    public static Digest getDigest(String str) {
        String upperCase = Strings.toUpperCase(str);
        if (b.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createSHA1();
        }
        if (f15067a.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createMD5();
        }
        if (c.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createSHA224();
        }
        if (d.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createSHA256();
        }
        if (e.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createSHA384();
        }
        if (f.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createSHA512();
        }
        if (g.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createSHA512_224();
        }
        if (h.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createSHA512_256();
        }
        if (i.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createSHA3_224();
        }
        if (j.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createSHA3_256();
        }
        if (k.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createSHA3_384();
        }
        if (l.contains(upperCase)) {
            return org.bouncycastle.crypto.util.DigestFactory.createSHA3_512();
        }
        return null;
    }

    public static ASN1ObjectIdentifier getOID(String str) {
        return (ASN1ObjectIdentifier) m.get(str);
    }

    public static boolean isSameDigest(String str, String str2) {
        return (b.contains(str) && b.contains(str2)) || (c.contains(str) && c.contains(str2)) || ((d.contains(str) && d.contains(str2)) || ((e.contains(str) && e.contains(str2)) || ((f.contains(str) && f.contains(str2)) || ((g.contains(str) && g.contains(str2)) || ((h.contains(str) && h.contains(str2)) || ((i.contains(str) && i.contains(str2)) || ((j.contains(str) && j.contains(str2)) || ((k.contains(str) && k.contains(str2)) || ((l.contains(str) && l.contains(str2)) || (f15067a.contains(str) && f15067a.contains(str2)))))))))));
    }
}
