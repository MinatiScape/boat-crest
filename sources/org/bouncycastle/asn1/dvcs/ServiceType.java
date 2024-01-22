package org.bouncycastle.asn1.dvcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
/* loaded from: classes12.dex */
public class ServiceType extends ASN1Object {
    public ASN1Enumerated h;
    public static final ServiceType CPD = new ServiceType(1);
    public static final ServiceType VSD = new ServiceType(2);
    public static final ServiceType VPKC = new ServiceType(3);
    public static final ServiceType CCPD = new ServiceType(4);

    public ServiceType(int i) {
        this.h = new ASN1Enumerated(i);
    }

    public ServiceType(ASN1Enumerated aSN1Enumerated) {
        this.h = aSN1Enumerated;
    }

    public static ServiceType getInstance(Object obj) {
        if (obj instanceof ServiceType) {
            return (ServiceType) obj;
        }
        if (obj != null) {
            return new ServiceType(ASN1Enumerated.getInstance(obj));
        }
        return null;
    }

    public static ServiceType getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Enumerated.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getValue() {
        return this.h.getValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }

    public String toString() {
        int intValue = this.h.getValue().intValue();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(intValue);
        sb.append(intValue == CPD.getValue().intValue() ? "(CPD)" : intValue == VSD.getValue().intValue() ? "(VSD)" : intValue == VPKC.getValue().intValue() ? "(VPKC)" : intValue == CCPD.getValue().intValue() ? "(CCPD)" : "?");
        return sb.toString();
    }
}
