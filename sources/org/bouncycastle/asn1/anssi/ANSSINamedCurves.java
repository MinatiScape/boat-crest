package org.bouncycastle.asn1.anssi;

import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ECParametersHolder;
import org.bouncycastle.asn1.x9.X9ECPoint;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes12.dex */
public class ANSSINamedCurves {

    /* renamed from: a  reason: collision with root package name */
    public static X9ECParametersHolder f14392a = new a();
    public static final Hashtable b = new Hashtable();
    public static final Hashtable c = new Hashtable();
    public static final Hashtable d = new Hashtable();

    /* loaded from: classes12.dex */
    public static class a extends X9ECParametersHolder {
        @Override // org.bouncycastle.asn1.x9.X9ECParametersHolder
        public X9ECParameters createParameters() {
            BigInteger e = ANSSINamedCurves.e("F1FD178C0B3AD58F10126DE8CE42435B3961ADBCABC8CA6DE8FCF353D86E9C03");
            BigInteger e2 = ANSSINamedCurves.e("F1FD178C0B3AD58F10126DE8CE42435B3961ADBCABC8CA6DE8FCF353D86E9C00");
            BigInteger e3 = ANSSINamedCurves.e("EE353FCA5428A9300D4ABA754A44C00FDFEC0C9AE4B1A1803075ED967B7BB73F");
            BigInteger e4 = ANSSINamedCurves.e("F1FD178C0B3AD58F10126DE8CE42435B53DC67E140D2BF941FFDD459C6D655E1");
            BigInteger valueOf = BigInteger.valueOf(1L);
            ECCurve c = ANSSINamedCurves.c(new ECCurve.Fp(e, e2, e3, e4, valueOf));
            return new X9ECParameters(c, new X9ECPoint(c, Hex.decode("04B6B3D4C356C139EB31183D4749D423958C27D2DCAF98B70164C97A2DD98F5CFF6142E0F7C8B204911F9271F0F3ECEF8C2701C307E8E4C9E183115A1554062CFB")), e4, valueOf, (byte[]) null);
        }
    }

    static {
        d("FRP256v1", ANSSIObjectIdentifiers.FRP256v1, f14392a);
    }

    public static ECCurve c(ECCurve eCCurve) {
        return eCCurve;
    }

    public static void d(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, X9ECParametersHolder x9ECParametersHolder) {
        b.put(Strings.toLowerCase(str), aSN1ObjectIdentifier);
        d.put(aSN1ObjectIdentifier, str);
        c.put(aSN1ObjectIdentifier, x9ECParametersHolder);
    }

    public static BigInteger e(String str) {
        return new BigInteger(1, Hex.decode(str));
    }

    public static X9ECParameters getByName(String str) {
        ASN1ObjectIdentifier oid = getOID(str);
        if (oid == null) {
            return null;
        }
        return getByOID(oid);
    }

    public static X9ECParameters getByOID(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        X9ECParametersHolder x9ECParametersHolder = (X9ECParametersHolder) c.get(aSN1ObjectIdentifier);
        if (x9ECParametersHolder == null) {
            return null;
        }
        return x9ECParametersHolder.getParameters();
    }

    public static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) d.get(aSN1ObjectIdentifier);
    }

    public static Enumeration getNames() {
        return d.elements();
    }

    public static ASN1ObjectIdentifier getOID(String str) {
        return (ASN1ObjectIdentifier) b.get(Strings.toLowerCase(str));
    }
}
