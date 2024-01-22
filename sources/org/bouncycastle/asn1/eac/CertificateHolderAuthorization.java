package org.bouncycastle.asn1.eac;

import java.io.IOException;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.util.Integers;
/* loaded from: classes12.dex */
public class CertificateHolderAuthorization extends ASN1Object {
    public static final int CVCA = 192;
    public static final int DV_DOMESTIC = 128;
    public static final int DV_FOREIGN = 64;
    public static final int IS = 0;
    public static final int RADG3 = 1;
    public static final int RADG4 = 2;
    public static final ASN1ObjectIdentifier id_role_EAC = EACObjectIdentifiers.bsi_de.branch("3.1.2.1");
    public static Hashtable j = new Hashtable();
    public static BidirectionalMap k = new BidirectionalMap();
    public ASN1ObjectIdentifier h;
    public DERApplicationSpecific i;

    static {
        new Hashtable();
        j.put(Integers.valueOf(2), "RADG4");
        j.put(Integers.valueOf(1), "RADG3");
        k.put(Integers.valueOf(192), "CVCA");
        k.put(Integers.valueOf(128), "DV_DOMESTIC");
        k.put(Integers.valueOf(64), "DV_FOREIGN");
        k.put(Integers.valueOf(0), "IS");
    }

    public CertificateHolderAuthorization(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i) throws IOException {
        b(aSN1ObjectIdentifier);
        a((byte) i);
    }

    public CertificateHolderAuthorization(DERApplicationSpecific dERApplicationSpecific) throws IOException {
        if (dERApplicationSpecific.getApplicationTag() == 76) {
            c(new ASN1InputStream(dERApplicationSpecific.getContents()));
        }
    }

    public static int getFlag(String str) {
        Integer num = (Integer) k.getReverse(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("Unknown value " + str);
    }

    public static String getRoleDescription(int i) {
        return (String) k.get(Integers.valueOf(i));
    }

    public final void a(byte b) {
        this.i = new DERApplicationSpecific(19, new byte[]{b});
    }

    public final void b(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.h = aSN1ObjectIdentifier;
    }

    public final void c(ASN1InputStream aSN1InputStream) throws IOException {
        ASN1Primitive readObject = aSN1InputStream.readObject();
        if (!(readObject instanceof ASN1ObjectIdentifier)) {
            throw new IllegalArgumentException("no Oid in CerticateHolderAuthorization");
        }
        this.h = (ASN1ObjectIdentifier) readObject;
        ASN1Primitive readObject2 = aSN1InputStream.readObject();
        if (!(readObject2 instanceof DERApplicationSpecific)) {
            throw new IllegalArgumentException("No access rights in CerticateHolderAuthorization");
        }
        this.i = (DERApplicationSpecific) readObject2;
    }

    public int getAccessRights() {
        return this.i.getContents()[0] & 255;
    }

    public ASN1ObjectIdentifier getOid() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        return new DERApplicationSpecific(76, aSN1EncodableVector);
    }
}
