package org.bouncycastle.asn1.x9;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.anssi.ANSSINamedCurves;
import org.bouncycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.teletrust.TeleTrusTNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
/* loaded from: classes12.dex */
public class ECNamedCurveTable {
    public static void a(Vector vector, Enumeration enumeration) {
        while (enumeration.hasMoreElements()) {
            vector.addElement(enumeration.nextElement());
        }
    }

    public static X9ECParameters b(ECDomainParameters eCDomainParameters) {
        if (eCDomainParameters == null) {
            return null;
        }
        return new X9ECParameters(eCDomainParameters.getCurve(), eCDomainParameters.getG(), eCDomainParameters.getN(), eCDomainParameters.getH(), eCDomainParameters.getSeed());
    }

    public static X9ECParameters getByName(String str) {
        X9ECParameters byName = X962NamedCurves.getByName(str);
        if (byName == null) {
            byName = SECNamedCurves.getByName(str);
        }
        if (byName == null) {
            byName = NISTNamedCurves.getByName(str);
        }
        if (byName == null) {
            byName = TeleTrusTNamedCurves.getByName(str);
        }
        if (byName == null) {
            byName = ANSSINamedCurves.getByName(str);
        }
        if (byName == null) {
            byName = b(ECGOST3410NamedCurves.getByName(str));
        }
        return byName == null ? GMNamedCurves.getByName(str) : byName;
    }

    public static X9ECParameters getByOID(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        X9ECParameters byOID = X962NamedCurves.getByOID(aSN1ObjectIdentifier);
        if (byOID == null) {
            byOID = SECNamedCurves.getByOID(aSN1ObjectIdentifier);
        }
        if (byOID == null) {
            byOID = TeleTrusTNamedCurves.getByOID(aSN1ObjectIdentifier);
        }
        if (byOID == null) {
            byOID = ANSSINamedCurves.getByOID(aSN1ObjectIdentifier);
        }
        if (byOID == null) {
            byOID = b(ECGOST3410NamedCurves.getByOID(aSN1ObjectIdentifier));
        }
        return byOID == null ? GMNamedCurves.getByOID(aSN1ObjectIdentifier) : byOID;
    }

    public static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String name = X962NamedCurves.getName(aSN1ObjectIdentifier);
        if (name == null) {
            name = SECNamedCurves.getName(aSN1ObjectIdentifier);
        }
        if (name == null) {
            name = NISTNamedCurves.getName(aSN1ObjectIdentifier);
        }
        if (name == null) {
            name = TeleTrusTNamedCurves.getName(aSN1ObjectIdentifier);
        }
        if (name == null) {
            name = ANSSINamedCurves.getName(aSN1ObjectIdentifier);
        }
        if (name == null) {
            name = ECGOST3410NamedCurves.getName(aSN1ObjectIdentifier);
        }
        return name == null ? GMNamedCurves.getName(aSN1ObjectIdentifier) : name;
    }

    public static Enumeration getNames() {
        Vector vector = new Vector();
        a(vector, X962NamedCurves.getNames());
        a(vector, SECNamedCurves.getNames());
        a(vector, NISTNamedCurves.getNames());
        a(vector, TeleTrusTNamedCurves.getNames());
        a(vector, ANSSINamedCurves.getNames());
        a(vector, ECGOST3410NamedCurves.getNames());
        a(vector, GMNamedCurves.getNames());
        return vector.elements();
    }

    public static ASN1ObjectIdentifier getOID(String str) {
        ASN1ObjectIdentifier oid = X962NamedCurves.getOID(str);
        if (oid == null) {
            oid = SECNamedCurves.getOID(str);
        }
        if (oid == null) {
            oid = NISTNamedCurves.getOID(str);
        }
        if (oid == null) {
            oid = TeleTrusTNamedCurves.getOID(str);
        }
        if (oid == null) {
            oid = ANSSINamedCurves.getOID(str);
        }
        if (oid == null) {
            oid = ECGOST3410NamedCurves.getOID(str);
        }
        return oid == null ? GMNamedCurves.getOID(str) : oid;
    }
}
