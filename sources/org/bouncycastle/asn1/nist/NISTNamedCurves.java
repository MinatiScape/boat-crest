package org.bouncycastle.asn1.nist;

import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.util.Strings;
import org.jose4j.keys.EllipticCurves;
/* loaded from: classes12.dex */
public class NISTNamedCurves {

    /* renamed from: a  reason: collision with root package name */
    public static final Hashtable f14419a = new Hashtable();
    public static final Hashtable b = new Hashtable();

    static {
        a("B-571", SECObjectIdentifiers.sect571r1);
        a("B-409", SECObjectIdentifiers.sect409r1);
        a("B-283", SECObjectIdentifiers.sect283r1);
        a("B-233", SECObjectIdentifiers.sect233r1);
        a("B-163", SECObjectIdentifiers.sect163r2);
        a("K-571", SECObjectIdentifiers.sect571k1);
        a("K-409", SECObjectIdentifiers.sect409k1);
        a("K-283", SECObjectIdentifiers.sect283k1);
        a("K-233", SECObjectIdentifiers.sect233k1);
        a("K-163", SECObjectIdentifiers.sect163k1);
        a(EllipticCurves.P_521, SECObjectIdentifiers.secp521r1);
        a(EllipticCurves.P_384, SECObjectIdentifiers.secp384r1);
        a(EllipticCurves.P_256, SECObjectIdentifiers.secp256r1);
        a("P-224", SECObjectIdentifiers.secp224r1);
        a("P-192", SECObjectIdentifiers.secp192r1);
    }

    public static void a(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        f14419a.put(str, aSN1ObjectIdentifier);
        b.put(aSN1ObjectIdentifier, str);
    }

    public static X9ECParameters getByName(String str) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) f14419a.get(Strings.toUpperCase(str));
        if (aSN1ObjectIdentifier != null) {
            return getByOID(aSN1ObjectIdentifier);
        }
        return null;
    }

    public static X9ECParameters getByOID(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return SECNamedCurves.getByOID(aSN1ObjectIdentifier);
    }

    public static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) b.get(aSN1ObjectIdentifier);
    }

    public static Enumeration getNames() {
        return f14419a.keys();
    }

    public static ASN1ObjectIdentifier getOID(String str) {
        return (ASN1ObjectIdentifier) f14419a.get(Strings.toUpperCase(str));
    }
}
