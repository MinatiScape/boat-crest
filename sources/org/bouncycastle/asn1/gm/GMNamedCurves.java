package org.bouncycastle.asn1.gm;

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
public class GMNamedCurves {

    /* renamed from: a  reason: collision with root package name */
    public static X9ECParametersHolder f14417a = new a();
    public static X9ECParametersHolder b = new b();
    public static final Hashtable c = new Hashtable();
    public static final Hashtable d = new Hashtable();
    public static final Hashtable e = new Hashtable();

    /* loaded from: classes12.dex */
    public static class a extends X9ECParametersHolder {
        @Override // org.bouncycastle.asn1.x9.X9ECParametersHolder
        public X9ECParameters createParameters() {
            BigInteger e = GMNamedCurves.e("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF");
            BigInteger e2 = GMNamedCurves.e("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC");
            BigInteger e3 = GMNamedCurves.e("28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93");
            BigInteger e4 = GMNamedCurves.e("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123");
            BigInteger valueOf = BigInteger.valueOf(1L);
            ECCurve c = GMNamedCurves.c(new ECCurve.Fp(e, e2, e3, e4, valueOf));
            return new X9ECParameters(c, new X9ECPoint(c, Hex.decode("0432C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0")), e4, valueOf, (byte[]) null);
        }
    }

    /* loaded from: classes12.dex */
    public static class b extends X9ECParametersHolder {
        @Override // org.bouncycastle.asn1.x9.X9ECParametersHolder
        public X9ECParameters createParameters() {
            BigInteger e = GMNamedCurves.e("BDB6F4FE3E8B1D9E0DA8C0D46F4C318CEFE4AFE3B6B8551F");
            BigInteger e2 = GMNamedCurves.e("BB8E5E8FBC115E139FE6A814FE48AAA6F0ADA1AA5DF91985");
            BigInteger e3 = GMNamedCurves.e("1854BEBDC31B21B7AEFC80AB0ECD10D5B1B3308E6DBF11C1");
            BigInteger e4 = GMNamedCurves.e("BDB6F4FE3E8B1D9E0DA8C0D40FC962195DFAE76F56564677");
            BigInteger valueOf = BigInteger.valueOf(1L);
            ECCurve c = GMNamedCurves.c(new ECCurve.Fp(e, e2, e3, e4, valueOf));
            return new X9ECParameters(c, new X9ECPoint(c, Hex.decode("044AD5F7048DE709AD51236DE65E4D4B482C836DC6E410664002BB3A02D4AAADACAE24817A4CA3A1B014B5270432DB27D2")), e4, valueOf, (byte[]) null);
        }
    }

    static {
        d("wapip192v1", GMObjectIdentifiers.wapip192v1, b);
        d("sm2p256v1", GMObjectIdentifiers.sm2p256v1, f14417a);
    }

    public static ECCurve c(ECCurve eCCurve) {
        return eCCurve;
    }

    public static void d(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, X9ECParametersHolder x9ECParametersHolder) {
        c.put(Strings.toLowerCase(str), aSN1ObjectIdentifier);
        e.put(aSN1ObjectIdentifier, str);
        d.put(aSN1ObjectIdentifier, x9ECParametersHolder);
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
        X9ECParametersHolder x9ECParametersHolder = (X9ECParametersHolder) d.get(aSN1ObjectIdentifier);
        if (x9ECParametersHolder == null) {
            return null;
        }
        return x9ECParametersHolder.getParameters();
    }

    public static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) e.get(aSN1ObjectIdentifier);
    }

    public static Enumeration getNames() {
        return e.elements();
    }

    public static ASN1ObjectIdentifier getOID(String str) {
        return (ASN1ObjectIdentifier) c.get(Strings.toLowerCase(str));
    }
}
