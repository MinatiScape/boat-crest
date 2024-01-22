package org.bouncycastle.asn1.cmc;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
/* loaded from: classes12.dex */
public class CMCStatus extends ASN1Object {
    public static final CMCStatus confirmRequired;
    public static final CMCStatus failed;
    public static Map i;
    public static final CMCStatus noSupport;
    public static final CMCStatus partial;
    public static final CMCStatus pending;
    public static final CMCStatus popRequired;
    public static final CMCStatus success;
    public final ASN1Integer h;

    static {
        CMCStatus cMCStatus = new CMCStatus(new ASN1Integer(0L));
        success = cMCStatus;
        CMCStatus cMCStatus2 = new CMCStatus(new ASN1Integer(2L));
        failed = cMCStatus2;
        CMCStatus cMCStatus3 = new CMCStatus(new ASN1Integer(3L));
        pending = cMCStatus3;
        CMCStatus cMCStatus4 = new CMCStatus(new ASN1Integer(4L));
        noSupport = cMCStatus4;
        CMCStatus cMCStatus5 = new CMCStatus(new ASN1Integer(5L));
        confirmRequired = cMCStatus5;
        CMCStatus cMCStatus6 = new CMCStatus(new ASN1Integer(6L));
        popRequired = cMCStatus6;
        CMCStatus cMCStatus7 = new CMCStatus(new ASN1Integer(7L));
        partial = cMCStatus7;
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put(cMCStatus.h, cMCStatus);
        i.put(cMCStatus2.h, cMCStatus2);
        i.put(cMCStatus3.h, cMCStatus3);
        i.put(cMCStatus4.h, cMCStatus4);
        i.put(cMCStatus5.h, cMCStatus5);
        i.put(cMCStatus6.h, cMCStatus6);
        i.put(cMCStatus7.h, cMCStatus7);
    }

    public CMCStatus(ASN1Integer aSN1Integer) {
        this.h = aSN1Integer;
    }

    public static CMCStatus getInstance(Object obj) {
        if (obj instanceof CMCStatus) {
            return (CMCStatus) obj;
        }
        if (obj != null) {
            CMCStatus cMCStatus = (CMCStatus) i.get(ASN1Integer.getInstance(obj));
            if (cMCStatus != null) {
                return cMCStatus;
            }
            throw new IllegalArgumentException("unknown object in getInstance(): " + obj.getClass().getName());
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }
}
