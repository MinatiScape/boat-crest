package org.bouncycastle.x509;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public static Hashtable f15419a = new Hashtable();
    public static Hashtable b = new Hashtable();
    public static Set c = new HashSet();

    /* loaded from: classes13.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public Object f15420a;
        public Provider b;

        public a(Object obj, Provider provider) {
            this.f15420a = obj;
            this.b = provider;
        }

        public Object a() {
            return this.f15420a;
        }

        public Provider b() {
            return this.b;
        }
    }

    static {
        Hashtable hashtable = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.md2WithRSAEncryption;
        hashtable.put("MD2WITHRSAENCRYPTION", aSN1ObjectIdentifier);
        f15419a.put("MD2WITHRSA", aSN1ObjectIdentifier);
        Hashtable hashtable2 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = PKCSObjectIdentifiers.md5WithRSAEncryption;
        hashtable2.put("MD5WITHRSAENCRYPTION", aSN1ObjectIdentifier2);
        f15419a.put("MD5WITHRSA", aSN1ObjectIdentifier2);
        Hashtable hashtable3 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = PKCSObjectIdentifiers.sha1WithRSAEncryption;
        hashtable3.put("SHA1WITHRSAENCRYPTION", aSN1ObjectIdentifier3);
        f15419a.put("SHA1WITHRSA", aSN1ObjectIdentifier3);
        Hashtable hashtable4 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = PKCSObjectIdentifiers.sha224WithRSAEncryption;
        hashtable4.put("SHA224WITHRSAENCRYPTION", aSN1ObjectIdentifier4);
        f15419a.put("SHA224WITHRSA", aSN1ObjectIdentifier4);
        Hashtable hashtable5 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = PKCSObjectIdentifiers.sha256WithRSAEncryption;
        hashtable5.put("SHA256WITHRSAENCRYPTION", aSN1ObjectIdentifier5);
        f15419a.put("SHA256WITHRSA", aSN1ObjectIdentifier5);
        Hashtable hashtable6 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = PKCSObjectIdentifiers.sha384WithRSAEncryption;
        hashtable6.put("SHA384WITHRSAENCRYPTION", aSN1ObjectIdentifier6);
        f15419a.put("SHA384WITHRSA", aSN1ObjectIdentifier6);
        Hashtable hashtable7 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = PKCSObjectIdentifiers.sha512WithRSAEncryption;
        hashtable7.put("SHA512WITHRSAENCRYPTION", aSN1ObjectIdentifier7);
        f15419a.put("SHA512WITHRSA", aSN1ObjectIdentifier7);
        Hashtable hashtable8 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = PKCSObjectIdentifiers.id_RSASSA_PSS;
        hashtable8.put("SHA1WITHRSAANDMGF1", aSN1ObjectIdentifier8);
        f15419a.put("SHA224WITHRSAANDMGF1", aSN1ObjectIdentifier8);
        f15419a.put("SHA256WITHRSAANDMGF1", aSN1ObjectIdentifier8);
        f15419a.put("SHA384WITHRSAANDMGF1", aSN1ObjectIdentifier8);
        f15419a.put("SHA512WITHRSAANDMGF1", aSN1ObjectIdentifier8);
        Hashtable hashtable9 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160;
        hashtable9.put("RIPEMD160WITHRSAENCRYPTION", aSN1ObjectIdentifier9);
        f15419a.put("RIPEMD160WITHRSA", aSN1ObjectIdentifier9);
        Hashtable hashtable10 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier10 = TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128;
        hashtable10.put("RIPEMD128WITHRSAENCRYPTION", aSN1ObjectIdentifier10);
        f15419a.put("RIPEMD128WITHRSA", aSN1ObjectIdentifier10);
        Hashtable hashtable11 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier11 = TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256;
        hashtable11.put("RIPEMD256WITHRSAENCRYPTION", aSN1ObjectIdentifier11);
        f15419a.put("RIPEMD256WITHRSA", aSN1ObjectIdentifier11);
        Hashtable hashtable12 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier12 = X9ObjectIdentifiers.id_dsa_with_sha1;
        hashtable12.put("SHA1WITHDSA", aSN1ObjectIdentifier12);
        f15419a.put("DSAWITHSHA1", aSN1ObjectIdentifier12);
        Hashtable hashtable13 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier13 = NISTObjectIdentifiers.dsa_with_sha224;
        hashtable13.put("SHA224WITHDSA", aSN1ObjectIdentifier13);
        Hashtable hashtable14 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier14 = NISTObjectIdentifiers.dsa_with_sha256;
        hashtable14.put("SHA256WITHDSA", aSN1ObjectIdentifier14);
        Hashtable hashtable15 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier15 = NISTObjectIdentifiers.dsa_with_sha384;
        hashtable15.put("SHA384WITHDSA", aSN1ObjectIdentifier15);
        Hashtable hashtable16 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier16 = NISTObjectIdentifiers.dsa_with_sha512;
        hashtable16.put("SHA512WITHDSA", aSN1ObjectIdentifier16);
        Hashtable hashtable17 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier17 = X9ObjectIdentifiers.ecdsa_with_SHA1;
        hashtable17.put("SHA1WITHECDSA", aSN1ObjectIdentifier17);
        f15419a.put("ECDSAWITHSHA1", aSN1ObjectIdentifier17);
        Hashtable hashtable18 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier18 = X9ObjectIdentifiers.ecdsa_with_SHA224;
        hashtable18.put("SHA224WITHECDSA", aSN1ObjectIdentifier18);
        Hashtable hashtable19 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier19 = X9ObjectIdentifiers.ecdsa_with_SHA256;
        hashtable19.put("SHA256WITHECDSA", aSN1ObjectIdentifier19);
        Hashtable hashtable20 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier20 = X9ObjectIdentifiers.ecdsa_with_SHA384;
        hashtable20.put("SHA384WITHECDSA", aSN1ObjectIdentifier20);
        Hashtable hashtable21 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier21 = X9ObjectIdentifiers.ecdsa_with_SHA512;
        hashtable21.put("SHA512WITHECDSA", aSN1ObjectIdentifier21);
        Hashtable hashtable22 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier22 = CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94;
        hashtable22.put("GOST3411WITHGOST3410", aSN1ObjectIdentifier22);
        f15419a.put("GOST3411WITHGOST3410-94", aSN1ObjectIdentifier22);
        Hashtable hashtable23 = f15419a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier23 = CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001;
        hashtable23.put("GOST3411WITHECGOST3410", aSN1ObjectIdentifier23);
        f15419a.put("GOST3411WITHECGOST3410-2001", aSN1ObjectIdentifier23);
        f15419a.put("GOST3411WITHGOST3410-2001", aSN1ObjectIdentifier23);
        c.add(aSN1ObjectIdentifier17);
        c.add(aSN1ObjectIdentifier18);
        c.add(aSN1ObjectIdentifier19);
        c.add(aSN1ObjectIdentifier20);
        c.add(aSN1ObjectIdentifier21);
        c.add(aSN1ObjectIdentifier12);
        c.add(aSN1ObjectIdentifier13);
        c.add(aSN1ObjectIdentifier14);
        c.add(aSN1ObjectIdentifier15);
        c.add(aSN1ObjectIdentifier16);
        c.add(aSN1ObjectIdentifier22);
        c.add(aSN1ObjectIdentifier23);
        ASN1ObjectIdentifier aSN1ObjectIdentifier24 = OIWObjectIdentifiers.idSHA1;
        DERNull dERNull = DERNull.INSTANCE;
        b.put("SHA1WITHRSAANDMGF1", d(new AlgorithmIdentifier(aSN1ObjectIdentifier24, dERNull), 20));
        b.put("SHA224WITHRSAANDMGF1", d(new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224, dERNull), 28));
        b.put("SHA256WITHRSAANDMGF1", d(new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, dERNull), 32));
        b.put("SHA384WITHRSAANDMGF1", d(new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, dERNull), 48));
        b.put("SHA512WITHRSAANDMGF1", d(new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, dERNull), 64));
    }

    public static byte[] a(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str, String str2, PrivateKey privateKey, SecureRandom secureRandom, ASN1Encodable aSN1Encodable) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (aSN1ObjectIdentifier != null) {
            Signature l = l(str, str2);
            if (secureRandom != null) {
                l.initSign(privateKey, secureRandom);
            } else {
                l.initSign(privateKey);
            }
            l.update(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER));
            return l.sign();
        }
        throw new IllegalStateException("no signature algorithm specified");
    }

    public static byte[] b(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str, PrivateKey privateKey, SecureRandom secureRandom, ASN1Encodable aSN1Encodable) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (aSN1ObjectIdentifier != null) {
            Signature k = k(str);
            if (secureRandom != null) {
                k.initSign(privateKey, secureRandom);
            } else {
                k.initSign(privateKey);
            }
            k.update(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER));
            return k.sign();
        }
        throw new IllegalStateException("no signature algorithm specified");
    }

    public static X509Principal c(X500Principal x500Principal) {
        try {
            return new X509Principal(x500Principal.getEncoded());
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot convert principal");
        }
    }

    public static RSASSAPSSparams d(AlgorithmIdentifier algorithmIdentifier, int i) {
        return new RSASSAPSSparams(algorithmIdentifier, new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, algorithmIdentifier), new ASN1Integer(i), new ASN1Integer(1L));
    }

    public static Iterator e() {
        Enumeration keys = f15419a.keys();
        ArrayList arrayList = new ArrayList();
        while (keys.hasMoreElements()) {
            arrayList.add(keys.nextElement());
        }
        return arrayList.iterator();
    }

    public static ASN1ObjectIdentifier f(String str) {
        String upperCase = Strings.toUpperCase(str);
        return f15419a.containsKey(upperCase) ? (ASN1ObjectIdentifier) f15419a.get(upperCase) : new ASN1ObjectIdentifier(upperCase);
    }

    public static a g(String str, String str2) throws NoSuchAlgorithmException {
        Provider[] providers = Security.getProviders();
        for (int i = 0; i != providers.length; i++) {
            a h = h(str, Strings.toUpperCase(str2), providers[i]);
            if (h != null) {
                return h;
            }
            try {
                h(str, str2, providers[i]);
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        throw new NoSuchAlgorithmException("cannot find implementation " + str2);
    }

    public static a h(String str, String str2, Provider provider) throws NoSuchAlgorithmException {
        String upperCase = Strings.toUpperCase(str2);
        while (true) {
            String property = provider.getProperty("Alg.Alias." + str + "." + upperCase);
            if (property == null) {
                break;
            }
            upperCase = property;
        }
        String property2 = provider.getProperty(str + "." + upperCase);
        if (property2 == null) {
            throw new NoSuchAlgorithmException("cannot find implementation " + upperCase + " for provider " + provider.getName());
        }
        try {
            ClassLoader classLoader = provider.getClass().getClassLoader();
            return new a((classLoader != null ? classLoader.loadClass(property2) : Class.forName(property2)).newInstance(), provider);
        } catch (ClassNotFoundException unused) {
            throw new IllegalStateException("algorithm " + upperCase + " in provider " + provider.getName() + " but no class \"" + property2 + "\" found!");
        } catch (Exception unused2) {
            throw new IllegalStateException("algorithm " + upperCase + " in provider " + provider.getName() + " but class \"" + property2 + "\" inaccessible!");
        }
    }

    public static Provider i(String str) throws NoSuchProviderException {
        Provider provider = Security.getProvider(str);
        if (provider != null) {
            return provider;
        }
        throw new NoSuchProviderException("Provider " + str + " not found");
    }

    public static AlgorithmIdentifier j(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (c.contains(aSN1ObjectIdentifier)) {
            return new AlgorithmIdentifier(aSN1ObjectIdentifier);
        }
        String upperCase = Strings.toUpperCase(str);
        return b.containsKey(upperCase) ? new AlgorithmIdentifier(aSN1ObjectIdentifier, (ASN1Encodable) b.get(upperCase)) : new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE);
    }

    public static Signature k(String str) throws NoSuchAlgorithmException {
        return Signature.getInstance(str);
    }

    public static Signature l(String str, String str2) throws NoSuchProviderException, NoSuchAlgorithmException {
        return str2 != null ? Signature.getInstance(str, str2) : Signature.getInstance(str);
    }
}
